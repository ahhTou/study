var express = require('express')
var session = require('express-session')

var app = express()

app.use(session({
    //加密字符串,类似于md5加一串字符数
    secret: 'ahhTouSafeString',
    resave: false,
    //未初始化保存,是否使用session都会给你一个session
    saveUninitialized: true
}))

app.get('/', function (req, res) {
    console.log('接收到请求了');
    req.session.isLogin = true;
    res.send(req.session.isLogin)
})
app.get('/a', function (req, res) {
    res.send('send:')
    res.send(req.session.isLogin)
})


app.listen(3000, function () {
    console.log('running');
})