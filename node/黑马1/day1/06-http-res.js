var http = require('http')


var sever = http.createServer()

//request 请求事件处理函数，需要两个参数
// Request 请求对象
// Response 响应
sever.on('request',function(request,response){
    console.log('收到客户端的请求了,请求路径是:'+request.url)
    //response 对象只有一个方法 write，可用来发送响应数据
    //wirte 可以多次使用，但是最后一定要有end来结束
    response.write('hello')
    response.end()
    
})

sever.listen(3000,function(){
    console.log('服务器启动成功,http://127.0.0.1:3000/')
})