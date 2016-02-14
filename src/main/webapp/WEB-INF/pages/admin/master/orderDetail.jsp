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
    <script type="text/javascript">
       function selFn(){
            if($("#btn1").is(':checked')){
                $("[name='items']").prop("checked",true);
            } else{
                $("[name='items']").prop("checked",false);
            }

        }
    </script>
</head>
<body>
<%@ include file="../topBar.jsp"%>

<form action="${pageContext.request.contextPath}/admin/updateOrderDetail.do" method="post">
<div id="bodyContainer">


<div style="overflow: auto;">
    <div style="padding: 0px 0px 10px 5px; font-size: large; font-weight: bolder; float: left;">
       Order ID : ${param.orderId}
    </div>
    <div style="padding: 0px 5px 10px 0px; float: right;">
        <input type="hidden" name="orderId" value="${param.orderId}"/>
        <input type="hidden" name="searchOrderId" value="${param.searchOrderId}"/>
        <input type="hidden" name="searchOrderName" value="${param.searchOrderName}"/>
        <input value="Submit" class="button" style="cursor: pointer;" type="submit" />
    </div>
</div>



<div class="listview">
    <table id="list_table">
        <thead>
        <tr style="font-size: 100">
            <th ><input type="checkbox" value="all" id="btn1" onclick="selFn()"/></th>
            <th >No.</th>
            <th >trackId</th>
            <th >status</th>
            <th >deliveryDate</th>
            <th >product</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="obj" items="${list}" varStatus="vs">
            <tr>
                <td>
<input type="checkbox" value="${obj.id}" name="items" <c:if test="${obj.tag}">checked="checked"</c:if> /></td>
                <td>${vs.count}</td>
                <td>${obj.trackId}</td>
                <td>${obj.status}</td>
                <td><fmt:formatDate value="${obj.deliveryDate}" pattern="yyyy-MM-dd"/> </td>
                <td>${obj.product.aliasName}</td>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

  </div>
</div>
</form>

</div>
    <div id="footer"><span>Powered By</span></div>
</div>

</body>
</html>