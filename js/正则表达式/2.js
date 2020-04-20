var rg1 = /[abc]/; //出现abc一个就行了
console.log("rg = /[abc]/");
console.log("andy", rg1.test("andy"));
console.log("123", rg1.test("123"));
console.log("================");

var rg2 = /^[abc$]$/; //只能出现其中一个
console.log("rg = /[^abc$]/");
console.log("andy", rg2.test("andy"));
console.log("abc", rg2.test("abc"));
console.log("aa", rg2.test("aa"));
console.log("================");

var rg3 = /^[a-z]$/; //只能出现其中一个a-z之中
console.log("rg =  /^[a-z]$");
console.log("s", rg3.test("s"));
console.log("abc", rg3.test("abc"));
console.log("Z", rg3.test("Z"));
console.log("================");

var rg3 = /^[a-zA-Z0-9-_]$/; //
console.log("rg =  /^[a-zA-Z0-9]$/");
console.log("s", rg3.test("s"));
console.log("abc", rg3.test("abc"));
console.log("Z", rg3.test("Z"));
console.log("3", rg3.test("3"));
console.log("_", rg3.test("_"));
console.log("================");

var rg4 = /^[^a-zA-Z0-9-_]$/; //^取反
console.log("rg = /^[^a-zA-Z0-9-_]$");
console.log("s", rg4.test("s"));
console.log("abc", rg4.test("abc"));
console.log("Z", rg4.test("Z"));
console.log("3", rg4.test("3"));
console.log("_", rg4.test("_"));
console.log("[", rg4.test("["));
console.log("================");
