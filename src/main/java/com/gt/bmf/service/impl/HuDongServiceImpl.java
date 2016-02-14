package com.gt.bmf.service.impl;

import com.gt.bmf.BmfConstants;
import com.gt.bmf.common.page.PageList;
import com.gt.bmf.dao.BmfBaseDao;
import com.gt.bmf.dao.HuDongDao;
import com.gt.bmf.pojo.HuDong;
import com.gt.bmf.service.HuDongService;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("HuDongService")
public class HuDongServiceImpl extends BmfBaseServiceImpl<HuDong> implements HuDongService {
    @Value("${gf.cookie}")
    private String gfCookie;
    @Value("${gf.session}")
    private String gfSession;
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




    public void saveData(Document doc ) throws ParseException {
        Elements elements = doc.getElementsByClass("req_box2");
        for(Element element : elements) {
            HuDong obj = new HuDong();
            Elements tds = element.getElementsByTag("td");
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
                Pattern pattern = Pattern.compile("\\d{2,}");
                Matcher matcher = pattern.matcher(answer);
                while(matcher.find()){
                    //System.out.println(  matcher.group());
                    obj.setStatus(BmfConstants.GLOBAL_EDITING);
                }
            }else{
                id = href.substring(27);
            }




            obj.setAnswer(answer);
            obj.setAnswerDate(sdf.parse(answerDate));
            obj.setCode(code);
            obj.setId(Long.valueOf(id));
            obj.setName(name);
            obj.setQuestion(question);
            obj.setQuestionDate(sdf.parse(questionDate));

            System.out.println(id);
            System.out.println(code);
            System.out.println(name);
            System.out.println("--------------------------------------------");
/*
            System.out.println(id);
            System.out.println(question);
            System.out.println(questionDate);
            System.out.println(answer);
            System.out.println(answerDate);
          */
            huDongDao.merge(obj);


        }
    }
    public void checkHuDong(String pageNo) throws IOException, InterruptedException, ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-100);
        Document doc = Jsoup.connect("http://ircs.p5w.net/ircs/interaction/moreQuestionForGszz.do")
                .data("condition.dateFrom", sdf2.format(calendar.getTime()))
                .data("condition.dateTo", sdf2.format(new Date()))
                .data("pageNo",pageNo)
                .userAgent("Mozilla")
                .timeout(9000)
                .post();
        saveData(doc);
    }
/**
 * condition.status:3
 condition.keyWord:股东人数
 condition.stockcode:
 condition.searchType:content
 condition.questionCla:
 condition.questionAtr:
 condition.marketType:
 condition.questioner:
 condition.searchRange:0
 condition.provinceCode:
 condition.plate:
 * **/
    public void checkGuDong(String pageNo) throws IOException, InterruptedException, ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-100);

        String from = sdf2.format(calendar.getTime());
        String to = sdf2.format(new Date());




        Document doc = Jsoup.connect("http://ircs.p5w.net/ircs/interaction/queryQuestionByGszz.do")
                .postDataCharset("UTF-8")
                .data("condition.dateFrom", from)
                .data("condition.dateTo",to )
                .data("condition.status","3")
                .data("condition.keyWord","股东人数")
                .data("condition.searchRange","0")
                .data("pageNo",pageNo)
                .userAgent("Mozilla")
                .timeout(9000)
                .post();

        //System.out.println(doc.html());
        saveData(doc);
    }

    @Override
    public PageList<HuDong> findPageData(int pageNum, int pageSize, Map<String, String> params) {
        return huDongDao.findPageData(pageNum, pageSize, params);
    }


}
