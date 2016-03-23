package com.gt.bmf.service;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.pojo.Ranking;

import java.util.List;
import java.util.Map;

public interface RankingService extends BmfBaseService<Ranking> {

    public PageList<Ranking> findPageData(int pageNum, int pageSize, Map<String, String> params);
}
