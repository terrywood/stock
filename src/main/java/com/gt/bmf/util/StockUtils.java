package com.gt.bmf.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class StockUtils {


    public static  int getGuDongRenShu(String str){
        str = StringUtils.replace(str," ","");
        str = StringUtils.replace(str,"一","1");
        str = StringUtils.replace(str,"二","2");
        str = StringUtils.replace(str,"两","2");
        str = StringUtils.replace(str,"三","3");
        str = StringUtils.replace(str,"四","4");
        str = StringUtils.replace(str,"五","5");
        str = StringUtils.replace(str,"六","6");
        str = StringUtils.replace(str,"七","7");
        str = StringUtils.replace(str,"八","8");
        str = StringUtils.replace(str,"九","9");
        //str = StringUtils.replace(str,"十","10");
        int renCount=0;
        Pattern pattern3 = Pattern.compile("([0-9]+[.]?)+千+");
        Matcher matcher3 = pattern3.matcher(str);
        while(matcher3.find()){
            String value = matcher3.group();
            value = StringUtils.replace(value,",","");
            value = StringUtils.replace(value,"千","");
            Double _temp = Double.valueOf(value)*1000D;
            renCount  = _temp.intValue();
        }
        Pattern pattern2 = Pattern.compile("([0-9]+[.]?)+万+");
        Matcher matcher2 = pattern2.matcher(str);
        while(matcher2.find()){
            String value = matcher2.group();
            value = StringUtils.replace(value,",","");
            value = StringUtils.replace(value,"万","");
           // System.out.println(value);
            Double _temp = Double.valueOf(value)*10000D;
            renCount  += _temp.intValue();
        }

        if(renCount>0) return renCount;
        Pattern pattern = Pattern.compile("([0-9]+[,]?)+万?");
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            String value = matcher.group();
            value = StringUtils.replace(value,",","");
            value = StringUtils.replace(value,"万","0000");
           try{
               int _temp = Integer.valueOf(value);
               if(_temp>renCount){
                   renCount =_temp;
               }
           }catch (Exception e){
               e.printStackTrace();
           }

        }
        return renCount;

    }
    public static Date getGuDongDate(String str){
        str = StringUtils.replace(str," ","");
        str = StringUtils.replace(str,"十五","15");
        str = StringUtils.replace(str,"3月中旬","3月15日");
        str = StringUtils.replace(str,"2月十五日","2月15日");
        str = StringUtils.replace(str,"2月中旬","2月15日");
        str = StringUtils.replace(str,"一月29日","1月29日");
        str = StringUtils.replace(str,"1月末","1月29日");
        str = StringUtils.replace(str,"2.29","2月29日");

        {
            Pattern pattern = Pattern.compile("[0-9]{4}年[0-9]{1,}月[0-9]{1,}日");
            Matcher matcher = pattern.matcher(str);
            String value = "";

            while (matcher.find()) {
                value = (matcher.group());
            }
           //System.out.println("value:"+value);
            if (StringUtils.isNotBlank(value)) {
                String patterns[] = new String[]{"yyyy年M月d日"};
                try {
                    Date date = (DateUtils.parseDate(value, patterns));
                    return date;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        {
            Pattern pattern = Pattern.compile("([0-9]{1,}月[0-9]{1,}日)");
            Matcher matcher = pattern.matcher(str);
            String value = "";
            while (matcher.find()) {
                value = (matcher.group());
            }
            //System.out.println("value222:"+value);
            if (StringUtils.isNotBlank(value)) {
                String patterns[] = new String[]{"M月d日"};
                try {
                    Date date = (DateUtils.parseDate(value, patterns));
                    return DateUtils.setYears(date, 2016);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }



    public static void main(String[] args) {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String str ="截至2016年2月15日，公司股东总户数约为两万一千余户。" ;
       // System.out.println("：" + sdf2.format(getGuDongDate(str)));
        System.out.println("：" + (getGuDongRenShu(str)));
    }

}
