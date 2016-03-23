package com.gt.bmf.dao;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.pojo.HuDong;
import com.gt.bmf.pojo.Ranking;

import java.util.Date;
import java.util.Map;

public interface RankingDao extends BmfBaseDao<Ranking> {

    PageList<Ranking> findPageData(int pageNum, int pageSize, Map<String, String> params);
}
