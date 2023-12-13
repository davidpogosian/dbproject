<%@ page import="java.util.List" %>
<%@ page import="dbpack.Tree" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tree Information</title>
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
            <a href="/treecutting/highView">Records</a>

        </div>


    </nav>
    <h2>Highest Trees</h2>
    <% 
        List<Tree> highestTrees = (List<Tree>) request.getAttribute("highestTrees");
        if (highestTrees != null && !highestTrees.isEmpty()) {
    %>
        <table border="1">
            <tr>
                <th>Tree ID</th>
                <th>Request ID</th>
                <th>Height</th>
                <th>Distance</th>
                <th>Address</th>
                <th>Size</th>
            </tr>
            <% for (Tree tree : highestTrees) { %>
                <tr>
                    <td><%= tree.getTreeId() %></td>
                    <td><%= tree.getRequestId() %></td>
                    <td><%= tree.getHeight() %></td>
                    <td><%= tree.getDistance() %></td>
                    <td><%= tree.getAddress() %></td>
                    <td><%= tree.getSize() %></td>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <p>No highest trees found.</p>
    <% } %>

    <h2>All Trees</h2>
    <% 
        List<Tree> allTrees = (List<Tree>) request.getAttribute("allTrees");
        if (allTrees != null && !allTrees.isEmpty()) {
    %>
        <table border="1">
            <tr>
                <th>Tree ID</th>
                <th>Request ID</th>
                <th>Height</th>
                <th>Distance</th>
                <th>Address</th>
                <th>Size</th>
            </tr>
            <% for (Tree tree : allTrees) { %>
                <tr>
                    <td><%= tree.getTreeId() %></td>
                    <td><%= tree.getRequestId() %></td>
                    <td><%= tree.getHeight() %></td>
                    <td><%= tree.getDistance() %></td>
                    <td><%= tree.getAddress() %></td>
                    <td><%= tree.getSize() %></td>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <p>No trees found.</p>
    <% } %>


    
</body>
</html>
