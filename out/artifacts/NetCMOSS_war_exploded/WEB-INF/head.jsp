<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/24 0024
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="header">
    <img src="images/logo.png" alt="logo" class="left"/>
    <%--EL表达式的默认取值范围是：
        page、request、session、application依次取值；
        EL表达式也可以从cookie取值--%>
    <span>${adminCode}</span><a href="javascript:void (0);" onclick="location.href='logout.do';">[退出]</a>
</div>