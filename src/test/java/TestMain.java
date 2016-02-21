import com.alibaba.druid.support.json.JSONUtils;
import com.gt.bmf.util.NUIResponseUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Riky on 2016/2/14.
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
     //  String str ="截至2016年2月15日，公司股东总数为154,857。多谢您对公司的关注！" ;
      // Pattern pattern = Pattern.compile("([0-9]+[,]?)+");Matcher matcher = pattern.matcher(str);
        //List<Integer> list = new ArrayList<Integer>();
 /*       int renCount=0;
        while(matcher.find()){
            String value = matcher.group();
            value = StringUtils.replace(value,",","");
            int _temp = Integer.valueOf(value);
            if(_temp>renCount){
                renCount =_temp;
            }
        }

*/
        Map<String,String> map = new HashMap<String,String>();
        map.put("Status","0");
        System.out.println( JSONUtils.toJSONString(map));
    }
}
