var htttp = require('http');
var fs = require('fs');

var server = htttp.createServer();

server.on('request',function(req,res){
    var url = req.url;
    if(url ==='/'){
        fs.readFile('./resource/index.html', function(err,data){
                if(err){
                    res.setHeader('Content-Type', 'text/plain; charset=utf-8')
                    res.end('文件读取失败，请稍后重试!');
                }else{
                    res.setHeader('Content-Type', 'text/html; charset=utf-8')
                    res.end(data);
                }
        });
        // res.end('');
    }else if(url ==='/ab'){
        //url：统一资源定位符
        fs.readFile('./resource/ab2.jpg', function(err,data){
            if(err){
                res.setHeader('Content-Type', 'text/plain; charset=utf-8')
                res.end('文件读取失败，请稍后重试!');
            }else{
                //图片不要编码
                res.setHeader('Content-Type', 'image/jpeg')
                res.end(data);
            }
    });
    }
})

server.listen(3000,function(){
    console.log('Server is running...');
})