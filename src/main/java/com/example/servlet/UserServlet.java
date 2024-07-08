package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        //Якщо користувач не увійшов у систему або сесія закінчилася
        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        request.setAttribute("username", username);
        request.getRequestDispatcher("user.jsp").forward(request, response);
    }

}
