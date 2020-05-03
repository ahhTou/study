var fs = require('fs')
//普通函数——一直执行，直到结束

//async函数————能够“暂停”

async function show() {
	let time = 10000
	console.log(`异步操作在${time / 1000}秒后执行完毕`)
  let data = await fsData()
	console.log(`异步操作执行完毕,数据是${data}`)
}
let fsData = () => {
	return new Promise((resolve, rejcet) => {
		fs.readFile('./11-Promise.js', (err, data) => {
			resolve(data)
		})
	})
}

show()