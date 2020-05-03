//npm init -y
//npm install --save express
//npm install --save art-template
//npm install --save express-art-template
//npm install --save boostrap
//npm i bootstrap@3.3.7 -S
//npm install --sabe body-parser

var express = require('express');
var app = express();
var router =  require('./router')
var bodyParser = require('body-parser');

app.use('/node_modules/', express.static('./node_modules/'));
app.use('/public/', express.static('./public/'));

//配置模板引擎
app.engine('html', require('express-art-template'));

//配置body-parser
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

//配置模板引擎 和 body-parse 一定要在挂载router之前
app.use(router);


// (其他方法)启动路由
// router(app);

app.listen(3000, function(){
    console.log('running');
})
