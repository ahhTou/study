//以函数形式来写对象
function Person(name, age) {
	this.name = name
	this.age = age
}
Person.prototype.showName = function () {
	console.log('名字是', this.name)
}
Person.prototype.showAge = function () {
	console.log('年龄是', this.age, '岁')
}

let p = new Person('ahhTou', 20)
p.showAge()
p.showName()

//继承
function Worker(name, age, job) {
	Person.call(this, name, age)
	this.job = job
}
Worker.prototype = new Person()
Worker.prototype.constructor = Worker
Worker.prototype.showJob = function () {
	console.log('工作是', this.job)
}

let w = new Worker('ahhTou', 20, 'student')
w.showJob()
w.showAge()
w.showName()
