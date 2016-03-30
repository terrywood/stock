package com.gt.bmf.web.controller.admin;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.pojo.GuDong;
import com.gt.bmf.pojo.Ranking;
import com.gt.bmf.service.GuDongService;
import com.gt.bmf.service.RankingService;
import org.codehaus.jackson.map.ObjectMapper;
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
    @Autowired
    private GuDongService guDongService;

    @RequestMapping("/ranking/list")
    public String list(@RequestParam(value = "pager.offset", defaultValue = "0") Integer offset,
                       @RequestParam(value = "pageSize", defaultValue = "50") Integer pageSize,
                       @RequestParam Map<String,String> params,HttpServletRequest request) {
        Integer pageNum = offset/pageSize +1;
        PageList<Ranking> pageList =  rankingService.findPageData(pageNum,pageSize,params);
        request.setAttribute("pageList",pageList);
        return "/admin/ranking/list";
    }

    @RequestMapping("/ranking/chart")
    public String gender(
            @RequestParam Map<String,String> params,
            HttpServletRequest request) {
        PageList<GuDong> pageList =  guDongService.findPageData(1,100,params);
        request.setAttribute("list",pageList.getData());
        return "/admin/ranking/chart";
    }


}
