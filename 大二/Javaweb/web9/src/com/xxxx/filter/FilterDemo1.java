package com.xxxx.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/s1")
public class FilterDemo1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter1........");
        //放行
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("filter1 response...............");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
