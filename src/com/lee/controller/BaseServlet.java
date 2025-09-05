package com.lee.controller;

import com.lee.annotations.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: ljg
 * @Date: 2025/9/4 4:57 PM Thursday
 * @Description:
 */
public class BaseServlet extends ViewBaseServlet{
    private final String METHOD = "method";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // /user/findUserList


        String pathInfo = req.getPathInfo();
        System.out.println("pathInfo = " + pathInfo);
        Class<? extends BaseServlet> clazz = this.getClass();
        Method[] declaredMethods = clazz.getDeclaredMethods();

        for (Method declareMethod : declaredMethods) {
             try {
                 // 被 @RequestMapping 注解标记
                 if(declareMethod.isAnnotationPresent(RequestMapping.class)) {
                     // 获取注解内容
                     RequestMapping requestMapping = declareMethod.getDeclaredAnnotation(RequestMapping.class);
                     // 获取了注解的请求路径
                     String requestPath = requestMapping.value();

                     if(requestPath.equals(pathInfo)) {
                         declareMethod.setAccessible(true);
                         declareMethod.invoke(this, req, resp);
                         break;
                     }
                 }
             } catch (Exception e) {
                 throw new RuntimeException(e);
             }
        }
        resp.setStatus(404);
    }
}
