package com.gt.bmf.util;

import com.gt.bmf.service.StockService;
import org.apache.log4j.BasicConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TestStockService {

    private static StockService service;

    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    public static void main(String[] args) throws Exception {
    //   BasicConfigurator.configure();
/*
        String log4jConfPath = "D:\\Documents\\gf\\src\\main\\java\\log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);*/

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/bmf_applicationContext.xml");
        service = (StockService) ctx.getBean("StockService");
        service.saveStockCronjob();
        System.exit(0);
	}




}
