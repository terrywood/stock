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
        function updateStatus(){
            var params ={};
            $("[name='items']").each(function(){
                var id = ($(this).val());
                var status = ($(this).prop("checked"));
                params[id]=status;
            });
           var str = jQuery.param(params);
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/admin/updateOrderTag.do",
                data: str,
                dataType: 'json',
                success: function(data){
                    if(data.message)  {
                        alert(data.message);
                    }else{
                        if($('.next').length>0 ){
                            window.location = $(".next a").attr("href");
                        } else{
                            window.location = 'orderItemList.do';
                        }

                    }
                }
            });
        }
    </script>
</head>
<body>
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
    <form action="orderItemList.do" method="post" id="list_form">
        <div class="pics">
            <p>
                orderId: <input type="text"name="orderId" id="orderId" value="${params.orderId}">
                orderName: <input type="text" name="orderName" id="orderName" value="${params.orderName}">
                trackId: <input type="text" name="trackId" id="trackId" value="${params.trackId}">
<%--
               status: <input type="text" name="status" id="status" value="${status}">
                productId: <input type="text" name="productId" id="productId" value="${productId}">
--%>
                <button class="button" type="submit" >Search</button>
                <button class="button" type="button" onclick="updateStatus()">Update</button>
            </p>
<%--
            <div class="button_row">
              <button class="button" type="button" onclick="back();">Cancel</button>
            </div>--%>
        </div>
    </form>
</div>

<div class="listview">
    <table id="list_table">
        <thead>
        <tr style="font-size: 100">
            <th ><input type="checkbox" value="all" id="btn1" onclick="selFn()"/></th>
            <th >No.</th>
            <th >TrackId</th>
            <th >OrderId</th>

            <th >DeliveryDate</th>
            <th >Product</th>
            <th >Status</th>
            <th >OrderTime</th>
            <th >OrderName</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="obj" items="${pageList.data}" varStatus="vs">
            <tr>
                <td>
                    <input type="checkbox" value="${obj[7]}" name="items" <c:if test="${obj[8]}">checked="checked"</c:if> />
                </td>
                <td>${vs.count}</td>
                <td>${obj[0]}</td>
                <td>${obj[4]}</td>

                <td><fmt:formatDate value="${obj[2]}" pattern="yyyy-MM-dd"/></td>
                <td>${obj[3]}</td>
                <td>${obj[1]}</td>
                <td><fmt:formatDate value="${obj[5]}" pattern="yyyy-MM-dd"/></td>
                <td>${obj[6]}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div id="paginationDiv">
        <pg:pager url="orderItemList.do"
                  items="${pageList.totalItems}"
                  maxPageItems="20"
                  maxIndexPages="10"
                  export="pagerOffset=pageOffset,currentPageNumber=pageNumber"
                  index="center">
            <pg:param name="orderName" value="${param.orderName}"/>
            <pg:param name="orderId"  value="${param.orderId}"/>
            <pg:index >
                <ul id="pagination">
                    <pg:prev ifnull="true">
                        <c:choose>
                            <c:when test="${not empty pageUrl}">
                                <li class="previous"><a href="${pageUrl}">&#171; Previous</a></li></c:when>
                            <c:otherwise>
                                <li class="previousOff">&#171; Previous</li></c:otherwise>
                        </c:choose>
                    </pg:prev>
                    <pg:pages>
                        <c:choose>
                            <c:when test="${pageNumber == currentPageNumber}">
                                <li class="active">${pageNumber}</li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${pageUrl}">${pageNumber}</a>
                            </li></c:otherwise>
                        </c:choose>
                    </pg:pages>
                    <pg:next ifnull="true"><c:choose><c:when test="${not empty pageUrl}"><li class="next"><a href="${pageUrl}">Next &#187;</a></li></c:when><c:otherwise><li class="nextOff">Next &#187;</li></c:otherwise></c:choose></pg:next>
                </ul>
            </pg:index>
        </pg:pager>
    </div>


</div>
    <div id="footer"><span>Powered By</span></div>
</div>

</body>
</html>