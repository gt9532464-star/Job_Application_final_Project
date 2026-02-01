<%
    if (session.getAttribute("userRole") == null || !"user".equals(session.getAttribute("userRole"))) {
        response.sendRedirect("login.html");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>User Dashboard</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>Welcome, <%= session.getAttribute("userName") %> (User)</h2>

<nav>
    <a href="viewJobsUser">View Jobs</a> |
    <a href="viewMyApplications">View My Applications</a> |
    <a href="logout">Logout</a>
</nav>

</body>
</html>