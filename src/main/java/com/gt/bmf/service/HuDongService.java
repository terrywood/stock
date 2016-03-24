package com.gt.bmf.service;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.pojo.HuDong;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

public interface HuDongService extends BmfBaseService<HuDong> {
    public void checkHuDong(Map<String, String> params) throws IOException, InterruptedException, ParseException;
    public void checkGuDong(Map<String, String> params) throws IOException, InterruptedException, ParseException;
    public  void checkPartData() throws ParseException, IOException, InterruptedException;
    public PageList<HuDong> findPageData(int pageNum, int pageSize, Map<String, String> params);

    void updateBlackGuDong();
    void deleteHuDongLessDate(Date date);
}
