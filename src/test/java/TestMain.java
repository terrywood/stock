import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Riky on 2016/2/14.
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
       String str ="viewQuestion.do?questionId=4774989";

        String  id = StringUtils.substringBetween(str,"questionId=","&");



  /*      Pattern pattern = Pattern.compile("\\d{2,}");
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            System.out.println(  matcher.group());
        }*/
        System.out.println(id);
    }
}
