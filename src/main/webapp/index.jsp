<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gt.bmf.service.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta http-equiv="refresh"content="3;url=${pageContext.request.contextPath}/admin/orderList.do">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/general.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/menu/menu.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/smoothness/jquery-ui.min.css" />
</head>
<body>



<div id="bodyContainer">


    <div style="width: 700px; margin: 0px auto; padding: 100px 0px">
        <form action="${pageContext.request.contextPath}/admin/orderList.do" method="post" id="loginForm">
            <div style="margin-left: 5px;">
                <div><span class="largeTitle"> SCMP HTML5 CMS[Staging] </span></div>
                <div style="margin: 5px 0px 10px;">
				<span class="subTitle">
					Administrator Login

				</span>
                </div>
                <div class="separator"></div>
                <div class="formRow">
                    <div class="label" style="width: 180px;">Username</div>
                    <div class="field"><input type="text" name="username" value="" /></div>
                </div>
                <div class="formRow">
                    <div class="label" style="width: 180px;">Password</div>
                    <div class="field"><input type="password" name="password" value="" /></div>
                </div>
                <div class="formRow">
                    <div class="label" style="width: 180px;">&nbsp;</div>
                    <div class="field"><input class="button" type="submit" value="Submit" /></div>
                </div>
            </div>
        </form>
    </div>
    <div id="footer"><span>Powered By</span></div>
</div>

</body>


</html>