<%--
  Created by IntelliJ IDEA.
  User: 11979
  Date: 2020/5/22
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<%--    <script src="js/jquery-3.5.1.min.js"></script>--%>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <script>
        $(function () {
            $("#btn").click(function () {
                // alert("hello btn")
                $.ajax({
                    // 编写json格式，设置属性和值
                    url:"day2/user/testAjax",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"username":"ahhTou", "password":"123", "age":"20"}',
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        console.log(data.username)
                        console.log(data.password)
                        console.log(data.age)
                    }

                })
            });
        })
    </script>
</head>
<body>
    <a href="day2/user/testString">testString</a>

    <br/>

    <a href="day2/user/testVoid">testVoid</a>

    <br/>

    <a href="day2/user/testModelAndView">testModelAndView</a>

    <br/>

    <a href="day2/user/testForwardOrRedirect">testForwardOrRedirect</a>

    <br/>
    <button id="btn">按钮</button>


</body>
</html>
