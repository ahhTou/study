<%--
  Created by IntelliJ IDEA.
  User: 11979
  Date: 2020/5/20
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>入门程序成功了</h3>

<%--${requestScope}--%>
    day1: ${msg}
${ sessionScope }
<br/>
day2:${user.username}

U2_4:<% System.out.println("success.jsp执行了"); %>
</body>
</html>
