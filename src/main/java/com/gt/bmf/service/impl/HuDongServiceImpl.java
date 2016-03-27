package com.gt.bmf.service.impl;

import com.gt.bmf.BmfConstants;
import com.gt.bmf.common.page.PageList;
import com.gt.bmf.dao.BmfBaseDao;
import com.gt.bmf.dao.HuDongDao;
import com.gt.bmf.pojo.HuDong;
import com.gt.bmf.service.HuDongService;
import com.gt.bmf.util.StockUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("HuDongService")
public class HuDongServiceImpl extends BmfBaseServiceImpl<HuDong> implements HuDongService {
	private HuDongDao huDongDao;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
	@Qualifier("HuDongDao")
	@Override
	public void setBmfBaseDao(BmfBaseDao<HuDong> bmfBaseDao) {
		this.bmfBaseDao = bmfBaseDao;
		this.huDongDao = (HuDongDao) bmfBaseDao;

	}

    @PostConstruct
    public void init(){

    }

    public  void updateData() throws ParseException, IOException, InterruptedException {
        Map<String,String> params  = new HashMap<String, String>();
        params.put("isGuDong","true");
        params.put("status",BmfConstants.GLOBAL_INVALID);
        PageList<HuDong> pageList = findPageData(1,5000,params);
        for(HuDong obj : pageList.getData()){

            int count =  StockUtils.getGuDongRenShu(obj.getAnswer());
            if(count <=100 || count==2015|| count==2016|| count==2014){
                continue;
            }

            Date date =   StockUtils.getGuDongDate(obj.getAnswer());
            if(date==null){
                date =   StockUtils.getGuDongDate(obj.getQuestion());
            }
            if(date==null){
                continue;
            }
            if(date.after(new Date())){
                date = DateUtils.addYears(date,-1);
            }
            //System.out.println("code["+obj.getCode()+"] Ren Shu["+count+"] date["+date+"]");

            obj.setMarkCount(count);
            obj.setMarkDate(date);
            obj.setStatus(BmfConstants.GLOBAL_VALID);
            update(obj);

        }
    }


    public  void checkPartData() throws ParseException, IOException, InterruptedException {
        Date startDate = sdf2.parse("2016-03-19");
        while (startDate.before(new Date())){
            Date endDate  = DateUtils.addDays(startDate,1);
            Map<String ,String> map = new HashMap<String,String>();
            map.put("condition.dateFrom",sdf2.format(startDate));
            map.put("condition.dateTo",sdf2.format(endDate));
            checkGuDong(map);
            // service.checkHuDong(map);
            startDate = DateUtils.addDays(endDate,1);;
        }
        updateData();
    }


    public void saveData(Document doc, Boolean isGuDong) throws ParseException {
        Elements elements = doc.getElementsByClass("req_box2");
        for(Element element : elements) {
            HuDong obj = null;
            Elements tds = element.getElementsByTag("td");
            if(tds.size()==1) break;
            String td1[] = tds.get(1).html().split("<br>");
            String code = td1[1];
            String name = td1[0];
            Element td3 = tds.get(3);
            String question = td3.text();
            String questionDate = tds.get(6).text();
            String answer = tds.get(11).text();
            String answerDate = tds.get(14).text();
          //  String id = td3.getElementsByTag("a").get(0).attr("href").substring(27);
            String  id = null;
            String href = td3.getElementsByTag("a").get(0).attr("href");

            if(StringUtils.contains(href,"&")){
                id = StringUtils.substringBetween(href,"questionId=","&");
             /*   Pattern pattern = Pattern.compile("\\d{2,}");
                Matcher matcher = pattern.matcher(answer);
                while(matcher.find()){
                    obj.setStatus(BmfConstants.GLOBAL_EDITING);
                }*/
            }else{
                id = href.substring(27);
            }
            Long _id = Long.valueOf(id);
            obj = huDongDao.get(_id);
            if(obj==null){
                obj  = new HuDong();
                obj.setGuDong(isGuDong);
                obj.setStatus(BmfConstants.GLOBAL_INVALID);
                obj.setAnswer(answer);
                obj.setAnswerDate(sdf.parse(answerDate));
                obj.setCode(code);
                obj.setId(_id);
                obj.setName(name);
                obj.setQuestion(question);
                obj.setQuestionDate(sdf.parse(questionDate));
                huDongDao.save(obj);
            }
        }
    }



    public Document checkHuDongDocument(Map<String, String> params) throws IOException, InterruptedException, ParseException {
        return  Jsoup.connect("http://ircs.p5w.net/ircs/interaction/moreQuestionForGszz.do")
                .data(params)
                .userAgent("Mozilla")
                .timeout(90000)
                .post();
    }
    public Document checkGuDongDocument(Map<String, String> params) throws IOException, InterruptedException, ParseException {
        return  Jsoup.connect("http://ircs.p5w.net/ircs/interaction/queryQuestionByGszz.do")
                .postDataCharset("UTF-8")
                .data("condition.status","3")
                .data("condition.keyWord","股东人数")
                .data("condition.searchRange","0")
                .data(params)
                .userAgent("Mozilla")
                .timeout(90000)
                .post();
    }
    public void checkHuDong(Map<String, String> params) throws IOException, InterruptedException, ParseException {
        params.put("pageNo","1");
        Document doc = checkHuDongDocument(params);
        int totalPage = getTotalPageNo(doc);
        if(totalPage>0){
            saveData(doc, false);
            for(int i=2;i<totalPage;i++){
                params.put("pageNo",""+i);
                System.out.println(params);
                doc = checkHuDongDocument(params);
                saveData(doc, false);
            }
        }


      //
    }

    private int getTotalPageNo( Document doc){
        Elements elements = doc.getElementsByClass("yms_box");
        String text  = elements.text();
        System.out.print(text);
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        int totalPage = 0;
        while(matcher.find()){
            totalPage  = Integer.valueOf(matcher.group());
        }
        return  totalPage;
    }
    public void checkGuDong(Map<String, String> params) throws IOException, InterruptedException, ParseException {
        params.put("pageNo","1");
        System.out.println(params);
        Document doc = checkGuDongDocument(params);
        int totalPage = getTotalPageNo(doc);
        if(totalPage>0){
            saveData(doc, true);
            for(int i=2;i<totalPage;i++){
                params.put("pageNo",""+i);
                System.out.println(params);
                doc = checkGuDongDocument(params);
                saveData(doc, true);
            }
        }

    }

    @Override
    public PageList<HuDong> findPageData(int pageNum, int pageSize, Map<String, String> params) {
        return huDongDao.findPageData(pageNum, pageSize, params);
    }

    @Override
    public void updateBlackGuDong() {
        int c = this.huDongDao.executeByHQL("update HuDong set isGuDong= ? where isGuDong =? and status =? and code in (select id from Black)",false,true,"I");
        System.out.println("updateBlackGuDong count["+c+"]");
    }

    @Override
    public void deleteHuDongLessDate(Date date) {
        huDongDao.deleteHuDongLessDate(date);
    }

    @Override
    public void updateAllStatus() {
        huDongDao.executeByHQL("update HuDong set status=?", BmfConstants.GLOBAL_YES);
    }
}
