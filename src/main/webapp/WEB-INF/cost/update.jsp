<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/23 0023
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>中软－NetCMOSS</title>
    <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
    <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
    <script language="javascript" type="text/javascript">
        //保存结果的提示
        function showResult() {
            showResultDiv(true);
            window.setTimeout("showResultDiv(false);", 3000);
        }
        function showResultDiv(flag) {
            var divResult = document.getElementById("save_result_info");
            if (flag)
                divResult.style.display = "block";
            else
                divResult.style.display = "none";
        }

        //切换资费类型
        function feeTypeChange(type) {
            var inputArray = document.getElementById("main").getElementsByTagName("input");
            if (type == 1) {
                inputArray[5].readOnly = true;
                inputArray[5].value = "";
                inputArray[5].className += " readonly";
                inputArray[6].readOnly = false;
                inputArray[6].className = "width100";
                inputArray[7].readOnly = true;
                inputArray[7].className += " readonly";
                inputArray[7].value = "";
            }
            else if (type == 2) {
                inputArray[5].readOnly = false;
                inputArray[5].className = "width100";
                inputArray[6].readOnly = false;
                inputArray[6].className = "width100";
                inputArray[7].readOnly = false;
                inputArray[7].className = "width100";
            }
            else if (type == 3) {
                inputArray[5].readOnly = true;
                inputArray[5].value = "";
                inputArray[5].className += " readonly";
                inputArray[6].readOnly = true;
                inputArray[6].value = "";
                inputArray[6].className += " readonly";
                inputArray[7].readOnly = false;
                inputArray[7].className = "width100";
            }
        }
    </script>
</head>
<body>
<!--Logo区域开始-->
<c:import url="../head.jsp"></c:import>
<!--Logo区域结束-->
<!--导航区域开始-->
<c:import url="../menu.jsp"></c:import>
<!--导航区域结束-->
<!--主要区域开始-->
<div id="main">
    <div id="save_result_info" class="save_success">保存成功！</div>
    <form action="updateCost.do" method="post" class="main_form">
        <div class="text_info clearfix"><span>资费ID：</span></div>
        <div class="input_info"><input type="text" class="readonly" name="costId" readonly value="${cost.costId}" /></div>
        <div class="text_info clearfix"><span>资费名称：</span></div>
        <div class="input_info">
            <input type="text" class="width300" name="name" value="${cost.name}"/>
            <span class="required">*</span>
            <div class="validate_msg_short">50长度的字母、数字、汉字和下划线的组合</div>
        </div>
        <div class="text_info clearfix"><span>资费类型：</span></div>
        <div class="input_info fee_type">
            <input type="radio" name="costType" ${cost.costType == 1 ? 'checked' : ''} id="monthly" onclick="feeTypeChange(1);" />
            <label for="monthly">包月</label>
            <input type="radio" name="costType" ${cost.costType == 2 ? 'checked' : ''} id="package" onclick="feeTypeChange(2);" />
            <label for="package">套餐</label>
            <input type="radio" name="costType" ${cost.costType == 3 ? 'checked' : ''} id="timeBased" onclick="feeTypeChange(3);" />
            <label for="timeBased">计时</label>
        </div>
        <div class="text_info clearfix"><span>基本时长：</span></div>
        <div class="input_info">
            <input type="text" value="${cost.baseDuration}" name="baseDuration" class="width100" />
            <span class="info">小时</span>
            <span class="required">*</span>
            <div class="validate_msg_long">1-600之间的整数</div>
        </div>
        <div class="text_info clearfix"><span>基本费用：</span></div>
        <div class="input_info">
            <input type="text" value="${cost.baseCost}" name="baseCost" class="width100" />
            <span class="info">元</span>
            <span class="required">*</span>
            <div class="validate_msg_long">0-99999.99之间的数值</div>
        </div>
        <div class="text_info clearfix"><span>单位费用：</span></div>
        <div class="input_info">
            <input type="text" value="${cost.unitCost}" name="unitCost" class="width100" />
            <span class="info">元/小时</span>
            <span class="required">*</span>
            <div class="validate_msg_long">0-99999.99之间的数值</div>
        </div>
        <div class="text_info clearfix"><span>资费说明：</span></div>
        <div class="input_info_high">
            <textarea class="width300 height70" name="descr">${cost.descr}</textarea>
            <div class="validate_msg_short">100长度的字母、数字、汉字和下划线的组合</div>
        </div>
        <div class="button_info clearfix">
            <input type="submit" value="保存" class="btn_save" />
            <input type="button" value="取消" class="btn_save" onclick="history.back()" />
        </div>
    </form>
</div>
<!--主要区域结束-->
<div id="footer">
    <span>[培养适应社会经济发展的高层次技术技能人才、复合型人才]</span>
    <br />
    <span>版权所有 © 2000-2017 中软国际有限公司，保留一切权利。</span>
</div>
</body>
</html>

