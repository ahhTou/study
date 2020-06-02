<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<body>
<h2>Hello World!</h2>
<a></a>
<script>
        console.log("123")
        $.ajax({
            // 编写json格式，设置属性和值
            url: 'getUserBaseDataByUsername',
            contentType: 'application/json;charset=UTF-8',
            // data: '{"username":"ahhTou", "password":"123", "age":"20"}',
            data: 'root',
            dataType: 'json',
            type: 'post',
            success: function (data) {
                console.log(data)
            },
        })
</script>
</body>
</html>
