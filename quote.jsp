<%@ page import="java.util.List" %>
<%@ page import="dbpack.Quote" %>
<%@ page import="dbpack.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accepted Quotes Involving Only One Tree</title>
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
            <a href="/treecutting/orderView">Orders</a>
            <a href="/treecutting/treeView">Trees</a>
            <a href="/treecutting/highView">Records</a>

        </div>


    </nav>
    <h2>One tree quotes</h2>
    <% 
        List<Quote> quotes = (List<Quote>) request.getAttribute("onequote");
        if (quotes != null && !quotes.isEmpty()) { 
    %>
        <table border="1">
            <tr>
                <th>Quote ID</th>
                <th>Request ID</th>
                <th>Price</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Status</th>
            </tr>
            <% for (Quote quote : quotes) { %>
                <tr>
                    <td><%= quote.getQuoteId() %></td>
                    <td><%= quote.getRequestId() %></td>
                    <td><%= quote.getPrice() %></td>
                    <td><%= quote.getStartDate() %></td>
                    <td><%= quote.getEndDate() %></td>
                    <td><%= quote.getStatus() %></td>
                </tr>
            <% } %>
        </table>
    <% 
        } else { 
    %>
        <p>No quotes found.</p>
    <% 
        } 
    %>
    <h2>Prospective clients</h2>
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

    <h2>Easy Clients</h2>
    <% 
        List<User> usersWithoutDeniedOrPendingQuotes = (List<User>) request.getAttribute("accepted");
        if (usersWithoutDeniedOrPendingQuotes != null && !usersWithoutDeniedOrPendingQuotes.isEmpty()) {
    %>
        <ul>
            <% for (User user : usersWithoutDeniedOrPendingQuotes) { %>
                <li><%= user.getFirstName() %> <%= user.getLastName() %> - <%= user.getEmail() %></li>
            <% } %>
        </ul>
    <% 
        } else {
    %>
        <p>No users found without denied or pending quotes.</p>
    <% 
        }
    %>


</body>
</html>
