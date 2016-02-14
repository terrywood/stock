<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="java.util.Enumeration" %>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gt.bmf.service.*" %>

<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.gt.bmf.common.page.PageList" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
    WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext())  ;
/*    FtrReportService ftrReportService = (FtrReportService)wac.getBean("ftrReportService") ;
    for(int i=0 ;i <10;i++){
        FtrReport model = new FtrReport();
        model.setCreateDate(new Date());
        model.setAuthorizedUser("ALL");
        model.setStatus("A");
        model.setFileName("buyerFile/WordRqmErrors"+i+".log");
        ftrReportService.save(model);
    }*/


%>
<script>
    $("#zfbaccount").val("rikyse@163.com");
    $("#zfbname").val("谢绮芬");
    var tracks =['412466771776','412466770586','412466771581','412466771710']
    $('input[name="productexpno[]"]').each(function(index){
       $(this).val(tracks[index]);
       $('select[name=goodsid'+index+']').val('90').attr("selected",true).prop("checked", true);;
    });

</script>