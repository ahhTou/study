//1.Promise的使用
new Promise((reslove, reject) => {
	setTimeout(() => {
		reslove()
	}, 1000)
})
	.then(() => {
		console.log('hello world')
	})
	.catch((err) => {})

//02-Promise的另外处理形式
new Promise((resolve, reject) => {
	setTimeout(() => {
		// resolve('helloWorld')
		reject('error')
	})
}).then(
	(data) => {
		console.log('data')
	},
	(err) => {
		console.log(err)
	}
)
//03-Promise的链式调用
new Promise((reslove, reject) => {
	setTimeout(() => {
		reslove()
	}, 100)
})

	.then(() => {
		console.log('第一次调用')

		return new Promise((reslove, reject) => {
			setTimeout(() => {
				reslove()
			})
		})
	})

	.then(() => {
		console.log('第二次调用')

		return new Promise((reslove, reject) => {
			setTimeout(() => {
				reslove()
			})
		})
	})

	.then(() => {
		console.log('第三次调用')
	})

//04-Promise的链式调用 其二
new Promise((resolve) => {
	setTimeout(() => {
		resolve('aaa')
	}, 300)
})
	.then((res) => {
		console.log(res, '第一层的代码')

		return res + '111'
	})
	.then((res) => {
		console.log(res, '处理结果')
	})
//05-Promise的all方法使用
Promise.all([
	new Promise((resolve, reject) => {
		setTimeout(() => {
			resolve('r1')
		}, 1000)
	}),
	new Promise((resolve, reject) => {
		setTimeout(() => {
			resolve('r2')
		}, 1000)
	}),
]).then((results) => {
	console.log(results)
})
//05-Promise的race方法————竞速

