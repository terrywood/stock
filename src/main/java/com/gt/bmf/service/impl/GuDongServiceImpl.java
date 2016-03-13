package com.gt.bmf.service.impl;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.dao.BmfBaseDao;
import com.gt.bmf.dao.StockMasterDao;
import com.gt.bmf.pojo.GuDong;
import com.gt.bmf.service.GuDongService;
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
import java.util.Map;

@Service("GuDongService")
public class GuDongServiceImpl extends BmfBaseServiceImpl<GuDong> implements GuDongService {
    @Value("${gf.cookie}")
    private String gfCookie;
    @Value("${gf.session}")
    private String gfSession;
	private StockMasterDao stockMasterDao;


    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
	@Qualifier("StockMasterDao")
	@Override
	public void setBmfBaseDao(BmfBaseDao<GuDong> bmfBaseDao) {
		this.bmfBaseDao = bmfBaseDao;
		this.stockMasterDao = (StockMasterDao) bmfBaseDao;

	}

    @PostConstruct
    public void init(){

    }


    /**
     * condition.loginType:
     condition.isPub:1
     condition.type:
     condition.dateFrom:2015-11-30
     condition.dateTo:2016-02-14
     condition.provinceCode:
     condition.plate:
     condition.searchType:content
     condition.keyWord:股东人数
     condition.status:3
     condition.searchRange:0
     condition.searchRangeRadio:0
     * */
    public void checkData(String pageNo) throws IOException, InterruptedException, ParseException {

        Document doc = Jsoup.connect("http://ircs.p5w.net/ircs/interaction/moreQuestionForGszz.do")
                .data("condition.dateFrom", "2016-01-15")
                .data("condition.dateTo", "2016-02-14")
                .data("pageNo",pageNo)

                .userAgent("Mozilla")
                .timeout(3000)
                .post();


        Elements elements = doc.getElementsByClass("req_box2");
        for(Element element : elements){
            Elements tds = element.getElementsByTag("td");
            String  td1[] = tds.get(1).html().split("<br>");
            String code = td1[1];
            String name = td1[0];
            Element  td3 = tds.get(3);

           String  id = td3.getElementsByTag("a").get(0).attr("href").substring(27);


            String  question = td3.text();
            String  questionDate = tds.get(6).text();
            String  answer = tds.get(11).text();
            String  answerDate = tds.get(14).text();


            GuDong obj = new GuDong();

/*
            System.out.println(id);
            System.out.println(question);
            System.out.println(questionDate);
            System.out.println(answer);
            System.out.println(answerDate);
            System.out.println(code);
            System.out.println(name);
            System.out.println("--------------------------------------------");*/
            stockMasterDao.saveOrUpdate(obj);
        }

    }

    @Override
    public PageList<GuDong> findPageData(int pageNum, int pageSize, Map<String, String> params) {
        return stockMasterDao.findPageData(pageNum, pageSize, params);
    }


}
