import com.gt.bmf.service.StockMasterService;
import org.apache.log4j.BasicConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestStockMasterService {

    private static StockMasterService service;


    public static void main(String[] args) throws Exception {
     //   BasicConfigurator.configure();
/*
        String log4jConfPath = "D:\\Documents\\gf\\src\\main\\java\\log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);*/
      //


     /*   System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire.header", "debug");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "debug");
*/
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/bmf_applicationContext.xml");

        service = (StockMasterService) ctx.getBean("StockMasterService");

        for(int i=1;i<100;i++)
          service.checkData(String.valueOf(i));
/*

        Gson gson = new Gson();
        List<ArrayList> list  =gson.fromJson(new FileReader("D:\\Documents\\gf\\doc\\878004\\20151113.json"),List.class);
        for(ArrayList al :list){
            Double price  = (Double)al.get(1);
            service.checkPrice(price);
        }*/


      /*  for(int i=0;i<25;i++)
           service.buy(1d,1d);

        System.exit(0);*/

        //service.save();

	}

    public void testCreateDB(){


    }

}
