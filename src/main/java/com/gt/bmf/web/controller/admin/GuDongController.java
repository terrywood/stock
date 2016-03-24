package com.gt.bmf.web.controller.admin;

import com.gt.bmf.common.page.PageList;
import com.gt.bmf.pojo.GuDong;
import com.gt.bmf.service.GuDongService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 15-5-5.
 */
@Controller
@RequestMapping("/admin")
public class GuDongController {

    @Autowired
    private GuDongService guDongService;

    @RequestMapping("/guDong/list")
    public String list(@RequestParam(value = "pager.offset", defaultValue = "0") Integer offset,
                       @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                       @RequestParam Map<String,String> params,HttpServletRequest request) {
        Integer pageNum = offset/pageSize +1;
        PageList<GuDong> pageList =  guDongService.findPageData(pageNum,pageSize,params);
        request.setAttribute("pageList",pageList);
        return "/admin/guDong/list";
    }
    @RequestMapping("/guDong/save")
    public String save( GuDong model,HttpServletRequest request) throws ParseException, InvocationTargetException, IllegalAccessException {
        GuDong obj = guDongService.get(model.getId());
        if(obj!=null){
            BeanUtils.copyProperties(obj,model);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse( request.getParameter("date1"));
            obj.setDate(date);
            guDongService.saveOrUpdate(obj);
        }else{
            guDongService.saveOrUpdate(model);
        }

        return "redirect:list.do?code="+obj.getCode();
    }
    @RequestMapping("/guDong/{id}")
    public String detail(
                         HttpServletRequest request,
                         @PathVariable("id") String id) throws ParseException {
        if (StringUtils.isNotBlank(id)) {
            request.setAttribute("obj",guDongService.get(id));
        }
        return "/admin/guDong/edit";
    }
    @RequestMapping("/guDong/delete")
    public String delete(
                         HttpServletRequest request,
                          String id) throws ParseException {
        GuDong obj =  guDongService.get(id);
        String code = obj.getCode();
        guDongService.deleteByPK(id);
        return "redirect:list.do?code="+code;
    }
    @RequestMapping("/guDong/gender")
    public String gender() throws ParseException {
        guDongService.saveRandingDataByGroup();
        return "redirect:stock/admin/ranking/list.do";
    }
    @RequestMapping("/guDong/report")
    public String report( HttpServletRequest request) throws Exception {
        guDongService.saveData();
        return "redirect:list.do?code=";
    }
    @RequestMapping("/guDong")
    public String add( HttpServletRequest request) throws ParseException {
        return detail(request,null);
    }
/*
    @RequestMapping("/guDong/save")
    public String save( @RequestParam Map<String,String> params,HttpServletRequest request) throws ParseException {
        GuDong model = new GuDong();

        guDongService.get()
        Double markCount = Double.valueOf( params.get("markCount"));
        Double price = Double.valueOf( params.get("price"));
        Double volume = Double.valueOf( params.get("volume"));
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse( params.get("date"));
        guDongService.saveOrUpdate(model);
        request.setAttribute("pageList",pageList);
        return "/admin/guDong/list";
    }
*/


}
