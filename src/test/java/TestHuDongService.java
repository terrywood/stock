import com.gt.bmf.BmfConstants;
import com.gt.bmf.common.page.PageList;
import com.gt.bmf.pojo.HuDong;
import com.gt.bmf.service.HuDongService;
import com.gt.bmf.util.StockUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class TestHuDongService {

    private static HuDongService service;

    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    public static void main(String[] args) throws Exception {
    //   BasicConfigurator.configure();
/*
        String log4jConfPath = "D:\\Documents\\gf\\src\\main\\java\\log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);*/

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/bmf_applicationContext.xml");
        service = (HuDongService) ctx.getBean("HuDongService");
       // deleteOldData();


       //step 1
        //get3PartData();
        //step 2
        markData();

        //step 3 open browser validate date by myself
        System.exit(0);
	}

    public static void deleteOldData() throws ParseException, IOException, InterruptedException {
        Date startDate = sdf2.parse("2016-01-01");
        service.deleteHuDongLessDate(startDate);
    }
    public static void markData() throws ParseException, IOException, InterruptedException {
        Map<String,String> params  = new HashMap<String, String>();
        params.put("isGuDong","true");
        params.put("status",BmfConstants.GLOBAL_INVALID);
        PageList<HuDong> pageList = service.findPageData(1,5000,params);
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
            System.out.println("code["+obj.getCode()+"] Ren Shu["+count+"] date["+date+"]");
            if(count>0 && count!=2016 && date !=null){
                obj.setMarkCount(count);
                obj.setMarkDate(date);
                obj.setStatus(BmfConstants.GLOBAL_VALID);
                service.update(obj);
            }
        }
    }


    public static void get3PartData() throws ParseException, IOException, InterruptedException {
        Date startDate = sdf2.parse("2016-03-12");
        while (startDate.before(new Date())){
            Date endDate  = DateUtils.addDays(startDate,1);
            Map<String ,String> map = new HashMap<String,String>();
            map.put("condition.dateFrom",sdf2.format(startDate));
            map.put("condition.dateTo",sdf2.format(endDate));
            service.checkGuDong(map);
            // service.checkHuDong(map);
            startDate = DateUtils.addDays(endDate,1);;
        }
    }

}
