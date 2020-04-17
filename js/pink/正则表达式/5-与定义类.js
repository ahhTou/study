//匹配数字
var reg1 = /\d/;
console.log("/d", reg1.test("123"));
var reg1 = /\D/;
console.log("/D", reg1.test("123"));

// \w是匹配 所有 数字 字母 下划线
var reg1 = /\w/;
console.log("/w", reg1.test("123dfdsfwWW"));

// \W是匹配 所有非 数字 字母 下划线

//s 匹配空格，相当于[\t\r\n\v\f]

//S匹配所有非空字符 相当于[^\t\r\n\v\f]

//或者 | 
// 010-12345678
//0530-1234567
var reg2 = /^\d{3}-\d{8}|\d{4}-\d{7}$/
var reg3 = /^\d{3,4}-\d{7,8}$/
console.log(reg2.test('010-12345678'))
console.log(reg2.test('0103-2345678'))
console.log(reg3.test('010-12345678'))
console.log(reg3.test('0103-2345678'))