package com.lee.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: ljg
 * @Date: 2025/9/3 3:55 AM Wednesday
 * @Description:
 */
@WebServlet(value = {"/index.html"})
public class IndexServletView extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processTemplate("index", req, resp);
    }
}
