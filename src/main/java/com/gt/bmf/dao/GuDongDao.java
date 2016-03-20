package com.gt.bmf.dao;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.pojo.GuDong;

import java.util.List;
import java.util.Map;

public interface GuDongDao extends BmfBaseDao<GuDong> {
    public PageList<GuDong> findPageData(int pageNum, int pageSize, Map<String,String> params);
    public List findByGroup();
}
