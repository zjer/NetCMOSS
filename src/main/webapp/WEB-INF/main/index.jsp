<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/23 0023
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>首页</title>
    <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
    <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
</head>
<body class="index">
<!--导航区域开始-->
<div id="index_navi">
    <%--<%@ include file="../menu.jsp"%>--%>
    <c:import url="../menu.jsp"></c:import>
</div>
</body>
</html>

