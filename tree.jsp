<%@ page import="java.util.List" %>
<%@ page import="dbpack.Tree" %>
<%@ page import="dbpack.TreeDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dbpack.User" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map.Entry" %>
<% List<User> users = (List<User>) request.getAttribute("usersWithHighestTrees"); %>


<html>
<head>
    <title>Highest Tree Information</title>
</head>
<body>
    <h2>Highest Tree Information</h2>
    <% 
        TreeDAO treeDAO = new TreeDAO();
        List<Tree> highestTrees = treeDAO.findHighestTreesCut();
    %>

    <% if (!highestTrees.isEmpty()) { %>
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


</body>
</html>
