package com.ahhTou.lesson04;

import com.ahhTou.lesson03.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTransaction1 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = JdbcUtils.getConnection();

            // 关闭数据库自动提交，自动开启事务
            conn.setAutoCommit(false);

            String sql1 = "update `account` set money = money -100 where name ='A' ";
            String sql2 = "update `account` set money = money +100 where name ='b'";

            st = conn.prepareStatement(sql1);
            st.executeUpdate();
            st = conn.prepareStatement(sql2);
            st.executeUpdate();

            conn.commit();


        } catch (Exception e) {
            e.printStackTrace();
            try {
                // 默认:失败是默认回滚
                assert conn != null;
                conn.rollback();
            } catch (Exception ee) {
                ee.printStackTrace();
            }

        } finally {
            com.ahhTou.lesson02.utils.JdbcUtils.release(conn, st, rs);
        }
    }
}
