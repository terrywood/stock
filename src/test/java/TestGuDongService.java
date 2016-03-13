import com.gt.bmf.common.page.PageList;
import com.gt.bmf.pojo.HuDong;
import com.gt.bmf.service.HuDongService;
import com.gt.bmf.util.StockUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class TestGuDongService {

    private static HuDongService service;

    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/bmf_applicationContext.xml");
        service = (HuDongService) ctx.getBean("HuDongService");
        Map<String,String> params  = new HashMap<String, String>();
        params.put("isGuDong","true");
        PageList<HuDong> pageList = service.findPageData(1,5000,params);
        for(HuDong obj : pageList.getData()){
          int count =  StockUtils.getGuDongRenShu(obj.getAnswer());
          Date date =   StockUtils.getGuDongDate(obj.getAnswer());
            if(date==null){
                date =   StockUtils.getGuDongDate(obj.getQuestion());
            }
          System.out.println("code["+obj.getCode()+"] Ren Shu["+count+"] date["+date+"]");
          if(count>0 && count!=2016 && date !=null){

          }
        }
        System.exit(0);
	}



}
