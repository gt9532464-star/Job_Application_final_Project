package com.jobportal.servlet;

import com.jobportal.DBUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewAllApplicationsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if(session==null || !"admin".equals(session.getAttribute("userRole"))){
            res.sendRedirect("login.html");
            return;
        }

        List<String[]> applications = new ArrayList<>();
        try(Connection con = DBUtil.getConnection()){
            PreparedStatement ps = con.prepareStatement(
                "SELECT a.id, u.name as user_name, j.title as job_title, a.resume_path, a.status " +
                "FROM applications a " +
                "JOIN users u ON a.user_id = u.id " +
                "JOIN jobs j ON a.job_id = j.id"
            );
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                applications.add(new String[]{
                    String.valueOf(rs.getInt("id")),
                    rs.getString("user_name"),
                    rs.getString("job_title"),
                    rs.getString("resume_path"),
                    rs.getString("status")
                });
            }
        }catch(Exception e){ e.printStackTrace(); }

        req.setAttribute("applications", applications);
        RequestDispatcher rd = req.getRequestDispatcher("viewAllApplications.jsp");
        rd.forward(req,res);
    }
}