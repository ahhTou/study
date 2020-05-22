<%--
  Created by IntelliJ IDEA.
  User: 11979
  Date: 2020/5/21
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--   常用注解--%>
<a href="anno/testRequestParam?name=ahhTou">RequestParam</a>

<form action="anno/testRequestBody" method="post">
    name:<input type="text" name=name"><br/>
    age:<input type="text" name="age"><br/>
    <input type="submit" name="提交"><br/>
</form>

<br/>
<a href="anno/testPathVariable/10">user/testVariable</a>
<br/>
<a href="anno/testRequestHeader">user/testRequestHeader</a>
<br/>
<a href="anno/testCookieValue">user/testCookieValue</a>
<br/>
<form action="anno/testModelAttribute" method="post">
    name:<input type="text" name="name"><br/>
    age:<input type="text" name="age"><br/>
    <input type="submit" name="提交"><br/>
</form>

<a href="anno/testSessionAttribute">user/tesSessionAttribute</a>
<a href="anno/getSessionAttribute">user/tesSessionAttribute</a>
<a href="anno/delSessionAttribute">user/delSessionAttribute</a>
</body>
</html>
