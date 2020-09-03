package com.ahhtou;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Springboot05DataApplicationTests {

    @Resource
    DataSource dataSource;

    @Resource
    JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());

        Connection connection = dataSource.getConnection();

        String sql = "select * from user";

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

        System.out.println(maps);

    }

}
