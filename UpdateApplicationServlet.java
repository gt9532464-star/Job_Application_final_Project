package com.jobportal.servlet;

import com.jobportal.DBUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class UpdateApplicationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"recruiter".equals(session.getAttribute("userRole"))) {
            res.getWriter().println("<h2>Unauthorized</h2>");
            return;
        }

        int appId = Integer.parseInt(req.getParameter("id"));
        String status = req.getParameter("status");

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE applications SET status=? WHERE id=?");
            ps.setString(1, status);
            ps.setInt(2, appId);
            ps.executeUpdate();
            res.sendRedirect("viewApplicationsRecruiter");
        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}