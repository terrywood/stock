package com.gt.bmf.web.controller.admin;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.pojo.HuDong;
import com.gt.bmf.pojo.StockMaster;
import com.gt.bmf.service.HuDongService;
import com.gt.bmf.service.StockMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 15-5-5.
 */
@Controller
@RequestMapping("/admin")
public class HuDongController {

/*
    @Value("${jsp.admin.common.nuiresponse}")
    private String jspCommonNuiResponse;
*/
    @Autowired
    private HuDongService huDongService;

    @RequestMapping("/huDong/list")
    public String list(@RequestParam(value = "pager.offset", defaultValue = "0") Integer offset,
                       @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                       @RequestParam Map<String,String> params,HttpServletRequest request) {
        Integer pageNum = offset/pageSize +1;
        PageList<HuDong> pageList =  huDongService.findPageData(pageNum,pageSize,params);
        request.setAttribute("pageList",pageList);
        return "/admin/huDong/list";
    }


}