<%@ page import="java.util.*" %>
<%
    List<String[]> apps = (List<String[]>) request.getAttribute("apps");
    if (apps == null) apps = new ArrayList<>();
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Applications</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>My Applications</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Job</th>
        <th>Status</th>
        <th>Match Score</th>
        <th>Applied At</th>
    </tr>
    <% for (String[] a : apps) { %>
    <tr>
        <td><%= a[0] %></td>
        <td><%= a[1] %></td>
        <td><%= a[2] %></td>
        <td><%= a[3] %></td>
        <td><%= a[4] %></td>
    </tr>
    <% } %>
</table>

<a href="userDashboard.jsp">Back</a>
</body>
</html>