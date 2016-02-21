package com.gt.bmf.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 15-4-1.
 */
public class StockUtils {


    public static  int getGuDongRenShu(String str){
        Pattern pattern = Pattern.compile("([0-9]+[,]?)+");
        Matcher matcher = pattern.matcher(str);
        int renCount=0;
        while(matcher.find()){
            String value = matcher.group();
            value = StringUtils.replace(value,",","");
            int _temp = Integer.valueOf(value);
            if(_temp>renCount){
                renCount =_temp;
            }
        }
        return renCount;

    }
    public static Date getGuDongDate(String str){

        Pattern pattern = Pattern.compile("([0-9]{1,}月[0-9]{1,}日)");
        Matcher matcher = pattern.matcher(str);
        String value  = "";
        while(matcher.find()){
            value = (matcher.group());
        }
        if(StringUtils.isNotBlank(value)){
            String patterns[] = new String[]{"M月d日"};
            try {
                Date date = ( DateUtils.parseDate(value ,patterns ));
                return  DateUtils.setYears(date,2016);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    public static void main(String[] args) {

        String str ="截至2016年05月05日，公司股东总数为154,857。多谢您对公司的关注！" ;
            System.out.println("最大值：" + getGuDongDate(str));

    }

}
