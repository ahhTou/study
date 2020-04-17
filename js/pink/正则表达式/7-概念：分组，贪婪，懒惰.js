//分组 （？<groupname>exp)

var str = "aabab";
//贪婪
var reg1 = /a.*b/;
console.log("贪婪", str.match(reg1));
//懒惰
var reg2 = /a.?b/;
console.log("懒惰", str.match(reg2));
/*
  *？ 重复任意次，尽可能少
  +? 重复1次或更多次，尽可能少
  ？？ 重复0次或1次，但尽可能少
  {n,m}?重复n到m,但尽可能少
  {n,}?重复n次以上，但尽可能少重复
*/
