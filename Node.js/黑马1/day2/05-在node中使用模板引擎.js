var template = require('art-template');
var fs = require('fs');

tplStr = `
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>
</head>
<body>
        大家好，我叫： {{ name }}
        我今年 {{ age }} 岁了
</body>
</html>
`

fs.readFile('./06-在文件中使用template.html',function(err,data){
    if(err){
        return console.log("文件打开失败");
    }
    var ret = template.render(data.toString(),{
        name : 'jack ',
        age : 18,
    })
    
    console.log(ret);
})

