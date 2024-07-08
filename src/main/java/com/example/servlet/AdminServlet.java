package com.example.servlet;

import com.example.model.User;
import com.example.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String userrole = (String) session.getAttribute("role");

        if (userrole == null ||!"ADMIN".equals(userrole)) {
            response.sendRedirect("login.jsp");
            return;
        }

        try (Connection connection = DBUtil.getConnection()) {
            String query = "select * from users";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String role = resultSet.getString("role");
                User user = new User(id, username, role);
                userList.add(user);
            }

            request.setAttribute("userList", userList);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("admin.jsp?error=Database error");
        }
    }

}
