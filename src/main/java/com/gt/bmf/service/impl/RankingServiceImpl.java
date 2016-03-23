package com.gt.bmf.service.impl;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.dao.BmfBaseDao;
import com.gt.bmf.dao.RankingDao;
import com.gt.bmf.dao.RankingDao;
import com.gt.bmf.pojo.Ranking;
import com.gt.bmf.pojo.Ranking;
import com.gt.bmf.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("RankingService")
public class RankingServiceImpl extends BmfBaseServiceImpl<Ranking> implements RankingService {

	private RankingDao rankingDaoDao;


    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
	@Qualifier("RankingDao")
	@Override
	public void setBmfBaseDao(BmfBaseDao<Ranking> bmfBaseDao) {
		this.bmfBaseDao = bmfBaseDao;
		this.rankingDaoDao = (RankingDao) bmfBaseDao;

	}

    @PostConstruct
    public void init(){

    }



    @Override
    public PageList<Ranking> findPageData(int pageNum, int pageSize, Map<String, String> params) {
        return rankingDaoDao.findPageData(pageNum, pageSize, params);
    }

}
