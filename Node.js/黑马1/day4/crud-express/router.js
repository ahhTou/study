var fs = require('fs');
var express = require('express');
//挂载路由
//创建一个路由实例
var router = express.Router();

var Student = require('./students');

router.get('/students', function (req, res) {

    Student.find(function (err, students) {
        if (err) {
            return res.status(500).send('Server Err')
        }
        res.render('index.html', {
            fruits: [
                '香蕉',
                '苹果',
                '橘子',
                '西瓜'
            ],
            //字符串转化为对象
            students: students
        });
    })
})

router.get('/students/new', function (req, res) {
    res.render('new.html')
})
router.post('/students/new', function (req, res) {
    //获取
    //处理
    //
    // console.log(req.body);
    Student.save(req.body, function (err) {
        if (err) {
            return res.status(500).send('Server Err')
        }
        res.redirect('/students');
    })

})
router.get('/students/edit', function (req, res) {

    Student.findByid(parseInt(req.query.id), function (err, student) {
        if (err) {
            return res.status(500).send('Server Err');
        }
        res.render('edit.html', {
            student: student
        })

    })
})
router.post('/students/edit', function (req, res) {
    Student.updateByid(req.body, function (err) {
        // console.log(req.body);
        if (err) {
            return res.status(500).send('Server Err');
        }
        res.redirect('/students');

    })
})
router.get('/students/delete', function (req, res) {
    Student.deleteByid(req.query.id, function(err){
        if (err) {
            return res.status(500).send('Server Err');
        }
        res.redirect('/students')
    })
})
router.get('/students/delete', function (req, res) {

})
module.exports = router;

// module.exports = function(app){


//     app.get('/',function(req, res){
//     fs.readFile('./db.json','utf8',function(err,data){
//         if(err){
//             //错误状态码500

//             //data值为字符串需要转为对象
//             var students = JSON.parse(data).students;

//             return res.status(500).send('Server Err')}
//             res.render('index.html',{
//                 fruits:[
//                     '香蕉',
//                     '苹果',
//                     '橘子',
//                     '西瓜'
//                 ],
//                 //字符串转化为对象
//                 students:JSON.parse(data).students

//             });
//     })
//     })

//     app.get('/students',function(req,res){

//     })
//     app.get('/students',function(req,res){

//     })
//     app.get('/students',function(req,res){

//     })
//     app.get('/students',function(req,res){

//     })
//     app.get('/students',function(req,res){

//     })
//     app.get('/students',function(req,res){

//     })

// }