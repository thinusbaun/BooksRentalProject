package com.katner;

import com.katner.model.AuthUserEntity;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by michal on 23.11.15.
 */
@WebFilter(filterName = "StaffLoggedFilter")
public class StaffLoggedFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        AuthUserEntity user = (session != null) ? (AuthUserEntity) session.getAttribute("user") : null;

        if (user != null && (user.getIsSuperuser() == 1 || user.getIsStaff() == 1)) {
            chain.doFilter(request, resp);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
