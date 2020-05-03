// * 相当于>=0次
console.log('')
var reg1 = /^a*$/
console.log('reg1 = /^a*$/')
console.log('aaaa', reg1.test('aaaa'))
console.log('', reg1.test(''))

// + 相当于>=1次
console.log('')
var reg2 = /^a+$/
console.log('reg2 = /^a+$/')
console.log('aaaa', reg2.test('aaaa'))
console.log('', reg2.test(''))

// ? 相当于1 or 0次
console.log('')
var reg3 = /^a?$/
console.log('reg3 = /^a?$/')
console.log('', reg3.test(''))
console.log('a', reg3.test('a'))
console.log('aa', reg3.test('aa'))

//{3} 重复3次
console.log('')
var reg4 = /^a{3}$/
console.log('reg4 = /^a{3}$/')
console.log('', reg4.test(''))
console.log('a', reg4.test('a'))
console.log('aaa', reg4.test('aaa'))
//{3， }大于3次
console.log('')
var reg4 = /^a{3,}$/
console.log('reg4 = /^a{3,}$/')
console.log('aa', reg4.test('aa'))
console.log('aaa', reg4.test('aaa'))
console.log('aaaa', reg4.test('aaaa'))
//{3.16}大于3，且小于16
console.log('')
var reg3 = /^a{3,16}$/
console.log('reg3 = /^a{3,16}$/')
console.log('aaaaaaa', reg3.test('aaaaaa'))
console.log('aa', reg3.test('aa'))
console.log(
  'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa',
  reg3.test(' v aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa')
)
