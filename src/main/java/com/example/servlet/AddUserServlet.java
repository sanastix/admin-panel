package com.example.servlet;

import com.example.util.DBUtil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String role = (String) session.getAttribute("role");
        if (role == null && !"ADMIN".equals(role)) {
            response.sendRedirect("login.jsp");
            return;
        }

        try (Connection connection = DBUtil.getConnection()) {
            String query = "insert into users (username, password, role) values (?, ?, 'USER')";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();

            response.sendRedirect("admin");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("admin.jsp?error=Database error");
        }
    }

}
