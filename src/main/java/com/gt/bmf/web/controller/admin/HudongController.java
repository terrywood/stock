package com.gt.bmf.web.controller.admin;

import com.alibaba.druid.support.json.JSONUtils;
import com.gt.bmf.BmfConstants;
import com.gt.bmf.common.page.PageList;
import com.gt.bmf.pojo.Black;
import com.gt.bmf.pojo.HuDong;
import com.gt.bmf.service.HuDongService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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

    @RequestMapping("/guDong/status")
    public String status( HttpServletRequest request) throws Exception {
        huDongService.updateAllStatus();
        return "redirect:list.do";
    }
    @RequestMapping("/huDong/3part")
    public String part() throws ParseException, InterruptedException, IOException {
        huDongService.checkPartData();
        return "redirect:list.do";
    }
    @RequestMapping("/huDong/list")
    public String list(@RequestParam(value = "pager.offset", defaultValue = "0") Integer offset,
                       @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                       @RequestParam Map<String,String> params,HttpServletRequest request) {
        Integer pageNum = offset/pageSize +1;
        PageList<HuDong> pageList =  huDongService.findPageData(pageNum,pageSize,params);
        request.setAttribute("pageList",pageList);
        request.setAttribute("qs",request.getQueryString());
        return "/admin/huDong/list";
    }

    @RequestMapping("/huDong/isGuDong")
    public String list(
                       @RequestParam(value = "id", defaultValue = "0") Long id,
                       @RequestParam(value = "cancelId" ,required = false) Long[] cancelId,
                       @RequestParam(value = "a", defaultValue = "false") boolean a,
                       @RequestParam(value = "qs", defaultValue = "") String qs,
                      HttpServletRequest request) {
        System.out.println("isGuDong["+a+"]");
        if(id>0){
            HuDong obj =  huDongService.get(id);
            obj.setGuDong(a);
            huDongService.saveOrUpdate(obj);
        }else{
            for(Long _id : cancelId){
                HuDong obj =  huDongService.get(_id);
                obj.setGuDong(a);
                huDongService.saveOrUpdate(obj);
            }
        }

        return "redirect:list.do"+qs;
    }
    @RequestMapping("/huDong/mark")
    public String list( Long[] markId,
                        Integer[] markCount,
                        String[] markDate,
                        @RequestParam(value = "qs", defaultValue = "") String qs,
                        @RequestParam(value = "cancelId" ,required = false) Long[] cancelId,
                      HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0;i<markId.length;i++){
            Long id = markId[i];
            Integer count = markCount[i];
            String date = markDate[i];
            if(count>0 && StringUtils.isNotBlank(date)){
                try {
                    HuDong obj =  huDongService.get(id);
                    obj.setMarkCount(count);
                    obj.setMarkDate(sdf.parse(date));
                    obj.setStatus(BmfConstants.GLOBAL_VALID);
                    huDongService.saveOrUpdate(obj);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        if(cancelId!=null){
            List<Black> list = new ArrayList<Black>();
            for(Long _id : cancelId){
                HuDong obj =  huDongService.get(_id);
                obj.setGuDong(false);
                huDongService.saveOrUpdate(obj);
                Black black  = new Black(obj.getCode());
                if(!list.contains(black))
                list.add(black);
            }
            huDongService.saveOrUpdateAll(list);
            huDongService.updateBlackGuDong();
        }

        return "redirect:list.do"+qs;
    }


}
