<%@ page import="java.util.List" %>
<%@ page import="com.gt.bmf.pojo.OrderItem" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="en">
<head>
    <title>Gen Script</title>
    <%@ include file="/include.inc.jsp"%>
</head>
<body>
<pre>
  <%
      List<OrderItem> list =(List<OrderItem>)request.getAttribute("list");
     // int j=0;
      for(int i=0;i<list.size();i=i+4){
          for(int j=0;j<4;j++) {

          }
      %>

    <%
      }
  %>
</pre>
</body>
</html>
