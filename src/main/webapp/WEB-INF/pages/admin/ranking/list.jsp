<%@ page import="org.codehaus.jackson.map.ObjectMapper" %>
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
    <style>
        table {
            width: 60%;
            font-size: 24px;
        }
    </style>
</head>
<%@ include file="../topBar.jsp"%>
<div id="bodyContainer">
<%--<div style="overflow: auto;">
    <div style="padding: 0px 0px 10px 5px; font-size: large; font-weight: bolder; float: left;">
       Order List
    </div>
    <div style="padding: 0px 5px 10px 0px; float: right;">
        <input value="Add" class="button" style="cursor: pointer;" type="button" onclick="toAddParent()"/>
    </div>
</div>--%>



<div class="list-div">
    <form action="list.do" method="post" id="list_form">
        <div class="pics">
            <p>
                code: <input type="text"name="code" id="orderId" value="${param.code}">
                keyword: <input type="text" name="keyword" id="orderName" value="${param.keyword}">
                <button class="button" type="submit" >Search</button>
            </p>
        </div>
    </form>
</div>

<div class="listview">
    <table id="list_table" >
        <thead>
        <tr style="font-size: 100">
            <th >code</th>
            <th >date3</th>
            <th >date2</th>
            <th >date1</th>
            <th >percent</th>
            <th >percent</th>
            <th >*</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="obj" items="${pageList.data}" varStatus="vs">
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/guDong/list.do?code=${obj.code}">
                ${obj.code}</a>
                <br/>${obj.name}
                </td>
                <td  align="center" valign="middle"><fmt:formatDate value="${obj.date3}" pattern="yyyy-MM-dd"/>
                    <br/><fmt:formatNumber value="${obj.count3}" type="number" /></td>
                <td  align="center" valign="middle"><fmt:formatDate value="${obj.date2}" pattern="yyyy-MM-dd"/>
                    <br/><fmt:formatNumber value="${obj.count2}" type="number" /></td>
                <td  align="center" valign="middle"><fmt:formatDate value="${obj.date1}" pattern="yyyy-MM-dd"/>
                    <br/><fmt:formatNumber value="${obj.count1}" type="number" /></td>
                <td><fmt:formatNumber value="${obj.change1}" type="percent" /></td>
                <td><fmt:formatNumber value="${obj.change2}" type="percent" /></td>
                <td>
                    <a target="_blank" href="http://quote.eastmoney.com/sz${obj.code}.html">East</a>
                    <a target="_blank" href="chart.do?code=${obj.code}">Chart</a>
                </td>
           </tr>
        </c:forEach>
        </tbody>
    </table>

    <div id="paginationDiv">
        <pg:pager url="list.do"
                  items="${pageList.totalItems}"
                  maxPageItems="20"
                  maxIndexPages="10"
                  export="pagerOffset=pageOffset,currentPageNumber=pageNumber"
                  index="center">
            <pg:param name="keyword" value="${param.keyword}"/>
            <pg:param name="code"  value="${param.code}"/>
            <pg:index >
                <ul id="pagination">
                    <pg:prev ifnull="true"><c:choose><c:when test="${not empty pageUrl}"><li class="previous"><a href="${pageUrl}">&#171; Previous</a></li></c:when><c:otherwise><li class="previousOff">&#171; Previous</li></c:otherwise></c:choose></pg:prev><pg:pages><c:choose><c:when test="${pageNumber == currentPageNumber}"><li class="active">${pageNumber}</li></c:when><c:otherwise><li><a href="${pageUrl}">${pageNumber}</a></li></c:otherwise></c:choose></pg:pages><pg:next ifnull="true"><c:choose><c:when test="${not empty pageUrl}"><li class="next"><a href="${pageUrl}">Next &#187;</a></li></c:when><c:otherwise><li class="nextOff">Next &#187;</li></c:otherwise></c:choose></pg:next>
                </ul>
            </pg:index>
        </pg:pager>
    </div>


</div>

    <div id="footer"><span>Powered By</span></div>
</div>


</html>