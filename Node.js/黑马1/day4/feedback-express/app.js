var express = require('express');

//npm install body-parser
var bodyParser = require('body-parser');

var app = express();



app.use('/public/',express.static('./public/'));
app.use(bodyParser.urlencoded({ extended:false }));
app.use(bodyParser.json());

//配置使用art-template模板引擎
//npm install express-art-template
app.engine('html', require('express-art-template'));

//默认去views目录

//修改默认views路径
//app.set('views',render函数的默认路径)



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
];


app.get('/', function(req, res){
    
    res.render('index.html',{
        comments:comments,
    });
})

app.get('/post', function(req, res){
    res.render('post.html');
})

app.post('/post', function(req, res){
    //npm install body-parser

    var comment = req.body;
    comment.date = '2018-1-1';
    comments.unshift(comment);
    res.redirect('/');

})

// app.get('/pinglun', function(req, res){

//     var comment = req.query;
//     comment.date = '2018-1-1';
//     comments.unshift(comment);
//     res.redirect('/');

//     // res.statusCode = 302;
//     // res.setHeader('location','/')
// })

app.get('/admin', function(req, res){
    res.render('admin/index.html',{
        title:'管理系统',
    });
})

app.listen(3000, function(){
    console.log('running')
})