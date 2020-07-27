package com.ahhTou.lesson02;

import com.ahhTou.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestInsert {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            // 获取数据库连接
            conn = JdbcUtils.getConnection();

            st = conn.createStatement();
            String sql = "INSERT INTO users(`name`, `password`,`email`,`birthday`)\n" +
                    "VALUES ('ahh','12','11@qq.com','2020-01-01')";
            int i = st.executeUpdate(sql);
            if (i > 0) {
                System.out.println("插入了" + i + "条数据");
            } else {
                System.out.println("没有插入任何数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
