package com.lee.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: ljg
 * @Date: 2025/9/3 2:29 AM Wednesday
 * @Description: 数据库连接工具类
 */
public class JdbcUtil {
    private static DataSource dataSource = null;
    private static ThreadLocal<Connection> threadLocal = null;

    static {
        Properties properties = new Properties();
        try {
            properties.load(JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
            threadLocal = new ThreadLocal<>();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        Connection connection = threadLocal.get();
        if(connection == null) {
            try {
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public static void releaseConnection() {
        Connection connection = threadLocal.get();
        if(connection != null) {
            try {
                connection.close();
                threadLocal.remove();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
