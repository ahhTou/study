var fs = require('fs');

function pReadFile(filePath){
    return new Promise(function (resolve, reject){
        fs.readFile(filePath,'utf8',function(err,data){
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
}

pReadFile('./data/a.txt')
    .then(function(data){
        console.log(data);
        return pReadFile('./data/b.txt');
    })
    .then(function(data){
        console.log(data);
        return pReadFile('./data/c.txt');
    })
    .then(function(data){
        console.log(data);
    })