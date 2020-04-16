var express = require('express');

// http.createServer
var app = express();


//公开指定目录
app.use('/public/', express.static('./public/'));

//a表示public的别名
// app.use('/a/', express.static('./public/'));
// 省略第一个参数时，直接输入名称就行
// app.use(express.static('./public/'));

//主页
app.get('/',function(req,res){

    res.send('hello express!');
})

//关于我
app.get('/about',function(req,res){
    res.send('about Me');
})

app.get('/login',function(req,res){
    res.send('about Me');
})

// 相当于 server.listen
app.listen(3000, function(){
    console.log('app is running at port 3000.');
    
})