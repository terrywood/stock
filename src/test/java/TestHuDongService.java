import com.gt.bmf.service.HuDongService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        Date startDate = sdf2.parse("2015-10-30");
        while (startDate.before(new Date())){
            Date endDate  = DateUtils.addDays(startDate,1);
            Map<String ,String> map = new HashMap<String,String>();
            map.put("condition.dateFrom",sdf2.format(startDate));
            map.put("condition.dateTo",sdf2.format(endDate));
            service.checkHuDong(map);
            startDate = DateUtils.addDays(endDate,1);;
        }


        System.exit(0);
	}



}
