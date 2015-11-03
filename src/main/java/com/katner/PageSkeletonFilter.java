package com.katner;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by michal on 01.11.15.
 */
@WebFilter(filterName = "PageSkeletonFilter")
public class PageSkeletonFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/skeleton.jsp");
        dispatcher.include(req, resp);
        chain.doFilter(req, resp);
        RequestDispatcher dispatcher1 = httpServletRequest.getRequestDispatcher("/footer.jsp");
        dispatcher1.include(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
