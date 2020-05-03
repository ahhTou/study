// ...只能放最后一个
function show(a, b, ...c) {
	console.log(a, b, c)
}

show(1, 2, 3, 4, 5, 6)

let arr1 = [12, 5, 6]
console.log(...arr1)
show(...arr1)

let arr2 = [8, 9, 10]
//数组连接
let arr3 = [...arr1, ...arr2]
console.log(arr3)
