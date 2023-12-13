<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, dbpack.RequestDAO, dbpack.Request, dbpack.Order" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dave's Tree View</title>
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
    <h1>Requests Information</h1>
    <table border="1">
        <tr>
            <th>request_id</th>
            <th>request_name</th>
            <th>user_id</th>
            <th>status</th>
            <th>Submit Quote</th>
        </tr>
        <% Request[] requests=(Request[]) request.getAttribute("requests"); for(Request req : requests) { %>
            <tr>
                <td>
                    <%=req.getRequestId() %>
                </td>
                <td><a href="http://localhost:8080/treecutting/requestView?request_id=<%=req.getRequestId()%>"
                        target="_self">
                        <%=req.getRequestName()%>
                    </a>
                <td>
                    <%=req.getUserId() %>
                </td>
                <td>
                    <%=req.getStatus() %>
                </td>
                <td>
                    <form action="handleNewQuote">
                        <input type="hidden" name="request_id" value="<%= req.getRequestId() %>"> <!-- Add a hidden input field -->
                        <p>price:</p>
                        <input type="number" name="price" id="">
                        <p>start date:</p>
                        <input type="date" name="start_date">
                        <p>end date:</p>
                        <input type="date" name="end_date">
                        <input type="submit" value="Send">
                    </form>
                </td>
            </tr>
        <% } %>
    </table>


    <h1>Pending Orders Information</h1>
    <table border="1">
        <tr>
            <th>order_id</th>
            <th>quote_id</th>
            <th>date_paid</th>
            <th>status</th>
        </tr>
        <% Order[] pending_orders=(Order[]) request.getAttribute("pending_orders"); for(Order order : pending_orders) { %>
            <tr>
                <td>
                    <%=order.getOrderId() %>
                </td>
                <td>
                    <%=order.getQuoteId() %>
                </td>
                <td>
                    <%=order.getDatePaid() %>
                </td>
                <td>
                    <%=order.getStatus() %>
                </td>
            </tr>
        <% } %>
    </table>

    <h1>Paid Orders Information</h1>
    <table border="1">
        <tr>
            <th>order_id</th>
            <th>quote_id</th>
            <th>date_paid</th>
            <th>status</th>
        </tr>
        <% Order[] paid_orders=(Order[]) request.getAttribute("paid_orders"); for(Order order : paid_orders) { %>
            <tr>
                <td>
                    <%=order.getOrderId() %>
                </td>
                <td>
                    <%=order.getQuoteId() %>
                </td>
                <td>
                    <%=order.getDatePaid() %>
                </td>
                <td>
                    <%=order.getStatus() %>
                </td>
            </tr>
        <% } %>
    </table>
</body>

</html>