package com.gt.bmf.web.controller.api;

import com.gt.bmf.service.GuDongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 15-5-5.
 */
@Controller
public class ApiController {

    @Autowired
    GuDongService gfQueryLogService;


    @RequestMapping("/api/lockBuyAction")
    public void lockBuyAction(boolean action) {
      //  gfQueryLogService.setLockBuyAction(action);
    }

    @RequestMapping("/api/lockSaleAction")
    public void lockSaleAction(boolean action) {
       // gfQueryLogService.setLockSaleAction(action);
    }

}