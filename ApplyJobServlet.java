package com.jobportal.servlet;

import com.jobportal.DBUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 10*1024*1024, maxRequestSize = 20*1024*1024)
public class ApplyJobServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"user".equals(session.getAttribute("userRole"))) {
            res.getWriter().println("<h2>Please login as user to apply.</h2>");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");
        int jobId = Integer.parseInt(req.getParameter("job_id"));

        Part filePart = req.getPart("resume");
        if (filePart == null || filePart.getSize() == 0) {
            res.getWriter().println("<h2>Please upload PDF resume.</h2>");
            return;
        }

        String submittedName = filePart.getSubmittedFileName();
        if (!submittedName.toLowerCase().endsWith(".pdf")) {
            res.getWriter().println("<h2>Only PDF files allowed.</h2>");
            return;
        }

        String uploadsDir = getServletContext().getRealPath("") + File.separator + "uploads";
        File dir = new File(uploadsDir);
        if (!dir.exists()) dir.mkdirs();

        String fileName = System.currentTimeMillis() + "_" + submittedName;
        String fullPath = uploadsDir + File.separator + fileName;
        filePart.write(fullPath);

        int matchScore = 0;
        try (Connection con = DBUtil.getConnection()) {

            // Get user skills
            PreparedStatement psUser = con.prepareStatement("SELECT skills FROM users WHERE id=?");
            psUser.setInt(1, userId);
            ResultSet rsUser = psUser.executeQuery();
            String userSkills = "";
            if (rsUser.next()) userSkills = rsUser.getString("skills");

            // Get job skills
            PreparedStatement psJob = con.prepareStatement("SELECT skills_required FROM jobs WHERE id=?");
            psJob.setInt(1, jobId);
            ResultSet rsJob = psJob.executeQuery();
            String jobSkills = "";
            if (rsJob.next()) jobSkills = rsJob.getString("skills_required");

            matchScore = calculateMatchScore(userSkills, jobSkills);

            // Insert into applications
            PreparedStatement psInsert = con.prepareStatement(
                "INSERT INTO applications(user_id, job_id, match_score, status) VALUES (?,?,?,?)"
            );
            psInsert.setInt(1, userId);
            psInsert.setInt(2, jobId);
            psInsert.setInt(3, matchScore);
            psInsert.setString(4, "Submitted");
            psInsert.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().println("<h2>Error: " + e.getMessage() + "</h2>");
            return;
        }

        res.sendRedirect("userDashboard.jsp");
    }

    // Simple match score: count of overlapping skills
    private int calculateMatchScore(String userSkills, String jobSkills) {
        if (userSkills == null || jobSkills == null) return 0;

        String[] uSkills = userSkills.split(",");
        String[] jSkills = jobSkills.split(",");

        int count = 0;
        for (String us : uSkills) {
            for (String js : jSkills) {
                if (us.trim().equalsIgnoreCase(js.trim())) count++;
            }
        }
        return count;
    }
}