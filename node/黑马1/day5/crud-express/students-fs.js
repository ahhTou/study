//数据操作模块
var dbpath = './db.json';

var fs = require('fs');
//查询全部学生
// callback 中的参数第一个是err，第二个是结果
exports.find = function(callback){
    fs.readFile(dbpath, 'utf8',function(err, data){
        if(err){
            callback(err);
        }
        callback(null, JSON.parse(data).students);

    })
}

//查询单个
exports.findByid = function(id, callback) {
    fs.readFile(dbpath, 'utf8',function(err, data){
        if(err){
            callback(err);
        }
        var students =JSON.parse(data).students;
        var ret = students.find(function (item){
            return item.id === parseInt(id);
        })
        callback(null, ret);

    })
}
//保存全部学生
exports.save = function(student, callback){
    fs.readFile(dbpath,'utf8',function(err, data){
        if(err){
            return callback(err);
        }
        
        //字符串转对象
        var students = JSON.parse(data).students;
        //写入对象
        student.id = students[students.length-1].id +1 ;
        students.push(student);
        
        //对象转化为字符串
        var fileData = JSON.stringify({
            students:students
        })

        //字符串转化为文件
        fs.writeFile(dbpath, fileData, function(err){
            if(err){
                return callback(err);
            }
            callback(null);
        });
    })

}

//更新
exports.updateByid = function(student, callback){
    fs.readFile(dbpath, 'utf8',function(err,data){

        student.id = parseInt(student.id);
        if(err){
            return callback(err);            
        }
        var students = JSON.parse(data).students;
        //EcamScript 6 语法 遍历
        var stu = students.find(function (item) {
            //遍历没有找到返回un
            //得到值的地址而不是新数组
            return item.id === parseInt(student.id);
        })
        for(var key in student){
            stu[key]= student[key];  
        }


        var fileData = JSON.stringify({
            students:students
        })

        fs.writeFile(dbpath, fileData, function(err){
            if(err){
                return callback(err);
            }
            callback(null);
        });
    })

}

//删除
exports.deleteByid = function(id,callback){
    fs.readFile(dbpath, 'utf8',function(err, data){
        if(err){
            return callback(err);
        }
        var students = JSON.parse(data).students;

        //返回下标
        var deleteId = students.findIndex(function(item){
            return item.id ===parseInt(id)
        })

        students.splice(deleteId, 1);

        var fileData = JSON.stringify({
            students:students
        })

        fs.writeFile(dbpath,fileData,function(err){
            if(err){
                return callback(err);
            }
            callback(null);
        })
    })

}