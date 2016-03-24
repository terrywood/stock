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
       // markData();

        //step 3 open browser validate date by myself
        System.exit(0);
	}

    public static void deleteOldData() throws ParseException, IOException, InterruptedException {
        Date startDate = sdf2.parse("2016-01-01");
        service.deleteHuDongLessDate(startDate);
    }


}
