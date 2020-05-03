//引包
const mongoose = require('mongoose');

mongoose.connect('mongodb://localhost:27017/test',
{useNewUrlParser: true, useUnifiedTopology: true});

//创建一个模板，在代码中设计自己的数据库
const Cat = mongoose.model('Cat', { name: String });

const kitty = new Cat({ name: 'Zildjian' });

//持久化保存kitty实例
kitty.save().then(() => console.log('meow'));