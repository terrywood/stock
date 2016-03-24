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
               <%--<input name="pageSize" value="5" type="hidden"/>--%>
                My unCheck  : <input type="checkbox"name="statusNot"  value="Y" <c:if test="${not empty param.statusNot}">checked="checked"</c:if> >
                股东人数 : <input type="checkbox"name="isGuDong"  value="true" <c:if test="${not empty param.isGuDong}">checked="checked"</c:if> >

                code: <input type="text"name="code" id="orderId" value="${param.code}">
                keyword: <input type="text" name="keyword" id="orderName" value="${param.keyword}">
<%--


                productId: <input type="text" name="productId" id="productId" value="${productId}">
--%>
                status:<select name="status">
                         <option value="">All</option>
                         <option value="V" <c:if test="${param.status eq 'V'}">selected="selected"</c:if>>Valid</option>
                         <option value="I" <c:if test="${param.status eq 'I'}">selected="selected"</c:if>>Invalid</option>
                      </select>

                <button class="button" type="submit" >Search</button>
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
            <th  width="80">ID</th>
            <th >Content</th>
            <th  width="150">Date</th>
            <th  width="400">
                <input type="submit" value="Save Mark">
              <%--  <input class="cancelMarkBtn" type="button" value="取消选中标记">--%>
                <input class="selectAll" type="button" value="select all">
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="obj" items="${pageList.data}" varStatus="vs">
            <tr>
                <td  rowspan="2" style="vertical-align:middle;text-align:center "><a href="list.do?code=${obj.code}">${obj.code}<br/>${obj.name}</a></td>
                <td>${obj.question}</td>
                <td><fmt:formatDate value="${obj.questionDate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
                <td   style="vertical-align:middle;text-align:center ">
                    <c:choose>
                        <c:when test="${obj.guDong}">
                        <a class="button tag" href="isGuDong.do?a=false&id=${obj.id}" >取消标记</a>
                         <input type="checkbox" name="cancelId" value="${obj.id}">
                        </c:when>
                        <c:otherwise>
                        <a class="button tag" href="isGuDong.do?a=true&id=${obj.id}" >标记人数</a>
                        </c:otherwise>
                    </c:choose>
                   </a>

                </td>
           </tr>
            <tr>
                <td>${obj.answer}</td>
                <td><fmt:formatDate value="${obj.answerDate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
                <td>
                   <c:if test="${obj.guDong}">

                <%--    股东人数 : <strong style="color: darkblue">${st:getGuDongRenShu(obj.answer)}</strong>  <br/>
                    日期 :  <fmt:formatDate value="${st:getGuDongDate(obj.answer)}" pattern="yyyy-MM-dd"/>--%>


                    <input type="hidden" name="markId" value="${obj.id}">
                    股东人数 : <input  name="markCount"  value=" <c:out value="${obj.markCount}" default="${st:getGuDongRenShu(obj.answer)}" ></c:out>">  <br/>

                    <c:choose>
                        <c:when test="${not empty obj.markDate}">
                            <c:set var="markDate"><fmt:formatDate value="${obj.markDate}" pattern="yyyy-MM-dd"/></c:set>
                        </c:when>
                        <c:otherwise>
                        <c:set var="markDate"><fmt:formatDate value="${st:getGuDongDate(obj.answer)}" pattern="yyyy-MM-dd"/></c:set>
                        </c:otherwise>
                    </c:choose>
                    日期 :  <input name="markDate" class="datepicker" value="${markDate}">

                </c:if>

                </td>
           </tr>
        </c:forEach>
        </tbody>
        <tr>
            <td rowspan="2" colspan="4">
               <%-- <input style="float:right"  class="cancelMarkBtn" type="button" value="取消选中标记">--%>
                <input style="float:right" type="submit" value="Save Mark">

            </td>

        </tr>

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