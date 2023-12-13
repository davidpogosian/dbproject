<%@ page import="java.util.List" %>
<%@ page import="dbpack.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users With Highest Trees</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        nav {
            background-color: #333;
            overflow: hidden;
        }
        .nav{
            padding-left: 120px;
        }

        .nav a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            transition: 0.3s;
            padding: 35px;
            

        }

        .nav a:hover {
            background-color: #ddd;
            color: black;
        }

        .active {
            background-color: #4CAF50;
        }
    </style>
</head>
<body>
    <nav> 
        <div class="nav">
            <a href="/treecutting/daveView">Home</a>
            <a href="/treecutting/quoteView">Quotes Analysis</a>
            <a href="/treecutting/orderView">Orders</a>
            <a href="/treecutting/treeView">Trees</a>

        </div>


    </nav>
    <h2>Users With Highest Trees</h2>
    <% 
        List<User> usersWithHighestTrees = (List<User>) request.getAttribute("hightreeuser");
        if (usersWithHighestTrees != null && !usersWithHighestTrees.isEmpty()) {
    %>
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
