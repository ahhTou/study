json = {
	a: 15,
	b: 5,
}
let arr = [1, 2]
//两边结构必须一样
// let {a,b} =[12,6] 错误
// let {a,b} ={12,6} 错误
var { a, b } = json
let [c, d] = arr
console.log(a, b, c, d)


	//交换值
var [a, b] = [b, a]
console.log(a, b)
