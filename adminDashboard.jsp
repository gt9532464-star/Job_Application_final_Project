<%@ page session="true" %>
<%
    if (session.getAttribute("userRole") == null || !"admin".equals(session.getAttribute("userRole"))) {
        response.sendRedirect("login.html");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>Welcome Admin</h2>

<nav>
    <a href="viewAllUsers">View Users</a> |
    <a href="viewJobs">View Jobs</a> |
    <a href="logout">Logout</a>

</nav>
<nav>
    <a href="viewAllUsers">Manage Users</a> |
    <a href="manageJobs">Manage Jobs</a> |
    <a href="viewAllApplications">View All Applications</a> |
    
</nav>

</body>
</html>