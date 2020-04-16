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

//将文档结构发布为模型
//返回 : 模型构造函数
var User = mongoose.model('user', uesrSchema);

var admin = new User({
    username : 'admin2',
    password : '123456',
    email:'admin@admin.com'
})

admin.save().then(() => console.log('创建成功'));