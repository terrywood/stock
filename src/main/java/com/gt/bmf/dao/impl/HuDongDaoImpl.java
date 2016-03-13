package com.gt.bmf.dao.impl;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.dao.HuDongDao;
import com.gt.bmf.pojo.HuDong;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 15-8-24.
 */
@Repository("HuDongDao")
public class HuDongDaoImpl extends BmfBaseDaoImpl<HuDong> implements HuDongDao {

    @Override
    public PageList<HuDong> findPageData(int pageNum, int pageSize, Map<String, String> params) {
        String hql ="from HuDong where 1=1";
        List<Object> paramList = new ArrayList<Object>();
        if(StringUtils.isNotBlank(params.get("code"))) {
            hql+=" and code = ?";
            paramList.add(params.get("code").trim());
        }
        if(StringUtils.isNotBlank(params.get("status"))) {
            hql+=" and status = ?";
            paramList.add(params.get("status").trim());
        }
        if(StringUtils.equals("true",params.get("isGuDong"))) {
            hql+=" and isGuDong = ?";
            paramList.add(true);
        }
        if(StringUtils.isNotBlank(params.get("keyword"))) {
            hql+=" and( question like ? or answer like ?)";
            paramList.add("%"+params.get("keyword").trim()+"%");
            paramList.add("%"+params.get("keyword").trim()+"%");
        }
        hql+=" order by answerDate desc, id desc";
        return  super.findPageData(hql,pageNum,pageSize,paramList.toArray());

    }

    @Override
    public void deleteHuDongLessDate(Date date) {
        super.executeByHQL("delete HuDong where  answerDate < ?",date);
    }
}
