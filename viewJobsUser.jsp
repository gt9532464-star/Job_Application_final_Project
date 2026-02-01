<%@ page import="java.util.*" %>
<%
    if (session.getAttribute("userRole") == null || !"user".equals(session.getAttribute("userRole"))) {
        response.sendRedirect("login.html");
        return;
    }

    List<String[]> jobs = (List<String[]>) request.getAttribute("jobs");
    if (jobs == null) jobs = new ArrayList<>();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Available Jobs</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>Available Jobs</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Company</th>
        <th>Description</th>
        <th>Skills Required</th>
        <th>Action</th>
    </tr>
    <% for (String[] job : jobs) { %>
    <tr>
        <td><%= job[0] %></td>
        <td><%= job[1] %></td>
        <td><%= job[2] %></td>
        <td><%= job[3] %></td>
        <td><%= job[4] %></td>
        <td>
            <form method="post" action="applyJob" enctype="multipart/form-data">
                <input type="hidden" name="job_id" value="<%= job[0] %>">
                Upload Resume (PDF): <input type="file" name="resume" required><br>
                <input type="submit" value="Apply">
            </form>
        </td>
    </tr>
    <% } %>
</table>

<a href="userDashboard.jsp">Back</a>
</body>
</html>