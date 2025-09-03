package com.lee.dao;

import com.lee.pojo.User;

import java.util.List;

/**
 * @Author: ljg
 * @Date: 2025/9/3 2:58 AM Wednesday
 * @Description:
 */
public interface UserDao {
    List<User> findAllUsers();

    boolean insertUser(User user);

    User findUserById(Integer id);

    boolean updateUserIgnorePassword(User user);

    boolean updateUser(User user);

    boolean removeUser(int id);
}
