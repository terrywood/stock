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
    <style type="text/css">
        .myns > div {
            box-shadow: 0 0 6px black, inset 0 0 6px black;
        }
    </style>
</head>
<body>


<%@ include file="../topBar.jsp"%>
<div id="bodyContainer">

    <div style="overflow: auto;">
        <div style="padding: 0px 0px 10px 5px; font-size: larger; font-weight: bolder; float: left;">
           Check out order list by cookie
        </div>
    </div>

    <form action="${pageContext.request.contextPath}/admin/checkout.do" method="post" style="margin-bottom: 30px;" onSubmit="return checkForm();" enctype="multipart/form-data">
        <input type="hidden" name="category" value="view_news" />
        <input type="hidden" name="sound" value="default" />
        <div class="formRow">
            <div class="label">Cookie<br/>
                <span style="font-style: normal;color: greenyellow">document.cookie </span>
                <br/>
                <span style="font-style: normal;color: greenyellow">15812415893 </span>
            </div>
            <div class="field">
                <textarea style="width: 600px;height:100px" rows="20"  name="cookie">${jacookie}</textarea>
            </div>
        </div>
        <div class="formRow">
            <div class="label">Page</div>
            <div class="field">
                <select name="page">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>

            </div>
        </div>
<%--        <div class="formRow">
            <div class="label">Date</div>
            <div class="field">
                 <input id="time">
            </div>
        </div>--%>
        <div class="formRow">
            <div class="label">OrderId</div>
            <div class="field">
                 <input type="text" id="orderId" name="orderId">
            </div>
        </div>
        <div class="formRow">
            <div class="label">&nbsp;</div>
            <div class="field"><input type="submit" value="Submit" /></div>
        </div>
        <div class="">
           <div id="shclNs"></div>
        </div>
    </form>
    <div id="footer"><span>Powered By</span></div>
</div></body>
</html>

<script>
    $(document).ready(function () {
        //$("#time").datepicker();

        $("form").submit(function(event) {
            $('#shclNs').show();
            $('#shclNs').shCircleLoader({namespace:"myns",color:"transparent",dotsRadius:15});
            event.preventDefault();
            var str = $("form").serialize();
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/admin/checkout.do",
                data: str,
                dataType: 'json',
                success: function(data){
                    if(data.message)  {
                        alert(data.message);
                        $('#shclNs').hide();
                    }else{
                        window.location='orderList.do';
                    }
                }
            });

            //$("span").text("Not valid!").show().fadeOut(1000);
            return false;
        });
    });

</script>