package com.jobportal.servlet;

import com.jobportal.DBUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewApplicationsUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"user".equals(session.getAttribute("userRole"))) {
            res.getWriter().println("<h2>Unauthorized</h2>");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");
        List<String[]> apps = new ArrayList<>();

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT a.id, j.title, a.status, a.match_score, a.applied_at " +
                        "FROM applications a JOIN jobs j ON a.job_id=j.id " +
                        "WHERE a.user_id=?"
            );
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                apps.add(new String[]{
                    String.valueOf(rs.getInt("id")),
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
        RequestDispatcher rd = req.getRequestDispatcher("viewApplicationsUser.jsp");
        rd.forward(req, res);
    }
}