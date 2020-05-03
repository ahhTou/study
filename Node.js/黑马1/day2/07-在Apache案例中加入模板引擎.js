var http = require('http');
var fs = require('fs');
var template = require('art-template')

var server = http.createServer();

server.on('request',function(req,res){
    //读取文件
    fs.readFile('./resource/index.html',function(err,data){
        if(err){
            return res.end('index没有找到')
        }
        //读取路径
        fs.readdir('./', function(err, files){
            if(err){
                return res.end('404 Not Found')
            }
            var  htmlStr = template.render(data.toString(),{
                title:"标题是这个",
                // files:files
            })
            res.end(htmlStr);
        })


    })


})

server.listen(4000,function(){
    console.log('Server is running...');
})