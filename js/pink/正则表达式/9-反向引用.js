var str = 'alipay alipay  sdfsdf' 
var reg1 = /\b\w+/
var reg2 = /\b(\w+)\s+(\1)\s+/
// \1 会引用前面的分组
console.log(reg1.exec(str))
console.log(reg2.exec(str))
