//node 中没有全局作用域
//不能省略'./'
//可以省略后缀名
console.log("开始执行a");

var ret = require('./b.js.js')


console.log(ret);

console.log(ret.add(1,4));

