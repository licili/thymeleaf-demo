package com.lee.controller;

import com.lee.pojo.User;
import com.lee.services.UserService;
import com.lee.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: ljg
 * @Date: 2025/9/3 11:20 AM Wednesday
 * @Description:
 */
@WebServlet("/editUser")
public class EditUserervlet extends ViewBaseServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        User dbUser = userService.queryUserById(Integer.valueOf(id));
        req.setAttribute("dbUser", dbUser);
        this.processTemplate("index", req, resp);
    }
}
