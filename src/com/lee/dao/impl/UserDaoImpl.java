package com.lee.dao.impl;

import com.lee.dao.UserDao;
import com.lee.pojo.User;

import java.util.List;

/**
 * @Author: ljg
 * @Date: 2025/9/3 3:45 AM Wednesday
 * @Description:
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    @Override
    public List<User> findAllUsers() {
        String sql = "SELECT * FROM tb_user";
        List<User> list = this.getList(User.class, sql);
        return list;
    }

    @Override
    public boolean insertUser(User user) {

        String sql = "INSERT INTO tb_user(id, username, password, email) VALUES(null, ?, ?, ?)";
        int effectRow = this.update(sql, user.getUsername(), user.getPassword(), user.getEmail());
        return effectRow > 0;

    }

    @Override
    public User findUserById(Integer id) {
        String sql = "SELECT * FROM tb_user WHERE id = ?";
        User dbUser = this.getBean(User.class, sql, id);
        return dbUser;
    }

    @Override
    public boolean updateUserIgnorePassword(User user) {
        String sql = "UPDATE tb_user SET username = ? , email = ? WHERE id = ?";
        int row = this.update(sql, user.getUsername(), user.getEmail(), user.getId());
        return row > 0;
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE tb_user SET username = ?,password = ?,email = ? WHERE id = ?";
        int row = this.update(sql, user.getUsername(),user.getPassword(), user.getEmail(), user.getId());
        return row > 0;
    }

    @Override
    public boolean removeUser(int id) {
        String sql = "DELETE FROM tb_user WHERE id = ?";
        int row = this.update(sql, id);
        return row > 0;
    }
}
