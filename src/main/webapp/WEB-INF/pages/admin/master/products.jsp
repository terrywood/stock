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

<form action="${pageContext.request.contextPath}/admin/updateProducts.do" method="post">
<div id="bodyContainer">


<div style="overflow: auto;">
    <div style="padding: 0px 0px 10px 5px; font-size: large; font-weight: bolder; float: left;">
      Products
    </div>
    <div style="padding: 0px 5px 10px 0px; float: right;">
        <input value="Submit" class="button" style="cursor: pointer;" type="submit" />
    </div>
</div>



<div class="listview">
    <table id="list_table">
        <thead>
        <tr style="font-size: 100">
            <th >No.</th>
            <th >Image</th>
            <th >Name</th>
            <th >aliasName</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="obj" items="${products}" varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td><img width="60" height="60" src="${obj.image}"/></td>
                <td>${obj.name}</td>
                <td><input name="${obj.id}" value="${obj.aliasName}"/></td>
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