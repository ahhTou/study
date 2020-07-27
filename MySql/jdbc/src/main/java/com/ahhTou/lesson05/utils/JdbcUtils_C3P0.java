package com.ahhTou.lesson05.utils;

import com.ahhTou.lesson03.utils.JdbcUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils_C3P0 {

    private static ComboPooledDataSource dataSource = null;

    static {


        try {

            dataSource = new ComboPooledDataSource("MySQL");


            /* 代码实现
            dataSource.setDriverClass();
            dataSource.setPassword();
            dataSource.setUser();
            dataSource.setJdbcUrl();

            dataSource.setMaxPoolSize();
            dataSource.setMinPoolSize();
            */

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 获取连接
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();

    }

    // 释放连接
    public static void release(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
