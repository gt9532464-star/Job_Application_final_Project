package com.jobportal.servlet;

import com.jobportal.DBUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        try (Connection con = DBUtil.getConnection()) {

            String sql = "";
            if ("admin".equals(role)) {
                sql = "SELECT * FROM admin WHERE username=? AND password=?";
            } else {
                sql = "SELECT * FROM users WHERE email=? AND password=? AND role=?";
            }

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            if (!"admin".equals(role)) ps.setString(3, role);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = req.getSession(true);
                session.setAttribute("userRole", role);
                session.setAttribute("userId", rs.getInt("id"));
                String name = "admin".equals(role) ? rs.getString("username") : rs.getString("name");
                session.setAttribute("userName", name);

                if ("admin".equals(role)) {
                    res.sendRedirect("adminDashboard.jsp");
                } else if ("recruiter".equals(role)) {
                    res.sendRedirect("recruiterDashboard.jsp");
                } else {
                    res.sendRedirect("userDashboard.jsp");
                }

            } else {
                res.sendRedirect("login.html?error=invalid");
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}