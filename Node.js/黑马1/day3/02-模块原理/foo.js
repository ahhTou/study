// //node日默认有
// var module = {
//     exprots:{

//     }
// }

// //默认在代码的最后一句有

// return module.exprots;

//结果为ture 原因是 有一句代码 exports = module.exports
console.log(exports === module.exports);

exports  = 'hello';

console.log();

