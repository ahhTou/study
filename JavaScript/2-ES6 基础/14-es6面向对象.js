class Person {
	constructor(name, age) {
		this.name = name
		this.age = age
	}
	showName() {
		console.log('名字是', this.name)
	}
	showAge() {
		console.log('年龄是', this.age, '岁')
	}
}

let p = new Person('ahhTou', 20)
p.showName()
p.showAge()

//继承
class Worker extends Person {
	constructor(name, age, job) {
		//父类，超类
		super(name, age)
		this.job = job
	}
	showJob() {
		console.log('工作是', this.job)
	}
}

let w = new Worker('ahhTou', 20, 'student')
w.showJob()
w.showAge()
w.showName()
