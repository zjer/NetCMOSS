<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/23 0023
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>登陆</title>
    <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
    <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
</head>
<body class="index">
<div class="login_box">
    <form action="login.do" method="post">
        <table>
            <tr>
                <td class="login_info">账号：</td>
                <td colspan="2"><input name="adminCode" type="text" placeholder="${param.adminCode}" class="width150" /></td>
                <td class="login_error_info"><span class="required">30长度的字母、数字和下划线</span></td>
            </tr>
            <tr>
                <td class="login_info">密码：</td>
                <td colspan="2"><input name="password" type="password" placeholder="${param.password}" class="width150" /></td>
                <td><span class="required">30长度的字母、数字和下划线</span></td>
            </tr>
            <tr>
                <td class="login_info">验证码：</td>
                <td class="width70"><input name="code" type="text" class="width70" /></td>
                <%--路径后面增加了参数(随机数)，是为了欺骗浏览器，让它以为路径变了--%>
                <td><img style="margin-top: 3px;" src="createImg.do" alt="验证码" title="点击更换" onclick="this.setAttribute('src', 'createImg.do?x='+Math.random())" /></td>
                <td><span class="required"></span></td>
            </tr>
            <tr>
                <td></td>
                <td class="login_button" colspan="2">
                    <%--1.表单有onsubmit事件，点击submit就是为了触发这个事件，可以提交表单；
                    2.表单还有submi()，调此方法一样可以提交表单；
                    3.在超链接上可以写js，但是必须声明。
                        语法：javascript:js代码
                        docuement.forms获取页面上所有的from
                    相当于getElementByTagName("form")；只有form可这样--%>
                    <a href="javascript:document.forms[0].submit();"><img src="images/login_btn.png" /></a>
                </td>
                <td><span class="required">${error}</span></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
