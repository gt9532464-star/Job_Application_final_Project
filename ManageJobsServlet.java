package com.jobportal.servlet;

import com.jobportal.DBUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManageJobsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if(session==null || !"admin".equals(session.getAttribute("userRole"))){
            res.getWriter().println("<h2>Unauthorized</h2>");
            return;
        }

        List<String[]> jobs = new ArrayList<>();
        try(Connection con = DBUtil.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM jobs");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                jobs.add(new String[]{
                    String.valueOf(rs.getInt("id")),
                    rs.getString("title"),
                    rs.getString("company"),
                    rs.getString("description"),
                    rs.getString("skills_required")
                });
            }
        }catch(Exception e){ e.printStackTrace(); }

        req.setAttribute("jobs", jobs);
        RequestDispatcher rd = req.getRequestDispatcher("manageJobs.jsp");
        rd.forward(req,res);
    }
}