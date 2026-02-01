<%
    if (session.getAttribute("userRole") == null || !"recruiter".equals(session.getAttribute("userRole"))) {
        response.sendRedirect("login.html");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Recruiter Dashboard</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>Welcome, <%= session.getAttribute("userName") %> (Recruiter)</h2>

<nav>
    <a href="jobPost.html">Post Job</a> |
    <a href="viewJobs">View My Jobs</a> |
    <a href="logout">Logout</a>
</nav>

<nav>
    <a href="postJob.jsp">Post Job</a> |
    <a href="viewApplicationsRecruiter">View Applications</a> |
    <a href="logout">Logout</a>
</nav>
</body>
</html>