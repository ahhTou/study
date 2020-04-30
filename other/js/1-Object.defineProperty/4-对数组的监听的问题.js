const obj = {}

let initValue = 'ahhtou'

Object.defineProperty(obj, 'name', {
  set: function (value) {
    console.log('set方法被执行了', value)
    initValue = value
  },
  get: function () {
    return initValue
  },
})

console.log(obj.name)

obj.name = []

obj.name = [1, 2, 3]

//不执行
obj.name[0] = [2]

//不执行
obj.name.push(4)

//不执行
obj.name.length = 5
