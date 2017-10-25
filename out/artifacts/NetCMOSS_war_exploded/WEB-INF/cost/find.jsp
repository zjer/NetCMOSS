<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/20 0020
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>资费信息列表</title>
    <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
    <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
    <script language="javascript" type="text/javascript">
        //排序按钮的点击事件
        function sort(btnObj) {
            if (btnObj.className == "sort_desc")
                btnObj.className = "sort_asc";
            else
                btnObj.className = "sort_desc";
        }

        //启用
        function startFee() {
            var r = window.confirm("确定要启用此资费吗？资费启用后将不能修改和删除。");
            document.getElementById("operate_result_info").style.display = "block";
        }
        //删除
        function deleteFee() {
            var r = window.confirm("确定要删除此资费吗？");
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
    <form action="" method="">
        <!--排序-->
        <div class="search_add">
            <div>
                <!--<input type="button" value="月租" class="sort_asc" onclick="sort(this);" />
                <input type="button" value="基费" class="sort_asc" onclick="sort(this);" />
                <input type="button" value="时长" class="sort_asc" onclick="sort(this);" />-->
            </div>
            <input type="button" value="增加" class="btn_add" onclick="location.href='toAddCost.do';" />
        </div>
        <!--启用操作的操作提示-->
        <div id="operate_result_info" class="operate_success">
            <img src="images/close.png" onclick="this.parentNode.style.display='none';" />
            删除成功！
        </div>
        <!--数据区域：用表格展示数据-->
        <div id="data">
            <table id="datalist">
                <tr>
                    <th>资费ID</th>
                    <th class="width100">资费名称</th>
                    <th>基本时长</th>
                    <th>基本费用</th>
                    <th>单位费用</th>
                    <th>创建时间</th>
                    <th>开通时间</th>
                    <th class="width50">状态</th>
                    <th class="width200"></th>
                </tr>
                <c:forEach items="${costs}" var="c">
                    <tr>
                        <td>${c.costId}</td>
                        <td><a href="fee_detail.html">${c.name}</a></td>
                        <td>${c.baseDuration}</td>
                        <td>${c.baseCost}</td>
                        <td>${c.unitCost}</td>
                        <td><fmt:formatDate value="${c.creatime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </td>
                        <td>${c.startime}</td>
                        <td>
                            <c:if test="${c.status == '0'}">开通</c:if>
                            <c:if test="${c.status == '1'}">暂停</c:if>
                        </td>
                        <td>
                            <input type="button" value="启用" class="btn_start" onclick="startFee();" />
                            <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateCost.do?id=${c.costId}';" />
                            <input type="button" value="删除" class="btn_delete" onclick="location.href='toDelCost.do?id=${c.costId}';" />
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <p>业务说明：<br />
                1、创建资费时，状态为暂停，记载创建时间；<br />
                2、暂停状态下，可修改，可删除；<br />
                3、开通后，记载开通时间，且开通后不能修改、不能再停用、也不能删除；<br />
                4、业务账号修改资费时，在下月底统一触发，修改其关联的资费ID（此触发动作由程序处理）
            </p>
        </div>
        <!--分页-->
        <div id="pages">
            <a href="#">上一页</a>
            <a href="#" class="current_page">1</a>
            <a href="#">2</a>
            <a href="#">3</a>
            <a href="#">4</a>
            <a href="#">5</a>
            <a href="#">下一页</a>
        </div>
    </form>
</div>
<!--主要区域结束-->
<div id="footer">
    <p>[培养适应社会经济发展的高层次技术技能人才、复合型人才]</p>
    <p>版权所有 © 2000-2017 中软国际有限公司，保留一切权利。</p>
</div>
</body>
</html>
