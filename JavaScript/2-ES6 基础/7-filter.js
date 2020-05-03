let arr = [68, 53, 12, 98, 65, 83, 16]
//过滤奇数
let arr2 = arr.filter((item) => {
	// if (item % 2 == 1) {
	// 	return false
	// } else {
	// 	return true
  // }
  
  //简写
	return item % 2 === 0
})

console.log(arr2)
