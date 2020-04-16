var http = require('http');
var fs = require('fs');

var server = http.createServer();

server.on('request',function(req,res){
    var url = req.url;
    var filePath =  'index.html';
    
    if(url !== '/'){
        filePath = url;
    }
    fs.readFile('./'+filePath, function(err, data){
        if(err){
            return res.end('404 Not Found')
        }
        res.end(data)
    })
})

server.listen(4000,function(){
    console.log('Server is running...');
})