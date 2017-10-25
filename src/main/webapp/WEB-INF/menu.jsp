<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/23 0023
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="navi">
<%--由于menu.jsp是被其他jsp共用的，因此无法预估目前是在哪个请求中调用它，所以此处适合使用绝对路径--%>
    <ul id="menu">
        <li><a href="toIndex.do" class="index_off"></a></li>
        <li><a href="" class="role_off"></a></li>
        <li><a href="" class="admin_off"></a></li>
        <li><a href="findCost.do" class="fee_off"></a></li>
        <li><a href="" class="account_off"></a></li>
        <li><a href="" class="service_off"></a></li>
        <li><a href="" class="bill_off"></a></li>
        <li><a href="" class="report_off"></a></li>
        <li><a href="" class="information_off"></a></li>
        <li><a href="" class="password_off"></a></li>
    </ul>
</div>