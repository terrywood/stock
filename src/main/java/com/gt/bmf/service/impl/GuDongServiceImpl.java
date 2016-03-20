package com.gt.bmf.service.impl;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.dao.BmfBaseDao;
import com.gt.bmf.dao.GuDongDao;
import com.gt.bmf.pojo.GuDong;
import com.gt.bmf.service.GuDongService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service("GuDongService")
public class GuDongServiceImpl extends BmfBaseServiceImpl<GuDong> implements GuDongService {
    @Value("${gf.cookie}")
    private String gfCookie;
    @Value("${gf.session}")
    private String gfSession;
	private GuDongDao guDongDaoDao;


    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
	@Qualifier("GuDongDao")
	@Override
	public void setBmfBaseDao(BmfBaseDao<GuDong> bmfBaseDao) {
		this.bmfBaseDao = bmfBaseDao;
		this.guDongDaoDao = (GuDongDao) bmfBaseDao;

	}

    @PostConstruct
    public void init(){

    }



    @Override
    public PageList<GuDong> findPageData(int pageNum, int pageSize, Map<String, String> params) {
        return guDongDaoDao.findPageData(pageNum, pageSize, params);
    }

    @Override
    public List findDisGuDong() {
        return guDongDaoDao.find("from GuDong group by code");
    }


}
