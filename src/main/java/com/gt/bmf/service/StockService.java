package com.gt.bmf.service;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.pojo.Stock;

import java.io.IOException;
import java.util.Map;

public interface StockService extends BmfBaseService<Stock> {
    public void saveStockCronjob() throws IOException;
}
