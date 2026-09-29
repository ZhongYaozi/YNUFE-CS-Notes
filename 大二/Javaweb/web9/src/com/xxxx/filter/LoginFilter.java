package com.xxxx.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String uri = req.getRequestURI();
        if (uri.contains("login.jsp") || uri.contains("register.jsp") || uri.contains("online.jsp")) {
            filterChain.doFilter(req, resp);
            return;
        }
        if (uri.contains("/js/") || uri.contains("/images/") || uri.contains("/css/")) {
            filterChain.doFilter(req, resp);
            return;
        }
        if (uri.contains("/lg")) {
            filterChain.doFilter(req, resp);
            return;
        }
        if (req.getSession().getAttribute("username") != null) {
            filterChain.doFilter(req, resp);
            return;
        }
        resp.getWriter().write("<script>alert('请先登录!');window.location='login.jsp'</script>");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
