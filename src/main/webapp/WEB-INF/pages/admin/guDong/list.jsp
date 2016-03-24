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
    <script type="application/javascript">
        function triggerGuDong(id,isGuDong){
            $.ajax({
                url: "isGuDong.do",    //请求的url地址
                dataType: "json",   //返回格式为json
                data: { "id": id,"isGuDong":isGuDong },    //参数值
                type: "GET",   //请求方式
                success: function(req) {
                    window.location = window.location.href+"&r="+Math.random();
                }
            });
        }

        $(document).ready(function () {
            $(".tag").bind("click",function(){
                var href = $(this).attr("href");
                //href += window.location.search.replace('\?','&') ;
                href = href + '&qs='+ encodeURIComponent(window.location.search);
                window.location = href;
                return false;
            });
            $(".cancelMarkBtn").bind("click",function(){
                var href = "isGuDong.do?a=false&"+$('input[name=cancelId]:checked').serialize();
                //href += window.location.search.replace('\?','&') ;
                href = href + '&qs='+ encodeURIComponent(window.location.search);
                window.location = href;
                return false;
            });
            $(".selectAll").bind("click",function(){
               $("input[type=checkbox]").each(function(){
                   $(this).attr("checked","checked");
               });
                return false;
            });

            $(".datepicker").datepicker({
                monthNames: ['一月','二月','三月','四月','五月','六月', '七月','八月','九                          月','十月','十一月','十二月'],
                monthNamesShort: ['一','二','三','四','五','六', '七','八','九','十','十                            一','十二'],   //月份名称简称，用于选择月份时显示
                dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
                dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
                dayNamesMin: ['日','一','二','三','四','五','六'],   //日期名称简称
                dateFormat: 'yy-mm-dd',   //选中日期后，已这个格式显示
                changeMonth: true,     //可以选择月份
                changeYear: true,     //可以选择年份
                firstDay: 1,         //0为已周日作为一周开始，1为周一作为一周开始，默认是0
                isRTL: false         //是否从右到左排列

            });
        });


    </script>
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
    <form action="list.do" method="get" id="list_form">

        <div class="pics">
            <p>

                code: <input type="text"name="code" id="orderId" value="${param.code}">


                <button class="button" type="submit" >Search</button>
                <button class="button" type="button" onclick="window.location='gender.do'" >Gen Ranking</button>
            </p>
<%--
            <div class="button_row">
              <button class="button" type="button" onclick="back();">Cancel</button>
            </div>--%>
        </div>


    </form>
</div>

<div class="listview">
    <form  action="mark.do" method="post">
        <input type="hidden" name="qs" value="?${qs}">
    <table id="list_table" width="60%">
        <thead>
        <tr style="font-size: 100">
            <th  >code</th>
            <th >name</th>
            <th  >Date</th>
            <th  >mark count</th>
            <th  >price </th>
            <th  >     </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="obj" items="${pageList.data}" varStatus="vs">
            <tr>
                <td><a href="${pageContext.request.contextPath}/admin/guDong/list.do?code=${obj.code}">${obj.code}</a></td>
                <td>${obj.name}</td>
                <td><fmt:formatDate value="${obj.date}" pattern="yyyy-MM-dd"/> </td>
                <td><fmt:formatNumber value="${obj.markCount}" /> </td>
                <td><fmt:formatNumber value="${obj.price}" /> </td>
                <td   style="vertical-align:middle;text-align:center ">
                     <a class="button" href="${obj.id}.do">Edit</a>
                     <a class="button"  href="delete.do?id=${obj.id}">Delete</a>
                </td>
           </tr>

        </c:forEach>
        </tbody>


    </table>
    </form>

    <div id="paginationDiv">

        <pg:pager url="list.do"
                  items="${pageList.totalItems}"
                  maxPageItems="20"
                  maxIndexPages="10"
                  export="pagerOffset=pageOffset,currentPageNumber=pageNumber"
                  index="center">
            <pg:param name="keyword" value="${param.keyword}"/>
            <pg:param name="code"  value="${param.code}"/>
            <pg:param name="isGuDong"  value="${param.isGuDong}"/>
            <pg:param name="status"  value="${param.status}"/>
            <pg:param name="statusNot"  value="${param.statusNot}"/>
            <pg:index >
                <ul id="pagination">
                    <li> total : ${pageList.totalItems} </li>
                    <pg:prev ifnull="true"><c:choose><c:when test="${not empty pageUrl}"><li class="previous"><a href="${pageUrl}">&#171; Previous</a></li></c:when><c:otherwise><li class="previousOff">&#171; Previous</li></c:otherwise></c:choose></pg:prev><pg:pages><c:choose><c:when test="${pageNumber == currentPageNumber}"><li class="active">${pageNumber}</li></c:when><c:otherwise><li><a href="${pageUrl}">${pageNumber}</a></li></c:otherwise></c:choose></pg:pages><pg:next ifnull="true"><c:choose><c:when test="${not empty pageUrl}"><li class="next"><a href="${pageUrl}">Next &#187;</a></li></c:when><c:otherwise><li class="nextOff">Next &#187;</li></c:otherwise></c:choose></pg:next>
                </ul>
            </pg:index>
        </pg:pager>
    </div>


</div>

    <div id="footer"><span>Powered By</span></div>
</div>


</html>