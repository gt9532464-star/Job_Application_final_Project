package com.jobportal.servlet;

import com.jobportal.DBUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String skills = req.getParameter("skills");

        try (Connection con = DBUtil.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(name,email,password,role,skills) VALUES (?,?,?,?,?)"
            );
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, role);
            ps.setString(5, skills);

            ps.executeUpdate();
            res.sendRedirect("login.html");

        } catch (SQLIntegrityConstraintViolationException e) {
            res.getWriter().println("<h2>Email already registered.</h2>");
        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}