let arrPush = {}

//如下 数组常用的方法
let arrayMethods = [
  'push',
  'pop',
  'shift',
  'unshift',
  'splice',
  'sort',
  'reverse',
]

// 对数组方法重写
arrayMethods.forEach((method) => {
  const original = Array.prototype[method]
  arrPush[method] = function () {
    console.log(this)
    return original.apply(this, arguments)
  }
})

const testPush = []

testPush.__proto__ = arrPush

testPush.push(1)

testPush.push(2)
