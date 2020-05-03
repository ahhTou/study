var fs = require('fs')
// 成功
//     data 数据
//     error null
// 失败
//     data null
//     error 错误对象
fs.readFile('./data/11.txt',function(error,data){
    //读出来的是二进制
    // console.log(data)

    if(error){
        console.log("读取文件失败");
    }else{
        console.log(data.toString()) 
    }
})  