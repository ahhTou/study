var msg = "我是模块A";

// 导出方式 1
export{
    msg
}
// 导出方式二
export let msg2 = "我被方式2导出了"

// 导出函数
export function say(){
    console.log('我是在函数中log的');
    
}

//导出类
export class sayAny{
   sayAny(){
        console.log('我是在类中的函数中log的');
    }
}

//default 不能多个
var ass = "我是default导出的";
export default ass;