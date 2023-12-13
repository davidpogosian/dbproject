<%@ page import="java.util.List" %>
<%@ page import="dbpack.User" %>
<%@ page import="dbpack.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client Summary</title>
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
            <a href="/treecutting/treeView">Trees</a>
            <a href="/treecutting/highView">Records</a>

        </div>


    </nav>
    <h2>Client Summary</h2>
    
    <h3>Good Clients</h3>
    <% 
        List<User> goodClients = (List<User>) request.getAttribute("good");
        if (goodClients != null && !goodClients.isEmpty()) {
    %>
        <ul>
            <% for (User user : goodClients) { %>
                <li><%= user.getFirstName() %> <%= user.getLastName() %> - <%= user.getEmail() %></li>
            <% } %>
        </ul>
    <% 
        } else {
    %>
        <p>No good clients found.</p>
    <% 
        }
    %>

    <h3>Bad Clients</h3>
    <% 
        List<User> badClients = (List<User>) request.getAttribute("bad");
        if (badClients != null && !badClients.isEmpty()) {
    %>
        <ul>
            <% for (User user : badClients) { %>
                <li><%= user.getFirstName() %> <%= user.getLastName() %> - <%= user.getEmail() %></li>
            <% } %>
        </ul>
    <% 
        } else {
    %>
        <p>No bad clients found.</p>
    <% 
        }
    %>

    <h3>Overdue Bills</h3>
    <% 
        List<Order> overdueBills = (List<Order>) request.getAttribute("overdue");
        if (overdueBills != null && !overdueBills.isEmpty()) {
    %>
        <table border="1">
            <tr>
                <th>Order ID</th>
                <th>Quote ID</th>
                <th>Status</th>
                <th>Date Paid</th>
            </tr>
            <% for (Order order : overdueBills) { %>
                <tr>
                    <td><%= order.getOrderId() %></td>
                    <td><%= order.getQuoteId() %></td>
                    <td><%= order.getStatus() %></td>
                    <td><%= order.getDatePaid() %></td>
                </tr>
            <% } %>
        </table>
    <% 
        } else {
    %>
        <p>No overdue bills found.</p>
    <% 
        }
    %>

    <h3>Users with Pending Quotes</h3>
    <% 
        List<User> usersWithPendingQuotes = (List<User>) request.getAttribute("pending");
        if (usersWithPendingQuotes != null && !usersWithPendingQuotes.isEmpty()) {
    %>
        <ul>
            <% for (User user : usersWithPendingQuotes) { %>
                <li><%= user.getFirstName() %> <%= user.getLastName() %> - <%= user.getEmail() %></li>
            <% } %>
        </ul>
    <% 
        } else {
    %>
        <p>No users with pending quotes found.</p>
    <% 
        }
    %>
</body>
</html>
