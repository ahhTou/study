//使用 getter/setter 时 不能使用 writable 和 value

let obj = {}
let initValue = 'ahhtou'
Object.defineProperty(obj, 'name', {
  // 使用 obj.name 时候会调用get函数
  get: function () {
    console.log('get')
    return initValue
  },
  set: function (value) {
    console.log('set')
    initValue = value
  },
})

//调用get函数
console.log(obj.name)

//调用set函数
obj.name = 'ahhtouPro'
console.log(obj.name)
