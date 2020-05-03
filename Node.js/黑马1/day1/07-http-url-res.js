var http = require('http')

var server = http.createServer()

server.on('request',function(req , res){
    console.log('收到请求，请求路径是:'+ req.url)
    var url = req.url
    if(url === '/'){
        res.end('homePage')
    }else if(url ==='/login'){
        res.end('Login')
    }else{
        res.end('404 Not Found')
    }
})

server.listen(3000,function(){
    console.log('服务器启动成功')
    
})