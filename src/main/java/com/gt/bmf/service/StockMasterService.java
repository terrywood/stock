package com.gt.bmf.service;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.pojo.StockMaster;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public interface StockMasterService extends BmfBaseService<StockMaster> {
    public void checkData(String pageNo) throws IOException, InterruptedException, ParseException;

    public PageList<StockMaster> findPageData(int pageNum, int pageSize, Map<String,String> params);

}
