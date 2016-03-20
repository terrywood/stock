import com.alibaba.druid.support.json.JSONUtils;
import com.gt.bmf.BmfConstants;
import com.gt.bmf.common.page.PageList;
import com.gt.bmf.pojo.GuDong;
import com.gt.bmf.pojo.HuDong;
import com.gt.bmf.service.GuDongService;
import com.gt.bmf.service.HuDongService;
import com.gt.bmf.util.StockUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.util.JavaScriptUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;


public class TestGuDongService {

    private static HuDongService service;
    private static GuDongService guDongService;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");

    public static void savePrice() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> params  = new HashMap<String, String>();
        params.put("price","0");
        PageList<GuDong> pageList = guDongService.findPageData(1,10000,params);
        for(GuDong obj : pageList.getData()){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(obj.getDate());
            int i  = calendar.get(Calendar.DAY_OF_WEEK);
            Double price =0d;
            Double volume =0d;

            if(i==1 ){
                //   System.out.println("code["+obj.getCode()+"] Ren Shu["+count+"] date["+date+"]");
                calendar.set(Calendar.DATE,-2);
            }else if( i==7){
                calendar.set(Calendar.DATE,-1);
            }else{

               // Map<String,Object> map2 = (Map)((List)map.get("rows")).get(0);
            }
            Date date = calendar.getTime();
            String httpUrl = "http://apis.baidu.com/tehir/stockassistant/hist_date";
            String httpArg = "date="+sdf.format(date)+"&code="+obj.getCode();
            String jsonResult = request(httpUrl, httpArg);
            //  System.out.println(jsonResult);
            Map<String,Object> map =  objectMapper.readValue(jsonResult,Map.class);
            List list =  (List)map.get("rows");
            if(list.size() > 0){
                Map<String,Object> detail =  (Map)list.get(0);
                price = Double.valueOf ( detail.get("close").toString());
                volume =Double.valueOf (detail.get("volume").toString());
                obj.setPrice(price);
                obj.setVolume(volume);
                guDongService.saveOrUpdate(obj);
            }

        }





    }




    public static String request(String httpUrl, String httpArg) {
        System.out.println("httpArg["+httpArg+"]");
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey",  "9ccd8312fdcb069a30406a895770dc07");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void save() throws Exception {
        Date startDate = sdf2.parse("20150116");
        Map<String,String> params  = new HashMap<String, String>();
        params.put("isGuDong","true");
        params.put("status", BmfConstants.GLOBAL_VALID);
        PageList<HuDong> pageList = service.findPageData(1,100000,params);
        for(HuDong obj : pageList.getData()){
          /*  int count =  StockUtils.getGuDongRenShu(obj.getAnswer());
            if(count <=100 || count==2015|| count==2016|| count==2014){
                continue;
            }*/

         /*   Date date =   StockUtils.getGuDongDate(obj.getAnswer());
            if(date==null){
                date =   StockUtils.getGuDongDate(obj.getQuestion());
            }
            if(date==null){
                continue;
            }
            if(date.after(new Date())){
                date = DateUtils.addYears(date,-1);
            }
            if(date.before(startDate)){
                continue;
            }*/

            Date date = obj.getMarkDate();
            int count  =obj.getMarkCount();
            //
            obj.setStatus(BmfConstants.GLOBAL_YES);
            service.update(obj);
            GuDong  entity  = new GuDong();
            String id = sdf2.format(date)+obj.getCode();
            entity.setName(obj.getName());
            entity.setCode(obj.getCode());
            entity.setDate(date);
            entity.setMarkCount(count);
            entity.setId(id);
            entity.setPrice(0d);
            entity.setVolume(0d);
            guDongService.saveOrUpdate(entity);
            //guDongService.merge(entity);
        }
    }

    public static void geDistGuDong() throws Exception {
       List<GuDong> list = guDongService.findDisGuDong();
        for(GuDong obj: list){
            saveGuDong(obj.getCode(),obj.getName());
        }
    }
    public static void saveGuDong(String code,String name)  {

        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.djstg.com/stock/"+code+"/xml/gdyj/gdhs.xml")
                    .userAgent("Mozilla")
                    .timeout(3000)
                    .get();
            Elements elements = doc.getElementsByTag("dataset");
            Elements set =  elements.get(0).getElementsByTag("set");
            Elements set2 =  elements.get(1).getElementsByTag("set");
            List<GuDong> array = new ArrayList<GuDong>();
            for(int i=0;i<set.size();i++){
                Element ele  =set.get(i);
                Element ele2  =set2.get(i);
                int count  = Integer.valueOf(ele.attr("value"));
                String _date = ele.attr("tooltext").split(",")[1];
                Date date = sdf.parse(_date);
                String value  = ele2.attr("value");
                Double price =0d;
                if(StringUtils.isNotBlank(value)){
                    price  =Double .valueOf(value);
                }
                String id = StringUtils.remove(_date,"-") +code;
                GuDong  entity = guDongService.get(id);
                if(entity ==null) {
                    entity  = new GuDong();
                }
               // GuDong entity  = new GuDong();
                entity.setName(name);
                entity.setCode(code);
                entity.setDate(date);
                entity.setMarkCount(count);
                entity.setId(id);
                entity.setPrice(price);
                //System.out.println(entity);
                // entity.setVolume(0d);
                array.add(entity);
            }
            guDongService.saveOrUpdateAll(array);
        } catch (Exception e) {
            System.out.println("error code["+code+"]");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/bmf_applicationContext.xml");
        service = (HuDongService) ctx.getBean("HuDongService");
        guDongService = (GuDongService) ctx.getBean("GuDongService");
        //step 4, save data to gu dong table
       // save();
        //step 5 get gu dong  data price .maybe skip
      //  savePrice();
        //step 6 get 3part data
     //   geDistGuDong();



        guDongService.findByGroup();
        System.exit(0);
	}



}
