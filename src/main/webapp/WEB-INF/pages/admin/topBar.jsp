<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="topBar">
    <ul>
        <li class="title">AMAZON CMS </li>
        <%--<li class="title"> <iframe height="15" src="http://1111.ip138.com/ic.asp"></iframe> </li>--%>
        <li><a href="${pageContext.request.contextPath}/admin/huDong/list.do">互动问答</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/guDong/list.do">股东人数</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/ranking/list.do">Ranking</a></li>
        <li class="logout"><a href="${pageContext.request.contextPath}/admin/huDong/3part.do">互动平台数据</a></li>
        <li class="logout"><a href="${pageContext.request.contextPath}/admin/guDong/report.do">Analysis</a></li>
        <li class="logout"><a href="${pageContext.request.contextPath}/admin/guDong/gender.do">Export Ranking</a></li>
    </ul>
    <div style="clear: both;">

    </div>
</div>