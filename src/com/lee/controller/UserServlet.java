package com.lee.controller;

import com.lee.annotations.RequestMapping;
import com.lee.pojo.User;
import com.lee.services.UserService;
import com.lee.services.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * @Author: ljg
 * @Date: 2025/9/4 3:50 PM Thursday
 * @Description:
 */

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    @RequestMapping("/findUserList")
    private void findUserList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("执行 findUserList");
        List<User> list = userService.findAllUserList();

        req.setAttribute("userList", list);
        this.processTemplate("index", req, resp);
    }

    @RequestMapping("/updateUser")
    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User();
        try {
            BeanUtils.populate(user, req.getParameterMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        boolean flag = userService.updateUser(user);
        resp.sendRedirect(req.getContextPath() + "/user/findUserList");
    }

    @RequestMapping("/editUser")
    private void editUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        User dbUser = userService.queryUserById(Integer.valueOf(id));
        req.setAttribute("dbUser", dbUser);
        this.processTemplate("index", req, resp);
    }

    @RequestMapping("/addUser")
    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User();
        try {
            BeanUtils.populate(user, req.getParameterMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        boolean flag = userService.addUser(user);
        resp.sendRedirect(req.getContextPath() + "/user/findUserList");
    }
}
