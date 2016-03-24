package com.gt.bmf.service.impl;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.dao.BmfBaseDao;
import com.gt.bmf.dao.GuDongDao;
import com.gt.bmf.dao.RankingDao;
import com.gt.bmf.pojo.GuDong;
import com.gt.bmf.pojo.Ranking;
import com.gt.bmf.service.GuDongService;
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

@Service("GuDongService")
public class GuDongServiceImpl extends BmfBaseServiceImpl<GuDong> implements GuDongService {
    @Value("${gf.cookie}")
    private String gfCookie;
    @Value("${gf.session}")
    private String gfSession;
	private GuDongDao guDongDaoDao;
    @Autowired
	private RankingDao rankingDao;


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

    public void saveRandingDataByGroup(){
        List<Object[]> list = guDongDaoDao.findByGroup();
        List<Ranking> rankingList = new ArrayList<>();
        for(Object[] obj : list){
            String cc[]  = obj[2].toString().split(",");
            if(cc.length==1){
                continue;
            }
           // System.out.println(cc[0]+","+cc[1]);
            String code = obj[0].toString();
            String dd[]  = obj[1].toString().split(",");
            Ranking ranking = new Ranking();
            if(cc.length==2){
                try {
                    ranking.setCode(code);
                    ranking.setDate1(sdf.parse(dd[1]));
                    ranking.setDate2(sdf.parse(dd[0]));
                    Double c1 = Double.valueOf(cc[1]);
                    Double c2 = Double.valueOf(cc[0]);
                    Double change1 = (c1-c2)/c2;
                    ranking.setCount1(c1);
                    ranking.setCount2(c2);
                    ranking.setChange1(change1);
                    rankingList.add(ranking);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }else{
                try {
                    int len = cc.length;
                    ranking.setCode(code);
                    ranking.setDate1(sdf.parse(dd[len-1]));
                    ranking.setDate2(sdf.parse(dd[len-2]));
                    ranking.setDate3(sdf.parse(dd[len-3]));

                    Double c1 = Double.valueOf(cc[len-1]);
                    Double c2 = Double.valueOf(cc[len-2]);
                    Double c3 = Double.valueOf(cc[len-3]);
                    Double change1 = (c1-c2)/c2;
                    Double change2 = (c2-c3)/c3;
                    ranking.setChange1(change1);
                    ranking.setChange2(change2);

                    ranking.setCount1(c1);
                    ranking.setCount2(c2);
                    ranking.setCount3(c3);
                    rankingList.add(ranking);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //rankingDao.saveOrUpdate(ranking);
        }
        rankingDao.executeByHQL("delete from Ranking");
        rankingDao.saveOrUpdateAll(rankingList);
    }

    @Override
    public void deleteByPK(String id) {
        guDongDaoDao.executeByHQL("delete from GuDong where id=?",id);
    }
}
