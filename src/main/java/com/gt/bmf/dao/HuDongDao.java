package com.gt.bmf.dao;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.pojo.HuDong;

import java.util.Date;
import java.util.Map;

public interface HuDongDao extends BmfBaseDao<HuDong> {
    public PageList<HuDong> findPageData(int pageNum, int pageSize, Map<String, String> params);
    void deleteHuDongLessDate(Date date);
}
