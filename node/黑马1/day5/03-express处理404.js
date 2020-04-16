//处理404
var express = require('express');
var app = express();

app.use(function(req, res){
    res.send('303030304000000004')
})

app.listen(3000, function(){
    console.log('running');
})