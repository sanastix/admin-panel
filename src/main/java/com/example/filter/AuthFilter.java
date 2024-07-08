package com.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false); //отримує поточну сесію і, якщо вона ще не існує, повертає null

        String uri = req.getRequestURI(); //для перевірки, до якої сторінки або ресурсу звертається користувач

        //Якщо сесія відсутня і запит не відноситься до сторінок входу, реєстрації або відповідних сервлетів, користувач перенаправляється на сторінку входу
        if (session == null && !(uri.endsWith("login.jsp") || uri.endsWith("register.jsp"))) {
            res.sendRedirect("login.jsp");
        } else if (session != null) {
            String role = (String) session.getAttribute("role");

            if ("USER".equals(role) && uri.contains("/admin")) {
                res.sendRedirect("user.jsp");
            } else if ("ADMIN".equals(role) && uri.contains("/user")) {
                res.sendRedirect("admin.jsp");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}
