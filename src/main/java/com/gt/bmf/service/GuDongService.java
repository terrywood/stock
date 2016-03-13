package com.gt.bmf.service;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.pojo.GuDong;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public interface GuDongService extends BmfBaseService<GuDong> {
    public void checkData(String pageNo) throws IOException, InterruptedException, ParseException;

    public PageList<GuDong> findPageData(int pageNum, int pageSize, Map<String,String> params);

}
