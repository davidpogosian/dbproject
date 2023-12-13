<%@ page import="java.util.List" %>
<%@ page import="dbpack.User" %>
<%@ page import="dbpack.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client Summary</title>
</head>
<body>
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
</body>
</html>
