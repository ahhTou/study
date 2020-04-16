var http = require('http');
var fs = require('fs');
var url = require('url')
//npm install art-template
var template = require('art-template');

var comments = [{
        name: '1',
        message: '1',
        dateTime: '1',
    },
    {
        name: '1',
        message: '1',
        dateTime: '1',
    },
    {
        name: '1',
        message: '1',
        dateTime: '1',
    }
]

http
    .createServer(function (req, res) {
        //得到解析后的url
        var parseObj = url.parse(req.url,true);
        //得到纯路径
        var pathname = parseObj.pathname;
        //跳转主页
        if (pathname === '/') {
            fs.readFile('./view/index.html', function (err, data) {
                if (err) {
                    return res.end('没有找到');
                }

                var htmlStr = template.render(data.toString(),{
                    comments: comments
                })
                res.end(htmlStr);
            })
        } 
        //发送评论页面
        else if (pathname === '/post') {
            fs.readFile('./view/post.html', function (err, data) {
                if (err) {
                    return res.end('404 not found');
                }
                res.end(data);
            })

        } 
        //其他公共界面
        else if (pathname.indexOf('/public/') === 0) {
            fs.readFile('.' + pathname, function (err, data) {
                if(err){
                    return res.end('404 Not Found');
                }
            })
            
        }
        //发送评论
        else if(pathname === '/pinglun'){
            var comment = parseObj.query;
            comment.dateTime = '2017-11-2 17:11:22';
            comments.unshift(comment);
            //302 临时重定向
            res.statusCode = 302;
            res.setHeader('location','/');
            res.end();

        }
        //404界面
        else{
            fs.readFile('./view/404.html', function (err, data) {
                if (err) {
                    return res.end('404 not found');
                }
                res.end(data);
            })
            
        }

    })
    .listen(3000, function () {
        console.log("running...");
        
    })