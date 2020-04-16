var express = require('express');
var fs = require('fs');
var app = express();

// app.use('/', express.static('/'))
app.use('/public/', express.static('./public/'));

app.engine('html', require('express-art-template'));

// 任何请求都会进来
// 不关心路径
// 中间件本身是一个方法， 该方法接受三个参数
// Requeset 请求对象
// Response 响应对象
// next 下一个中间件 [关门送客]
// 也可以添加路径过滤选项
// 有匹配问题，符合才能next下一条
// ues只匹配开头就行，get严格匹配

// 配置一个404的处理
app.use(function(req, res){
    // res.send('123')
    res.render('404.html')
    next()
})

//全局错误处理中间件
app.use(function(err, req, res, next){

})

app.listen(3000, function(){
    console.log('running');
    
})