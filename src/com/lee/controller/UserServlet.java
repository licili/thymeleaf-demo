package com.lee.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.annotations.RequestMapping;
import com.lee.pojo.User;
import com.lee.services.UserService;
import com.lee.services.impl.UserServiceImpl;
import com.lee.vo.SysResult;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


/**
 * @Author: ljg
 * @Date: 2025/9/4 3:50 PM Thursday
 * @Description:
 */

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private static final ObjectMapper MAPPER = new ObjectMapper();
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

    @RequestMapping("/getUserListByAxios")
    private void getUserListByAxios(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> list = userService.findAllUserList();

        String result = MAPPER.writeValueAsString(SysResult.success("获取列表成功", list));
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(result);
    }

    @RequestMapping("/addUserByAxois")
    private void addUserByAxois(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader reader = req.getReader();
        while((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonBody = sb.toString();

        // 解析客户端发过来的 json 字符串
        User user = MAPPER.readValue(jsonBody, User.class);
        System.out.println("user = " + user);

        userService.addUser(user);

        String result = MAPPER.writeValueAsString(SysResult.success("添加用户成功", null));
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(result);
    }
    @RequestMapping("/updateUserByAxois")
    private void updateUserByAxois(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader reader = req.getReader();
        while((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonBody = sb.toString();

        // 解析客户端发过来的 json 字符串
        User user = MAPPER.readValue(jsonBody, User.class);

        userService.updateUser(user);

        String result = MAPPER.writeValueAsString(SysResult.success("更新用户成功", null));
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(result);
    }



}
