<%@ page import="java.util.*" %>
<%
    List<String[]> jobs = (List<String[]>) request.getAttribute("jobs");
    if (jobs == null) jobs = new ArrayList<>();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Manage Jobs</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>Manage Jobs</h2>

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
            <!-- Add manage actions here, e.g., delete -->
            <a href="deleteJob?id=<%= job[0] %>">Delete</a>
        </td>
    </tr>
    <% } %>
</table>

<a href="adminDashboard.jsp">Back</a>
</body>
</html>