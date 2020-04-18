let arr = [68, 53, 12, 98, 65]
//求平均数
let res = arr.reduce((tmp, item, index) => {
	console.log(index + ':' + tmp + ':' + item)
	if (index == arr.length - 1) {
		return (tmp + item) / arr.length
	} else {
		return tmp + item
	}
})
console.log(res)
