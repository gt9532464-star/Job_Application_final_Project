<%@ page import="java.sql., java.util." %>
<%
    if (session.getAttribute("userRole") == null || !"admin".equals(session.getAttribute("userRole"))) {
        response.sendRedirect("login.html");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Users</title>
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
        <th>Actions</th>
    </tr>
    <%
        try (Connection con = com.jobportal.DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
    %>
    <tr>
        <td><%= rs.getInt("id") %></td>
        <td><%= rs.getString("name") %></td>
        <td><%= rs.getString("email") %></td>
        <td><%= rs.getString("role") %></td>
        <td><%= rs.getString("skills") %></td>
        <td>
            <a href="editUser.jsp?id=<%= rs.getInt("id") %>">Edit</a> |
            <a href="deleteUser?id=<%= rs.getInt("id") %>">Delete</a>
        </td>
    </tr>
    <%
            }
        } catch(Exception e) { e.printStackTrace(); }
    %>
</table>
<a href="adminDashboard.jsp">Back</a>
</body>
</html>