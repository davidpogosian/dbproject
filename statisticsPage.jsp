<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, dbpack.RequestDAO, dbpack.Request, dbpack.Statistic" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Statistics Page</title>
    <link rel="stylesheet" href="/css/index.css" />
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
            <a href="/treecutting/highView">Records</a>
        </div>
    </nav>

    <h1>Statistic Information</h1>
    <table border="1">
        <tr>
            <th>User ID</th>
            <th>Username</th>
            <th>Total Trees</th>
            <th>Total Due Amount</th>
            <th>Total Paid Amount</th>
            <th>Work Done Date</th>
        </tr>
        <% List<Statistic> statistics = (List<Statistic>) request.getAttribute("statistics");
           for (Statistic stat : statistics) { %>
            <tr>
                <td><%= stat.getUserId() %></td>
                <td><%= stat.getUsername() %></td>
                <td><%= stat.getTotalTrees() %></td>
                <td><%= stat.getTotalDueAmount() %></td>
                <td><%= stat.getTotalPaidAmount() %></td>
                <td><%= new SimpleDateFormat("yyyy-MM-dd").format(stat.getWorkDoneDate()) %></td>
            </tr>
        <% } %>
    </table>
</body>
</html>