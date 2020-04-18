function f1() {}

f2 = () => {}

let arr = [12, 8, 37, 26, 9]

arr.sort(function (n1, n2) {
	return n1 - n2
})
console.log(arr)

//箭头函数简写

let arr2 = [12, 8, 37, 26, 9]
arr2.sort((n1, n2) => {
	return n1 - n2
})
console.log(arr2)

//简写
let f3 = (a1) => a1
//等同于
function f4(a2) {
	return a2
}


//修正this