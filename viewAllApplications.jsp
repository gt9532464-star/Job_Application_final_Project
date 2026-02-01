<%@ page import="java.util.*" %>
<%
    List<String[]> applications = (List<String[]>) request.getAttribute("applications");
    if (applications == null) applications = new ArrayList<>();
%>

<!DOCTYPE html>
<html>
<head>
    <title>All Applications</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>All Applications</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>User Name</th>
        <th>Job Title</th>
        <th>Resume</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <% for (String[] app : applications) { %>
    <tr>
        <td><%= app[0] %></td>
        <td><%= app[1] %></td>
        <td><%= app[2] %></td>
        <td><a href="uploads/<%= app[3] %>" target="_blank">Download</a></td>
        <td><%= app[4] %></td>
        <td>
            <a href="updateApplication?id=<%= app[0] %>&status=accepted">Accept</a> |
            <a href="updateApplication?id=<%= app[0] %>&status=rejected">Reject</a>
        </td>
    </tr>
    <% } %>
</table>

<a href="adminDashboard.jsp">Back</a>
</body>
</html>