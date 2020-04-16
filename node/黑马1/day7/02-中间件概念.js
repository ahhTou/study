//中间件
var http = require('http')
var url = require('url')

var server = http.createServer(function(req, res){
    //
    var urlObj = url.parse(req.url, ture)

    req.body = {
        
    }
})



server.listen(3000, function(){
    console.log('running')
})