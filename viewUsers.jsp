<%@ page import="java.util.*" %>
<%
    List<String[]> users = (List<String[]>) request.getAttribute("users");
    if (users == null) users = new ArrayList<>();
%>

<!DOCTYPE html>
<html>
<head>
    <title>All Users</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>All Users</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Role</th>
        <th>Skills</th>
    </tr>
    <% for (String[] u : users) { %>
    <tr>
        <td><%= u[0] %></td>
        <td><%= u[1] %></td>
        <td><%= u[2] %></td>
        <td><%= u[3] %></td>
        <td><%= u[4] %></td>
    </tr>
    <% } %>
</table>

<a href="adminDashboard.jsp">Back</a>
</body>
</html>