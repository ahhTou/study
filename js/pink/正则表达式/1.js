var regexp = new RegExp(/123/);
console.log(regexp);

var rg = /123/;
console.log(rg);

//test检查
console.log(rg.test('123'))
console.log(rg.test('abc'))