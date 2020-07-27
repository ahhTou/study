package com.ahhTou.lesson01;

import java.sql.*;

// 我的第一个JDBC程序
public class JdbcFirstDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. 加载驱动
        // DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Class.forName("com.mysql.cj.jdbc.Driver"); // 固定写法，加载驱动
        // 2. 用户信息和url
        // url= jdbc:mysql://主机地址:端口号/数据库名?参数1&参数2&参数3
        String url = "jdbc:mysql://localhost:3306/jdbcStudy?serverTimezone=GMT%2B8";
        String username = "root";
        String password = "123";

        // 3. 连接成功, 得到数据库对象
        Connection connection = DriverManager.getConnection(url, username, password);

        // 4. 执行sql对象
        Statement statement = connection.createStatement();

        // 5. 执行sql的对象 去 执行sql 可能会返回结果
        String sql = "select * from `users`";

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            System.out.print("id=" + resultSet.getObject("id") + "||");
            System.out.print("id=" + resultSet.getObject("name") + "||");
            System.out.print("id=" + resultSet.getObject("password") + "||");
            System.out.print("id=" + resultSet.getObject("email") + "||");
            System.out.println("id=" + resultSet.getObject("birthday"));
        }


        // 6. 释放连接
        resultSet.close();
        statement.close();
        connection.close();


    }

}
