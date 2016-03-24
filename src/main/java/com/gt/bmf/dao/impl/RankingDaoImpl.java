package com.gt.bmf.dao.impl;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.dao.RankingDao;
import com.gt.bmf.pojo.Ranking;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by Administrator on 15-8-24.
 */
@Repository("RankingDao")
public class RankingDaoImpl extends BmfBaseDaoImpl<Ranking> implements RankingDao {

    @Override
    public void saveOrUpdateAll(Collection<?> list) {
            int temp = 0;
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                if (temp % 40 == 0) {
                    getSession().flush();
                    getSession().clear();
                }
                getSession().save(it.next());
                temp++;
            }
    }

    public List findByGroup() {
        String sql ="select  code, group_concat(date) as dd ,group_concat(mark_count) as cc from gu_dong where date>= date_format('2015-12-31','%Y%m%d') group by code";
        SQLQuery query = getSession().createSQLQuery(sql);
        //query.addEntity(StockGroup.class);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public PageList<Ranking> findPageData(int pageNum, int pageSize, Map<String, String> params) {
        return super.findPageData("from Ranking order by change1 asc",pageNum,pageSize);
    }
}
