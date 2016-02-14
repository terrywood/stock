<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gt.bmf.service.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en-US">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/general.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/menu/menu.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/colorbox/colorbox.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/smoothness/jquery-ui.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/timepicker/jquery-ui-timepicker-addon.css"/>
    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery-ui.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery-ui-timepicker-addon.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery-ui-sliderAccess.min.js"></script>
    <title>SCMP Breaking News &amp; Push Notification [UAT]</title>
</head>
<body>


<div id="topBar">
    <ul>
        <li class="title">SCMP Breaking News &amp; Push Notification [UAT]</li>

        <li><a href="${pageContext.request.contextPath}/static/pushList.do">Push Notification</a></li>
        <li><a href="${pageContext.request.contextPath}/static/iscoopList.do">IScoop</a></li>
        <li><a href="${pageContext.request.contextPath}/static/categoryList.do">Follow Topics</a></li>
        <li class="logout"><a href="${pageContext.request.contextPath}/static/logout.do">Logout</a></li>
        <li class="adminName">a</li>
    </ul>
    <div style="clear: both;"></div>
</div>


<div id="bodyContainer">


<div style="overflow: auto;">
    <div style="padding: 0px 0px 10px 5px; font-size: larger; font-weight: bolder; float: left;">
        Add New Push Notification
    </div>
</div>

<form action="${pageContext.request.contextPath}/static/pushAdd.do" method="post" style="margin-bottom: 30px;" onSubmit="return checkForm();" enctype="multipart/form-data">
    <input type="hidden" name="category" value="view_news" />
    <input type="hidden" name="sound" value="default" />
    <div class="formRow">
        <div class="label">Send To Device</div>
        <div class="field">
            <input type="radio" name="type" id="uuid" value="-2" /> <label for="uuid">Specific UUID</label>
            <input type="radio" name="type" id="all" value="-1" checked="checked" /> <label for="all">All [Total: 149]</label>
            <input type="radio" name="type" id="ios" value="0" /> <label for="ios">iOS [Total: 12]</label>
            <input type="radio" name="type" id="android" value="1" /> <label for="android">Android [Total: 137]</label>
        </div>
    </div>

    <div class="formRow" id="uuidRow">
        <div class="label">UUID</div>
        <div class="field"><input type="text" name="uuid" value="" /></div>
    </div>

    <div class="formRow">
        <div class="label">Section</div>
        <div class="field">
            <select name="tid">
                <option value="-1">NON-NEWS</option>
                <option value="2" >HONG  KONG</option>
                <option value="4" >CHINA</option>
                <option value="447" >CHINA INSIDER</option>
                <option value="3" >ASIA</option>
                <option value="5" >WORLD</option>
                <option value="92" >BUSINESS</option>
                <option value="88" >COMMENT</option>
                <option value="17" >INSIGHT & OPINION</option>
                <option value="240" >LETTERS</option>
                <option value="19" >BLOGS</option>
                <option value="141" >LIFESTYLE</option>
                <option value="62" >SPORT</option>
                <option value="64" >RACING</option>
                <option value="457" >RUGBY</option>
                <option value="68" >SOCCER</option>
                <option value="96" >PROPERTY</option>
            </select>
        </div>
    </div>

    <div class="formRow">
        <div class="label">Title</div>
        <div class="field"><textarea name="content"></textarea></div>
    </div>

    <div class="formRow">
        <div class="label">Label (Apple Watch)</div>
        <div class="field"><input type="text" name="title" value="Breaking News" /></div>
    </div>

    <div class="formRow">
        <div class="label">Summary (Apple Watch)</div>
        <div class="field"><input type="text" name="summary" value="" /></div>
    </div>

    <div class="formRow">
        <div class="label">
            <div>Image (Apple Watch)</div>
            <div style="font-size: smaller; font-weight: normal; color: #777;">image size : 390px * 312px (jpg)</div>
            <div style="font-size: smaller; font-weight: normal; color: #777;">file size: under 100kb</div>
        </div>
        <div class="field"><input type="file" name="image" value="" /></div>
    </div>

    <div class="formRow" id="nidRow">
        <div class="label">Article ID</div>
        <div class="field"><input type="text" name="nid" value="" /></div>
    </div>

    <!-- 	<div class="formRow"> -->
    <!-- 		<div class="label">Category</div> -->
    <!-- 		<div class="field"><input type="text" name="category" value="" /></div> -->
    <!-- 	</div> -->

    <!-- 	<div class="formRow"> -->
    <!-- 		<div class="label">Sound</div> -->
    <!-- 		<div class="field"><input type="text" name="sound" value="" /></div> -->
    <!-- 	</div> -->

    <div class="formRow">
        <div class="label">&nbsp;</div>
        <div class="field"><input type="submit" value="Submit" /></div>
    </div>
</form>

<div style="overflow: auto;">
    <div style="padding: 0px 0px 10px 5px; font-size: larger; font-weight: bolder; float: left;">
        Push Notification List
    </div>
</div>

<div class="listview">
<table>
<thead>
<tr>
    <th>Ref. Id</th>
    <th>Info</th>
    <th>Section</th>
    <th>Article Id</th>
    <th>Created By</th>
    <th>Total Sent</th>
    <th>Status</th>
    <th>Create Time</th>
</tr>
</thead>
<tbody>

<tr>
    <td>749</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Manhunt for gunman as Tsim Sha Tsui watch shop worker fights for life after robbery</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Manhunt for gunman as Tsim Sha Tsui watch shop worker fights for life after robbery</div>
        </div>



    </td>
    <td>



        CHINA INSIDER














    </td>
    <td>

        1736251


    </td>
    <td>a</td>
    <td>115</td>
    <td>


        DONE

    </td>
    <td>2015-03-13 19:15</td>
</tr>

<tr>
    <td>748</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Test Push Content</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Test</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Summary (Apple Watch)</div>
            <div>Test Push Content Summary</div>
        </div>


    </td>
    <td>

        HONG  KONG
















    </td>
    <td>

        1673668


    </td>
    <td>a</td>
    <td>11</td>
    <td>


        DONE

    </td>
    <td>2015-03-13 13:47</td>
</tr>

<tr>
    <td>747</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Breaking News</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Summary (Apple Watch)</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Image (Apple Watch)</div>
            <div><img src="http://54.251.143.126/scmp/public/1423872615790.jpg" /></div>
        </div>

    </td>
    <td>






        BUSINESS











    </td>
    <td>

        1673668


    </td>
    <td>a</td>
    <td>9</td>
    <td>


        DONE

    </td>
    <td>2015-02-14 08:10</td>
</tr>

<tr>
    <td>746</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Breaking News</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Summary (Apple Watch)</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Image (Apple Watch)</div>
            <div><img src="http://54.251.143.126/scmp/public/1423872466797.jpg" /></div>
        </div>

    </td>
    <td>






        BUSINESS











    </td>
    <td>

        1673668


    </td>
    <td>a</td>
    <td>9</td>
    <td>


        DONE

    </td>
    <td>2015-02-14 08:07</td>
</tr>

<tr>
    <td>745</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Breaking News</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Summary (Apple Watch)</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Image (Apple Watch)</div>
            <div><img src="http://54.251.143.126/scmp/public/1423871301889.jpg" /></div>
        </div>

    </td>
    <td>






        BUSINESS











    </td>
    <td>

        1673668


    </td>
    <td>a</td>
    <td>9</td>
    <td>


        DONE

    </td>
    <td>2015-02-14 07:48</td>
</tr>

<tr>
    <td>744</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Hong Kong</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Summary (Apple Watch)</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Image (Apple Watch)</div>
            <div><img src="http://54.251.143.126/scmp/public/1423871196320.jpg" /></div>
        </div>

    </td>
    <td>






        BUSINESS











    </td>
    <td>

        1673668


    </td>
    <td>a</td>
    <td>9</td>
    <td>


        DONE

    </td>
    <td>2015-02-14 07:46</td>
</tr>

<tr>
    <td>743</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Breaking News</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Summary (Apple Watch)</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Image (Apple Watch)</div>
            <div><img src="http://54.251.143.126/scmp/public/1423870901742.jpg" /></div>
        </div>

    </td>
    <td>

        HONG  KONG
















    </td>
    <td>

        1673668


    </td>
    <td>a</td>
    <td>9</td>
    <td>


        DONE

    </td>
    <td>2015-02-14 07:41</td>
</tr>

<tr>
    <td>742</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Breaking News</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Summary (Apple Watch)</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Image (Apple Watch)</div>
            <div><img src="http://54.251.143.126/scmp/public/1423870784410.jpg" /></div>
        </div>

    </td>
    <td>






        BUSINESS











    </td>
    <td>

        1673668


    </td>
    <td>a</td>
    <td>9</td>
    <td>


        DONE

    </td>
    <td>2015-02-14 07:39</td>
</tr>

<tr>
    <td>741</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Breaking News</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Summary (Apple Watch)</div>
            <div>2. Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Image (Apple Watch)</div>
            <div><img src="http://54.251.143.126/scmp/public/1423870710596.jpg" /></div>
        </div>

    </td>
    <td>






        BUSINESS











    </td>
    <td>

        1673668


    </td>
    <td>a</td>
    <td>9</td>
    <td>


        DONE

    </td>
    <td>2015-02-14 07:38</td>
</tr>

<tr>
    <td>740</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Breaking News</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Summary (Apple Watch)</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Image (Apple Watch)</div>
            <div><img src="http://54.251.143.126/scmp/public/1423869711877.jpg" /></div>
        </div>

    </td>
    <td>






        BUSINESS











    </td>
    <td>

        1673668


    </td>
    <td>a</td>
    <td>9</td>
    <td>


        DONE

    </td>
    <td>2015-02-14 07:21</td>
</tr>

<tr>
    <td>739</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Breaking News</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Summary (Apple Watch)</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Image (Apple Watch)</div>
            <div><img src="http://54.251.143.126/scmp/public/1423869657417.jpg" /></div>
        </div>

    </td>
    <td>






        BUSINESS











    </td>
    <td>

        1673668


    </td>
    <td>a</td>
    <td>92</td>
    <td>


        DONE

    </td>
    <td>2015-02-14 07:20</td>
</tr>

<tr>
    <td>738</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Breaking News</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Summary (Apple Watch)</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Image (Apple Watch)</div>
            <div><img src="http://54.251.143.126/scmp/public/1423869541199.jpg" /></div>
        </div>

    </td>
    <td>






        BUSINESS











    </td>
    <td>

        1673668


    </td>
    <td>a</td>
    <td>92</td>
    <td>


        DONE

    </td>
    <td>2015-02-14 07:19</td>
</tr>

<tr>
    <td>737</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Hong Kong and HSBC under scrutiny</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Breaking News</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Summary (Apple Watch)</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Image (Apple Watch)</div>
            <div><img src="http://54.251.143.126/scmp/public/1423867667317.jpg" /></div>
        </div>

    </td>
    <td>






        BUSINESS











    </td>
    <td>

        1673668


    </td>
    <td>a</td>
    <td>9</td>
    <td>


        DONE

    </td>
    <td>2015-02-14 06:47</td>
</tr>

<tr>
    <td>736</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Hong Kong and HSBC under scrutiny</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Breaking News</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Summary (Apple Watch)</div>
            <div>Hong Kong and HSBC under scrutiny as US cracks down on American tax cheats</div>
        </div>


    </td>
    <td>






        BUSINESS











    </td>
    <td>

        1673668


    </td>
    <td>a</td>
    <td>92</td>
    <td>


        DONE

    </td>
    <td>2015-02-14 06:41</td>
</tr>

<tr>
    <td>735</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Hong Kong and HSBC under scrutiny</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Breaking News</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Summary (Apple Watch)</div>
            <div>1. Hong Kong and HSBC under scrutiny 2.Hong Kong and HSBC under scrutiny</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Image (Apple Watch)</div>
            <div><img src="http://54.251.143.126/scmp/public/1423866981048.jpg" /></div>
        </div>

    </td>
    <td>






        BUSINESS











    </td>
    <td>

        1673668


    </td>
    <td>a</td>
    <td>9</td>
    <td>


        DONE

    </td>
    <td>2015-02-14 06:36</td>
</tr>

<tr>
    <td>734</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Hong Kong and HSBC under scrutiny</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Breaking News</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Summary (Apple Watch)</div>
            <div>1. Hong Kong and HSBC under scrutiny 2. Hong Kong and HSBC under scrutiny</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Image (Apple Watch)</div>
            <div><img src="http://54.251.143.126/scmp/public/1423866780760.jpg" /></div>
        </div>

    </td>
    <td>






        BUSINESS











    </td>
    <td>

        1673668


    </td>
    <td>a</td>
    <td>92</td>
    <td>


        DONE

    </td>
    <td>2015-02-14 06:33</td>
</tr>

<tr>
    <td>733</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Content</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Title</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Summary (Apple Watch)</div>
            <div>Summary</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Image (Apple Watch)</div>
            <div><img src="http://54.251.143.126/scmp/public/1423866341073.jpg" /></div>
        </div>

    </td>
    <td>


















    </td>
    <td>


        &nbsp;

    </td>
    <td>a</td>
    <td>92</td>
    <td>


        DONE

    </td>
    <td>2015-02-14 06:25</td>
</tr>

<tr>
    <td>732</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Content</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Title</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Summary (Apple Watch)</div>
            <div>Summary</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Image (Apple Watch)</div>
            <div><img src="http://54.251.143.126/scmp/public/1423866108203.jpg" /></div>
        </div>

    </td>
    <td>


















    </td>
    <td>


        &nbsp;

    </td>
    <td>a</td>
    <td>92</td>
    <td>

        PROCESSING


    </td>
    <td>2015-02-14 06:21</td>
</tr>

<tr>
    <td>731</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Hong Kong and HSBC under scrutiny</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Breaking News</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Summary (Apple Watch)</div>
            <div>2. Hong Kong and HSBC under scrutiny</div>
        </div>


    </td>
    <td>






        BUSINESS











    </td>
    <td>

        1673668


    </td>
    <td>a</td>
    <td>92</td>
    <td>


        DONE

    </td>
    <td>2015-02-14 04:59</td>
</tr>

<tr>
    <td>730</td>
    <td>

        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Title</div>
            <div>Hong Kong and HSBC under scrutiny</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Label (Apple Watch)</div>
            <div>Breaking News</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Summary (Apple Watch)</div>
            <div>2. Hong Kong and HSBC under scrutiny</div>
        </div>


        <div style="margin-bottom: 5px;">
            <div style="font-style: italic; font-size: smaller;">Image (Apple Watch)</div>
            <div><img src="http://54.251.143.126/scmp/public/1423860983410.jpg" /></div>
        </div>

    </td>
    <td>


















    </td>
    <td>

        1673668


    </td>
    <td>a</td>
    <td>9</td>
    <td>


        DONE

    </td>
    <td>2015-02-14 04:56</td>
</tr>

</tbody>
<tfoot>
<tr><td colspan="8">
    <div id="paginationDiv">


        <ul id="pagination">
            <li class="previousOff">&#171; Previous</li><li class="active">1</li><li><a href="pushList.do?pager.offset=20">2</a></li><li><a href="pushList.do?pager.offset=40">3</a></li><li><a href="pushList.do?pager.offset=60">4</a></li><li><a href="pushList.do?pager.offset=80">5</a></li><li><a href="pushList.do?pager.offset=100">6</a></li><li><a href="pushList.do?pager.offset=120">7</a></li><li><a href="pushList.do?pager.offset=140">8</a></li><li><a href="pushList.do?pager.offset=160">9</a></li><li><a href="pushList.do?pager.offset=180">10</a></li><li class="next"><a href="pushList.do?pager.offset=20">Next &#187;</a></li>
        </ul>


    </div>
</td></tr>
</tfoot>
</table>
</div>

<script type="text/javascript">
    function showOrHideUuidRow() {
        var selectedItemValue = $("input[type='radio']:checked").val();
        if (selectedItemValue == "-2") {
            $("div#uuidRow").slideDown();
        } else {
            $("div#uuidRow").slideUp();
            $("input[name='uuid']").val("");
        }
    }

    function checkPayloadLength() {
        return false;

    }

    function checkForm() {
        if ($("input[type='radio']:checked").val() == '-2') {
            if ($("input[name='uuid']").val() == '') {
                alert('Please Input a UUID.');
                return false;
            }
        }

        if ($("textarea[name='content']").val() == '') {
            alert('Please Input Push Notification Content.');
            return false;
        }

        var tid = $("select[name='tid']").val();
        if (tid != -1) {
            var nid = $("input[name='nid']").val();
            if (nid == null || nid == '' || isNaN(nid)) {
                alert('Please Input Valid News Id.');
                return false;
            }
        }

        if (checkPayloadLength()) {
            alert('Content Too Large.');
            return false;
        }

        return true;
    }

    function showOrHideNews() {
        var selectedItemValue = $("select[name='tid']").val();
        if (selectedItemValue == "-1") {
            $("div#nidRow").slideUp();
            $("input[name='nid']").val("");
        } else if (!$("div#nidRow").is(":visible")) {
            $("div#nidRow").slideDown();
            $("input[name='nid']").val("");
        }
    }

    $(function() {
        $("div#uuidRow").hide();

        $("input[type='radio']").change(showOrHideUuidRow);
        if (checkPayloadLength()) {
            alert('Content Too Large.');
            return false;
        }

        $("select[name='tid']").change(showOrHideNews);
    });
</script>

<div id="footer"><span>Powered By</span></div>
</div>

</body>
</html>