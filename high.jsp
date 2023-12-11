<%@ page import="java.util.List" %>
<%@ page import="dbpack.User" %>
<%@ page import="dbpack.TreeDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users With Highest Trees</title>
</head>
<body>
    <h2>Users With Highest Trees</h2>
    <% 
        TreeDAO treeDAO = new TreeDAO();
        List<User> usersWithHighestTrees = treeDAO.getUsersWithHighestTrees();
    %>

    <% if (!usersWithHighestTrees.isEmpty()) { %>
        <table border="1">
            <tr>
                <th>User ID</th>
                <th>Username</th>
                <th>Email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <!-- Add other necessary headers -->
            </tr>
            <% for (User user : usersWithHighestTrees) { %>
                <tr>
                    <td><%= user.getUserId() %></td>
                    <td><%= user.getUsername() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getFirstName() %></td>
                    <td><%= user.getLastName() %></td>
                    <!-- Add other user details -->
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <p>No users found with highest trees.</p>
    <% } %>
</body>
</html>
