<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="en">
<head>
    <title>LKK</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="" />
    <%@ include file="/include.inc.jsp"%>
    <%@ include file="/include.js.jsp"%>
</head>
<%@ include file="../topBar.jsp"%>
<div id="bodyContainer">

    <div style="overflow: auto;">
        <div style="padding: 0px 0px 10px 5px; font-size: larger; font-weight: bolder; float: left;">
            <iframe width="500" frameborder="0" src="http://1111.ip138.com/ic.asp"></iframe>
        </div>
    </div>

    <%--form action="${pageContext.request.contextPath}/admin/checkout.do" method="post" style="margin-bottom: 30px;" onSubmit="return checkForm();" enctype="multipart/form-data">
        <input type="hidden" name="category" value="view_news" />
        <input type="hidden" name="sound" value="default" />
        <div class="formRow">
            <iframe height="15" frameborder="0" src="http://1111.ip138.com/ic.asp"></iframe>
        </div>

        <div class="formRow">
            <div class="label">&nbsp;</div>
            <div class="field"><input type="submit" value="Submit" /></div>
        </div>
    </form>--%>
    <div id="footer"><span>Powered By</span></div>
</div>
</html>