package com.gt.bmf.service.impl;

import com.gt.bmf.BmfConstants;
import com.gt.bmf.common.page.PageList;
import com.gt.bmf.dao.BmfBaseDao;
import com.gt.bmf.dao.GuDongDao;
import com.gt.bmf.dao.HuDongDao;
import com.gt.bmf.dao.RankingDao;
import com.gt.bmf.pojo.GuDong;
import com.gt.bmf.pojo.HuDong;
import com.gt.bmf.pojo.Ranking;
import com.gt.bmf.service.GuDongService;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("GuDongService")
public class GuDongServiceImpl extends BmfBaseServiceImpl<GuDong> implements GuDongService {

	private GuDongDao guDongDaoDao;
    @Autowired
	private RankingDao rankingDao;
    @Autowired
	private HuDongDao huDongDao;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
    @Autowired
	@Qualifier("GuDongDao")
	@Override
	public void setBmfBaseDao(BmfBaseDao<GuDong> bmfBaseDao) {
		this.bmfBaseDao = bmfBaseDao;
		this.guDongDaoDao = (GuDongDao) bmfBaseDao;

	}

    @PostConstruct
    public void init(){

    }


    public  void savePrice() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> params  = new HashMap<String, String>();
        params.put("price","0");
        PageList<GuDong> pageList = findPageData(1,10000,params);
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

            Map<String,Object> map =  objectMapper.readValue(jsonResult,Map.class);
            List list =  (List)map.get("rows");
            if(list.size() > 0){
                Map<String,Object> detail =  (Map)list.get(0);
                price = Double.valueOf ( detail.get("close").toString());
                volume =Double.valueOf (detail.get("volume").toString());
                obj.setPrice(price);
                obj.setVolume(volume);
                update(obj);
            }

        }

        List<GuDong> list = findDisGuDong();
        for(GuDong obj: list){
            saveGuDong(obj.getCode(),obj.getName());
        }


    }




    public  String request(String httpUrl, String httpArg) {
        // System.out.println("httpArg["+httpArg+"]");
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


    public void saveData() throws Exception {
        Map<String,String> params  = new HashMap<String, String>();
        params.put("isGuDong","true");
        params.put("status", BmfConstants.GLOBAL_VALID);
        PageList<HuDong> pageList = huDongDao.findPageData(1,100000,params);
        for(HuDong obj : pageList.getData()){
            Date date = obj.getMarkDate();
            int count  =obj.getMarkCount();
            obj.setStatus(BmfConstants.GLOBAL_YES);
            huDongDao.update(obj);

            if(count <=100 || count==2015|| count==2016|| count==2014){
                continue;
            }

            GuDong  entity  = new GuDong();
            String id = sdf2.format(date)+obj.getCode();
            entity.setName(obj.getName());
            entity.setCode(obj.getCode());
            entity.setDate(date);
            entity.setMarkCount(count);
            entity.setId(id);
            entity.setPrice(0d);
            entity.setVolume(0d);
            saveOrUpdate(entity);
            //guDongService.merge(entity);
        }
        savePrice();
    }

/*    public  void geDistGuDong() throws Exception {
        List<GuDong> list = findDisGuDong();
        for(GuDong obj: list){
            saveGuDong(obj.getCode(),obj.getName());
        }
    }*/
    public  void saveGuDong(String code,String name)  {

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
                GuDong  entity = get(id);
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
            saveOrUpdateAll(array);
        } catch (Exception e) {
            System.out.println("error code["+code+"]");
            e.printStackTrace();
        }

    }

    @Override
    public PageList<GuDong> findPageData(int pageNum, int pageSize, Map<String, String> params) {
        return guDongDaoDao.findPageData(pageNum, pageSize, params);
    }

    @Override
    public List findDisGuDong() {
        return guDongDaoDao.find("from GuDong group by code");
    }

    public void saveRandingDataByGroup(){
        List<Object[]> list = guDongDaoDao.findByGroup();
        List<Ranking> rankingList = new ArrayList<>();
        for(Object[] obj : list){
            String cc[]  = obj[2].toString().split(",");
            if(cc.length==1){
                continue;
            }
           // System.out.println(cc[0]+","+cc[1]);
            String code = obj[0].toString();
            String dd[]  = obj[1].toString().split(",");
            Ranking ranking = new Ranking();
            if(cc.length==2){
                try {
                    ranking.setCode(code);
                    ranking.setDate1(simpleDateFormat.parse(dd[1]));
                    ranking.setDate2(simpleDateFormat.parse(dd[0]));
                    Double c1 = Double.valueOf(cc[1]);
                    Double c2 = Double.valueOf(cc[0]);
                    Double change1 = (c1-c2)/c2;
                    ranking.setCount1(c1);
                    ranking.setCount2(c2);
                    ranking.setChange1(change1);
                    rankingList.add(ranking);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }else{
                try {
                    int len = cc.length;
                    ranking.setCode(code);
                    ranking.setDate1(simpleDateFormat.parse(dd[len-1]));
                    ranking.setDate2(simpleDateFormat.parse(dd[len-2]));
                    ranking.setDate3(simpleDateFormat.parse(dd[len-3]));

                    Double c1 = Double.valueOf(cc[len-1]);
                    Double c2 = Double.valueOf(cc[len-2]);
                    Double c3 = Double.valueOf(cc[len-3]);
                    Double change1 = (c1-c2)/c2;
                    Double change2 = (c2-c3)/c3;
                    ranking.setChange1(change1);
                    ranking.setChange2(change2);

                    ranking.setCount1(c1);
                    ranking.setCount2(c2);
                    ranking.setCount3(c3);
                    rankingList.add(ranking);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //rankingDao.saveOrUpdate(ranking);
        }
        rankingDao.executeByHQL("delete from Ranking");
        rankingDao.saveOrUpdateAll(rankingList);
    }

    @Override
    public void deleteByPK(String id) {
        guDongDaoDao.executeByHQL("delete from GuDong where id=?",id);
    }
}
