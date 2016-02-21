package com.gt.bmf.dao.impl;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.dao.StockMasterDao;
import com.gt.bmf.pojo.GuDong;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 15-8-24.
 */
@Repository("StockMasterDao")
public class StockMasterDaoImpl extends BmfBaseDaoImpl<GuDong> implements StockMasterDao {

    @Override
    public PageList<GuDong> findPageData(int pageNum, int pageSize, Map<String, String> params) {
        String hql ="from GuDong where 1=1";
        List<Object> paramList = new ArrayList<Object>();
        if(StringUtils.isNotBlank(params.get("code"))) {
            hql+=" and code = ?";
            paramList.add(params.get("code").trim());
        }
        if(StringUtils.isNotBlank(params.get("keyword"))) {
            hql+=" and question like ?";
            paramList.add("%"+params.get("keyword").trim()+"%");
        }
        hql+=" order by answerDate desc";
        return  super.findPageData(hql,pageNum,pageSize,paramList.toArray());

    }
}
