package com.lee.dao.impl;

import com.lee.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: ljg
 * @Date: 2025/9/3 2:58 AM Wednesday
 * @Description:
 */
public class BaseDaoImpl {
    private QueryRunner queryRunner = new QueryRunner();

    protected <T> T getBean(Class<T> clazz, String sql, Object... args) {

        T t = null;

        List<T> list = this.getList(clazz, sql, args);
        if(list != null && list.size() != 0) {
            t = list.get(0);
        }
        return t;
    }

    protected <T> List<T> getList(Class<T> clazz, String sql, Object... args) {
        BeanListHandler<T> beanListHandler = new BeanListHandler<>(clazz);
        try {
            return queryRunner.query(JdbcUtil.getConnection(), sql, beanListHandler, args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected int update(String sql, Object... args) {
        try {
            return queryRunner.update(JdbcUtil.getConnection(), sql, args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected  Object getValue(String sql, Object... args) {
        ScalarHandler scalarHandler = new ScalarHandler();
        try {
            return queryRunner.query(JdbcUtil.getConnection(), sql, scalarHandler, args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
