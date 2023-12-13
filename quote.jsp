<%@ page import="java.util.List" %>
<%@ page import="dbpack.Quote" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accepted Quotes Involving Only One Tree</title>
</head>
<body>
    <h2>Accepted Quotes Involving Only One Tree</h2>
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
</body>
</html>
