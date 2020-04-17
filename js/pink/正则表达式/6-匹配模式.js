var rg1 = /激情|sexy/;
var rg2 = /激情|sexy/gi;//g 全局 i忽略大小写
var text = "我上课很有激情，生活也有激情,我也是个Sexy的人";
console.log(text.replace(rg1, "**"));
console.log(text.replace(rg2, "**"));
