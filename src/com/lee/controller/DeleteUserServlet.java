package com.lee.controller;

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
@WebServlet("/deleteUser")
public class DeleteUserServlet extends ViewBaseServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        boolean flag = userService.deleteUser(Integer.parseInt(id));
        resp.sendRedirect(req.getContextPath() + "/findUserList");

    }
}
