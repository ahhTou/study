var regexp = new RegExp(/123/)
console.log(regexp)

var rg = /123/
console.log(rg)

//test检查
console.log('reg= /abc/')
console.log(rg.test('123'))
console.log(rg.test('abc'))
console.log('==========================')
var rg = /123/ //正则表达式不需要加引号，不管是数值还是字符型
// /abc/  标识包含abc就行

console.log('reg= /^abc/')
var reg = /^abc/
console.log('abc', reg.test('abc'))
console.log('abcd', reg.test('abcd'))
console.log('aabc', reg.test('aabc'))
console.log('==========================')

console.log('reg= /^abc$/')
var reg = /^abc$/ //只能有abc
console.log('abc', reg.test('abc'))
console.log('abcd', reg.test('abcd'))
console.log('aabc', reg.test('aabc'))
console.log('abcabc', reg.test('abcabc'))
console.log('==========================')

let aaa = /^老.*了$/

console.log('正则表达式测试？？？', aaa.test('老傻逼了'))
