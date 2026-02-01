package com.jobportal.servlet;

import com.jobportal.DBUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class DeleteUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if(session==null || !"admin".equals(session.getAttribute("userRole"))){
            res.getWriter().println("<h2>Unauthorized</h2>");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));
        try(Connection con = DBUtil.getConnection()){
            PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            res.sendRedirect("manageUsers.jsp");
        }catch(Exception e){ e.printStackTrace(); }
    }
}