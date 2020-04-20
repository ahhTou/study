var reg = /((?<=\s+)\d(?=\s+))|((?<=\d)\d(?=\d))/g
//匹配前后都有空格的 前面有空格(?<=\s+) 后面有空格(?=\s+)
var str = '1 2 3 765 9'
console.log(str.match(reg))
