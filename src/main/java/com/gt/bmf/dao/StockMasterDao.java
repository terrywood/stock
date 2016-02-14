package com.gt.bmf.dao;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.pojo.StockMaster;

import java.util.Map;

public interface StockMasterDao extends BmfBaseDao<StockMaster> {
    public PageList<StockMaster> findPageData(int pageNum, int pageSize, Map<String,String> params);
}
