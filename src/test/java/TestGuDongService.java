import com.gt.bmf.BmfConstants;
import com.gt.bmf.common.page.PageList;
import com.gt.bmf.pojo.GuDong;
import com.gt.bmf.pojo.HuDong;
import com.gt.bmf.service.GuDongService;
import com.gt.bmf.service.HuDongService;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;


public class TestGuDongService {

    private static HuDongService service;
    private static GuDongService guDongService;



    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/bmf_applicationContext.xml");
        service = (HuDongService) ctx.getBean("HuDongService");
        guDongService = (GuDongService) ctx.getBean("GuDongService");
        //step 4, save data to gu dong table
       // save();
        //step 5 get gu dong  data price .maybe skip
      // savePrice();
        //step 6 get 3part data
     //   geDistGuDong();



     //   guDongService.saveRandingDataByGroup();
        System.exit(0);
	}



}
