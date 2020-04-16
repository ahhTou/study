var foo = 'bar'

function add(x, y){
    return x + y;
}

//多个导出
// exports.add = add;

//直接导出成员(只能单个)
module.exports = add; 

//也可以
//module.exports = {}