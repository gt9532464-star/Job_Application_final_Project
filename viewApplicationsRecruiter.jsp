<%@ page import="java.util.*" %>
<%
    List<String[]> apps = (List<String[]>) request.getAttribute("apps");
    if (apps == null) apps = new ArrayList<>();
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
        <th>Applicant</th>
        <th>Job</th>
        <th>Status</th>
        <th>Match Score</th>
        <th>Applied At</th>
        <th>Update Status</th>
    </tr>
    <% for (String[] a : apps) { %>
    <tr>
        <td><%= a[0] %></td>
        <td><%= a[1] %></td>
        <td><%= a[2] %></td>
        <td><%= a[3] %></td>
        <td><%= a[4] %></td>
        <td><%= a[5] %></td>
        <td>
            <form method="post" action="updateApplication">
                <input type="hidden" name="id" value="<%= a[0] %>">
                <select name="status">
                    <option>Submitted</option>
                    <option>Reviewed</option>
                    <option>Accepted</option>
                    <option>Rejected</option>
                </select>
                <input type="submit" value="Update">
            </form>
        </td>
    </tr>
    <% } %>
</table>

<a href="recruiterDashboard.jsp">Back</a>
</body>
</html>