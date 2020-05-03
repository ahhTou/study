// 参考文章：https://www.cnblogs.com/tugenhua0707/p/10261170.html

const obj = new Object()

obj.name = 'ahhtou'

obj.say = () => {
  console.log('恩啊啊啊啊')
}

console.log('第一次', obj)

//value
Object.defineProperty(obj, 'name', {
  value: 'ahhtouPro',
  writable: false,
})
console.log('value测试', obj.name)

//writable 默认为false(好像是true) false不能被重写
obj.name = 'lalala'
console.log('重写一次，默认为false', obj.name)

//enumerable 是否可枚举
Object.defineProperty(obj, 'name1', {
  value: 'ahhtouProMax',
  writable: true,
  enumerable: true,
})
for (let i in obj) {
  console.log(i)
}

//configurable 是否可以配置 默认false
Object.defineProperty(obj, 'name2', {
  value: 'toutou',
  configurable: false,
  enumerable: true,
})
console.log('删除前', obj)
delete obj.name2
console.log('删除后', obj)
