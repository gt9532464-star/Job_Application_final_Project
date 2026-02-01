package com.jobportal.servlet;

import com.jobportal.DBUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewAllUsersServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("userRole"))) {
            res.getWriter().println("<h2>Unauthorized</h2>");
            return;
        }

        List<String[]> users = new ArrayList<>();

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new String[]{
                    String.valueOf(rs.getInt("id")),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("role"),
                    rs.getString("skills")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("users", users);
        RequestDispatcher rd = req.getRequestDispatcher("viewUsers.jsp");
        rd.forward(req, res);
    }
}