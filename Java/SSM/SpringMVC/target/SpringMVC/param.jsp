<%--
  Created by IntelliJ IDEA.
  User: 11979
  Date: 2020/5/20
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    请求参数绑定 --%>
<a href="param/testParam?username=ahhTou&password=123">请求参数绑定</a>

<%--    表单--%>
<form action="param/saveAccount" method="post">
    姓名：<input type="text" name="username"><br/>
    密码：<input type="text" name="password"><br/>
    金额：<input type="text" name="money"><br/>
    name:<input type="text" name="user.name"><br/>
    age:<input type="text" name="user.age"><br/>
    <input type="submit" name="提交"><br/>
</form>

</body>
</html>
