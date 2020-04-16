//加载http 核心模块
var http = require('http')

//创建一个web服务器
//返回一个sever实例

var sever = http.createServer()

//服务器干什么

//发请求
sever.on('request',function(){
    console.log('收到客户端的请求了')
})

//绑定端口号，启动服务器
sever.listen(3000,function(){
    console.log('服务器启动成功')
})