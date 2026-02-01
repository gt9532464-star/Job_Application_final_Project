package com.jobportal.servlet;

import com.jobportal.DBUtil;
import jakarta.servlet.*;
import  jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
@WebServlet("/deleteJob")
public class DeleteJobServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if(session==null || !"admin".equals(session.getAttribute("userRole"))){
            res.sendRedirect("login.html");
            return;
        }

        int jobId = Integer.parseInt(req.getParameter("id"));

        try(Connection con = DBUtil.getConnection()){
            PreparedStatement ps = con.prepareStatement("DELETE FROM jobs WHERE id=?");
            ps.setInt(1, jobId);
            ps.executeUpdate();
        }catch(Exception e){ e.printStackTrace(); }

        res.sendRedirect("manageJobs");
    }
}