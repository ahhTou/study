var htttp = require('http');
var server = htttp.createServer();

server.on('request',function(req,res){
    // 在服务端默认发送的数据，其实是 utf-8 编码的内容
    // 但是浏览器不知道 你是 utf8 编码的内容 
    // 浏览器 在不知道服务器响应的内容和编码的情况下会按照当前操作系统的默认编码去解析
    // 中文操作系统默认是jbk
    // 解决方法是告诉浏览器 正确的编码
    // 
    var url = req.url;
    if(url == '/plain'){
        res.setHeader('Content-Type','text/plain; charset=UTF-8')
        res.end('hello 世界');
    }else if( url == '/html'){
        res.setHeader('Content-Type','text/plain; charset=UTF-8')
        res.end('<p> hello world<a href=""> 点我 </a> </p>');
    }
})

server.listen(3000,function(){
    console.log('Server is running...');
})