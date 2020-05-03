//npm init -y
//npm install --save express
//npm install --save art-template
//npm install --save express-art-template
//npm install --save boostrap
//npm i bootstrap@3.3.7 -S
//npm install --sabe body-parser

var express = require('express');
var path = require('path');

var app = express();

//path.join 拼接路径
//__dirname 当前模块路径
//文件操作是相对于node执行目录
//模块加载是相对于当前文件
app.use('/public/',express.static(path.join(__dirname,'./public/')));

app.use('/node_modules/',express.static(path.join(__dirname,'/node_modules/')));

app.engine('html', require('express-art-template'))

app.get('/', function(req, res){
    res.render('index.html', {
        name: '张三'
    })
})

app.listen(3000,function (res) {
    console.log('running');
})