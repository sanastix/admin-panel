package com.example.servlet;

import com.example.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection connection = DBUtil.getConnection()) {
            String query = "select * from users where id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                request.setAttribute("user", resultSet);
                request.getRequestDispatcher("editUser.jsp").forward(request, response);
            } else {
                response.sendRedirect("admin.jsp?error=User not found");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("admin.jsp?error=Database error");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String role = request.getParameter("role");

        try (Connection conn = DBUtil.getConnection()) {
            String query = "update users set username = ?, role = ? where id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, role);
            stmt.setInt(3, id);
            stmt.executeUpdate();

            response.sendRedirect("admin");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("editUser.jsp?id=" + id + "&error=Database error");
        }
    }

}
