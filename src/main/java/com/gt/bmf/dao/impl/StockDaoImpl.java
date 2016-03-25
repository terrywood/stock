package com.gt.bmf.dao.impl;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.dao.StockDao;
import com.gt.bmf.pojo.Stock;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 15-8-24.
 */
@Repository("StockDao")
public class StockDaoImpl extends BmfBaseDaoImpl<Stock> implements StockDao {

}
