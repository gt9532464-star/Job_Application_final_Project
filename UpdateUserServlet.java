package com.jobportal.servlet;

import com.jobportal.DBUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if(session==null || !"admin".equals(session.getAttribute("userRole"))){
            res.getWriter().println("<h2>Unauthorized</h2>");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String role = req.getParameter("role");
        String skills = req.getParameter("skills");

        try(Connection con = DBUtil.getConnection()){
            PreparedStatement ps = con.prepareStatement(
                "UPDATE users SET name=?, email=?, role=?, skills=? WHERE id=?"
            );
            ps.setString(1,name);
            ps.setString(2,email);
            ps.setString(3,role);
            ps.setString(4,skills);
            ps.setInt(5,id);
            ps.executeUpdate();
            res.sendRedirect("manageUsers.jsp");
        }catch(Exception e){ e.printStackTrace(); }
    }
}