package com.gt.bmf.service;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.pojo.HuDong;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public interface HuDongService extends BmfBaseService<HuDong> {
    public void checkHuDong(String pageNo) throws IOException, InterruptedException, ParseException;
    public void checkGuDong(String pageNo) throws IOException, InterruptedException, ParseException;

    public PageList<HuDong> findPageData(int pageNum, int pageSize, Map<String, String> params);

}
