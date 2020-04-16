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

//批量更新updateMany
User.updateOne({
    username:'admin'},{username:'admin0'},function(err,ret){
        if(err){
            console.log('更新失败');
        }else{
            console.log('更新成功');
            console.log(ret);
            
        }
    })