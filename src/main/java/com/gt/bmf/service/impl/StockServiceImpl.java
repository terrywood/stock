package com.gt.bmf.service.impl;

import com.gt.bmf.dao.BmfBaseDao;
import com.gt.bmf.dao.StockDao;
import com.gt.bmf.pojo.Stock;
import com.gt.bmf.service.StockService;
import com.gt.bmf.util.HttpUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("StockService")
public class StockServiceImpl extends BmfBaseServiceImpl<Stock> implements StockService {

	private StockDao stockDaoDao;


    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
	@Qualifier("StockDao")
	@Override
	public void setBmfBaseDao(BmfBaseDao<Stock> bmfBaseDao) {
		this.bmfBaseDao = bmfBaseDao;
		this.stockDaoDao = (StockDao) bmfBaseDao;

	}

    @PostConstruct
    public void init(){

    }

    String httpUrl = "http://apis.baidu.com/tehir/stockassistant/stocklist";
    public void saveStockCronjob() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        for(int p =1 ;p<15;p++){
            String httpArg = "page="+p+"&rows=250";
            String jsonResult = HttpUtils.request(httpUrl, httpArg);
            Map<String,Object> map =  objectMapper.readValue(jsonResult,Map.class);
            List list =  (List)map.get("rows");
            for(int i=0;i<list.size();i++){
                Map jsonObject =(Map)list.get(i);
                String code = jsonObject.get("code").toString();
                String name = jsonObject.get("name").toString();


                Stock obj = new Stock();
                obj.setId(code);
                obj.setName(name);
                stockDaoDao.save(obj);
            }

        }
    }


}
