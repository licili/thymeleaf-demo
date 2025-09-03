package com.lee.controller;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: ljg
 * @Date: 2025/9/3 2:47 AM Wednesday
 * @Description:
 */
public class ViewBaseServlet extends HttpServlet {
    private TemplateEngine templateEngine = null;
    @Override
    public void init() throws ServletException {
        ServletContext ctx = this.getServletContext();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(ctx);
        templateResolver.setPrefix("/WEB-INF/view/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        templateResolver.setCacheable(true);
        templateResolver.setCacheTTLMs(60000L);

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    protected void processTemplate(String templateName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");

        WebContext webContext = new WebContext(req, resp, this.getServletContext());
        templateEngine.process(templateName, webContext, resp.getWriter());
    }
}
