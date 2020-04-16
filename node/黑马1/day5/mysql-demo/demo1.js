var mysql = require('mysql');
var connection = mysql.createConnection({
    host    :'localhost',
    user    :'root',
    password:'123',
    database:'webtable'
});

//连接
connection.connect;

//执行操作
connection.query('SELECT * FROM `usermsg`', function(error, results, flieds) {
    if(error) throw error;
    console.log(results);
    

});

connection.end();