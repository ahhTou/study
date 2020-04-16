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

var User = mongoose.model('user', uesrSchema);

var newUser = 'admin01'

User.findOne({
    username : newUser 
})
    .then(function(ret) {
        console.log(ret);
        if(ret){
            console.log('用户存在');
        }else{
            console.log('用户不存在');
            return new User({
                username : newUser,
                password : '123456',
                email:'admin@admin.com'
            }).save()
        }
    })
    .then(function(ret){
        if(ret){
            console.log('创建成功');
        }else{
            console.log('创建失败');
        }
    })