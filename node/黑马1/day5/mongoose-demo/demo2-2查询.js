var mongoose = require('mongoose');

var Schema = mongoose.Schema;

//连接数据库不需要存在，插入第一条数据库时会自动创建
mongoose.connect('mongodb://localhost/itcast',
{useNewUrlParser: true, useUnifiedTopology: true})

//约束，文档结构
var uesrSchema = new Schema({
    username: {
        type: String,
        required: true, //必须有
    },
    password: {
        type: String,
        required: true,
    },
    email: {
        type: String,
    }

});

//将文档结构发布为模型find
//返回 : 模型构造函数
var User = mongoose.model('user', uesrSchema);

//查询全部
// User.find(function(err,ret){
//     if(err){
//         console.log('查询失败');
        
//     }else{
//         console.log(ret);
        
//     }
// })


// //查询符合条件，返回一个数组
// User.find({
//     username:'admin',},function(err,ret){
//         if(err){
//             console.log('查询失败');
//         }else{
//             console.log(ret); 
//         }
//     })


//只查找一个，返回一个对象
User.findOne({
    username:'admin',},function(err,ret){
        if(err){
            console.log('查询失败');
        }else{
            console.log(ret); 
        }
    })