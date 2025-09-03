package com.lee.services.impl;

import com.lee.dao.UserDao;
import com.lee.dao.impl.UserDaoImpl;
import com.lee.pojo.User;
import com.lee.services.UserService;
import com.lee.util.Md5Util;
import com.mysql.cj.util.StringUtils;

import java.util.List;

/**
 * @Author: ljg
 * @Date: 2025/9/3 3:44 AM Wednesday
 * @Description:
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findAllUserList() {
        return userDao.findAllUsers();
    }

    @Override
    public boolean addUser(User user) {
        String password = user.getPassword();
        String md5Password = Md5Util.encode(password);
        user.setPassword(md5Password);

        return userDao.insertUser(user);
    }

    @Override
    public User queryUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    public boolean updateUser(User user) {
        boolean flag = StringUtils.isNullOrEmpty(user.getPassword());
        if(flag) {
            return userDao.updateUserIgnorePassword(user);
        } else {
            user.setPassword(Md5Util.encode(user.getPassword()));
            return userDao.updateUser(user);
        }
    }

    @Override
    public boolean deleteUser(int id) {
        return userDao.removeUser(id);
    }
}
