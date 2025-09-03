package com.lee.controller;

import com.lee.pojo.User;
import com.lee.services.UserService;
import com.lee.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: ljg
 * @Date: 2025/9/3 10:52 AM Wednesday
 * @Description:
 */
@WebServlet("/findUserList")
public class FindUserListServlet extends ViewBaseServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> list = userService.findAllUserList();

        req.setAttribute("userList", list);
        this.processTemplate("index", req, resp);
    }
}
