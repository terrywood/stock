package com.gt.bmf.web.controller.admin;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.pojo.Ranking;
import com.gt.bmf.service.RankingService;
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
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @RequestMapping("/ranking/list")
    public String list(@RequestParam(value = "pager.offset", defaultValue = "0") Integer offset,
                       @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                       @RequestParam Map<String,String> params,HttpServletRequest request) {
        Integer pageNum = offset/pageSize +1;
        PageList<Ranking> pageList =  rankingService.findPageData(pageNum,pageSize,params);
        request.setAttribute("pageList",pageList);
        return "/admin/ranking/list";
    }
    @RequestMapping("/ranking/gender")
    public String gender(HttpServletRequest request) {

        return "/admin/ranking/list";
    }


}
