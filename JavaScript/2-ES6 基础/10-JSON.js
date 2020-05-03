let json = { a: 12, b: 5, name: 'ahhtou' }
console.log(json)
//变成字符串（标准）
console.log(JSON.stringify(json))

//str必须是标准json格式
let str = '{"a":12,"b":5,"name":"ahhtou"}'
let json2 = JSON.parse(str) //json格式