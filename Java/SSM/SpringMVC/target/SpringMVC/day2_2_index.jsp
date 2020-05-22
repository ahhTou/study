<%--
  Created by IntelliJ IDEA.
  User: 11979
  Date: 2020/5/22
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>文件上传</h3>
    <form
            action="day2_2/user/fileUpload1"
            method="post"
            enctype="multipart/form-data"
    >
    选择文件：<input type="file" name="upload"><br/>
        <input type="submit" value="上传">
    </form>

    <h3>Spring上传</h3>
    <form
            action="day2_2/user/fileUpload2"
            method="post"
            enctype="multipart/form-data"
    >
    选择文件：<input type="file" name="upload2"><br/>
        <input type="submit" value="上传">
    </form>

    <h3>跨服务器上传</h3>
    <form
            action="day2_2/user/fileUpload3"
            method="post"
            enctype="multipart/form-data"
    >
    选择文件：<input type="file" name="upload3"><br/>
        <input type="submit" value="上传">
    </form>
</body>
</html>
