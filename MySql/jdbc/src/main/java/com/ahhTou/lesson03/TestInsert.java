package com.ahhTou.lesson03;

import com.ahhTou.lesson02.utils.JdbcUtils;

import java.sql.*;

public class TestInsert {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            // 获取数据库连接
            conn = JdbcUtils.getConnection();

            String sql = "INSERT INTO users(`name`, `password`,`email`,`birthday`) " +
                    "VALUES(?,?,?,?)";

            // 预编译sql，先写sql，然后不执行
            st = conn.prepareStatement(sql);

            st.setString(1,"la");
            st.setString(2,"123");
            st.setString(3,"123@qq.com");
            st.setDate(4,new Date(new java.util.Date().getTime()));

            System.out.println(st);

            int i = st.executeUpdate();
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
