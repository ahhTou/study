//map -> 映射

let arr = [68, 53, 12, 98, 65]

// let arr2 = arr.map(function (item) {
// 	if (item >= 60) {
// 		return '及格'
// 	} else {
// 		return '不及格'
// 	}
// })
let arr2 = arr.map((item) => (item >= 60 ? '及格' : '不及格'))

console.log(arr2)
