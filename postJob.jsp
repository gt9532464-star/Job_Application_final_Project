<%
    if (session.getAttribute("userRole") == null || !"recruiter".equals(session.getAttribute("userRole"))) {
        response.sendRedirect("login.html");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Post Job</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>Post a New Job</h2>
<form method="post" action="postJob">
    Title: <input type="text" name="title" required><br>
    Company: <input type="text" name="company" required><br>
    Description: <textarea name="description" required></textarea><br>
    Skills Required (comma-separated): <input type="text" name="skills" required><br>
    <input type="submit" value="Post Job">
</form>
<a href="recruiterDashboard.jsp">Back</a>
</body>
</html>