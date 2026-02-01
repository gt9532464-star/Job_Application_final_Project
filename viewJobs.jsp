<%@ page import="java.util.*" %>
<%
    List<String[]> jobs = (List<String[]>) request.getAttribute("jobs");
    if (jobs == null) jobs = new ArrayList<>();
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Jobs</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>All Jobs</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Company</th>
        <th>Description</th>
        <th>Skills Required</th>
    </tr>
    <% for (String[] job : jobs) { %>
    <tr>
        <td><%= job[0] %></td>
        <td><%= job[1] %></td>
        <td><%= job[2] %></td>
        <td><%= job[3] %></td>
        <td><%= job[4] %></td>
    </tr>
    <% } %>
</table>

<a href="recruiterDashboard.jsp">Back</a>
</body>
</html>