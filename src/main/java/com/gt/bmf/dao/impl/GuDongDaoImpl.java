package com.gt.bmf.dao.impl;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.dao.GuDongDao;
import com.gt.bmf.pojo.GuDong;
import com.gt.bmf.vo.StockGroup;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 15-8-24.
 */
@Repository("GuDongDao")
public class GuDongDaoImpl extends BmfBaseDaoImpl<GuDong> implements GuDongDao {

    @Override
    public PageList<GuDong> findPageData(int pageNum, int pageSize, Map<String, String> params) {
        String hql ="from GuDong where 1=1";
        List<Object> paramList = new ArrayList<Object>();
        if(StringUtils.isNotBlank(params.get("code"))) {
            hql+=" and code = ?";
            paramList.add(params.get("code").trim());
        }
       if(StringUtils.isNotBlank(params.get("price"))) {
            hql+=" and price = ?";
            paramList.add(Double.valueOf(params.get("price")));
        }
        hql+=" order by id desc";
        return  super.findPageData(hql,pageNum,pageSize,paramList.toArray());

    }

    @Override
    public List findByGroup() {
        String sql ="select  code, group_concat(date) as dd ,group_concat(mark_count) as cc from gu_dong where date>= date_format('2015-12-31','%Y%m%d') group by code";
        SQLQuery query = getSession().createSQLQuery(sql);
        //query.addEntity(StockGroup.class);
        List<Object[]> list = query.list();
        for(Object[] obj : list){
            System.out.println(obj[1]);
        }
        return null;
    }
}
