package com.jobportal.servlet;

import com.jobportal.DBUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewApplicationsRecruiterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"recruiter".equals(session.getAttribute("userRole"))) {
            res.getWriter().println("<h2>Unauthorized</h2>");
            return;
        }

        List<String[]> apps = new ArrayList<>();

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT a.id, u.name, j.title, a.status, a.match_score, a.applied_at " +
                            "FROM applications a " +
                            "JOIN users u ON a.user_id = u.id " +
                            "JOIN jobs j ON a.job_id = j.id");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                apps.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("status"),
                        String.valueOf(rs.getInt("match_score")),
                        rs.getString("applied_at")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("apps", apps);
        RequestDispatcher rd = req.getRequestDispatcher("viewApplicationsRecruiter.jsp");
        rd.forward(req, res);
    }
}