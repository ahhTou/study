var fs = require('fs');

//创建promise容器,promise 不是异步，里面的是异步精髓
var p1 = new Promise(function (resolve, reject){
    fs.readFile('./data/a.txt','utf8',function(err,data){
        if(err){
            //失败
            // console.log('err');
            reject(err);
        }else{
            //成功了
            // console.log(data);
            resolve(data);
        }
    })
})

var p2 = new Promise(function (resolve, reject){
    fs.readFile('./data/b.txt','utf8',function(err,data){
        if(err){
            //失败
            // console.log('err');
            reject(err);
        }else{
            //成功了
            // console.log(data);
            resolve(data);
        }
    })
})

var p3 = new Promise(function (resolve, reject){
    fs.readFile('./data/c.txt','utf8',function(err,data){
        if(err){
            //失败
            // console.log('err');
            reject(err);
        }else{
            //成功了
            // console.log(data);
            resolve(data);
        }
    })
})

//then 方法接受的就是resolve(data)
p1
    .then(function(data){
        console.log(data);
        return p2;
    },function(err){
        console.log(err);
        
    })
    .then(function(data){
        console.log(data);
        return p3;
    },function(err){
        console.log(err);
        
    })
    .then(function(data){
        console.log(data)
    },function(err){
        console.log(err);
        
    })
