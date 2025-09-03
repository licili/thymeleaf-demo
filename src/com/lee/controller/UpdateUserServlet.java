package com.lee.controller;

import com.lee.pojo.User;
import com.lee.services.UserService;
import com.lee.services.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: ljg
 * @Date: 2025/9/3 11:58 AM Wednesday
 * @Description:
 */

@WebServlet("/updateUser")
public class UpdateUserServlet extends ViewBaseServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        try {
            BeanUtils.populate(user, req.getParameterMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        boolean flag = userService.updateUser(user);
        resp.sendRedirect(req.getContextPath() + "/findUserList");
    }
}
