package com.jobportal.servlet;

import com.jobportal.DBUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class PostJobServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"recruiter".equals(session.getAttribute("userRole"))) {
            res.getWriter().println("<h2>Unauthorized. Login as recruiter.</h2>");
            return;
        }

        String title = req.getParameter("title");
        String company = req.getParameter("company");
        String description = req.getParameter("description");
        String skills = req.getParameter("skills");

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO jobs(title, company, description, skills_required) VALUES (?,?,?,?)");
            ps.setString(1, title);
            ps.setString(2, company);
            ps.setString(3, description);
            ps.setString(4, skills);
            ps.executeUpdate();

            res.sendRedirect("recruiterDashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}