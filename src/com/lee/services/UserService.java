package com.lee.services;

import com.lee.pojo.User;

import java.util.List;

/**
 * @Author: ljg
 * @Date: 2025/9/3 3:44 AM Wednesday
 * @Description:
 */
public interface UserService {
    List<User> findAllUserList();

    boolean addUser(User user);

    User queryUserById(Integer integer);

    boolean updateUser(User user);

    boolean deleteUser(int i);
}
