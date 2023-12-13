<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ page import="dbpack.Request" %>
    <%@ page import="dbpack.Quote" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="ISO-8859-1" />
            <title>treecutting</title>
            <link rel="stylesheet" href="/css/index.css" />
        </head>

        <body>
            <div align="center">
                <h1>Welcome to treecutting, ${first_name}</h1>
                <p>Homepage</p>

                <form action="handleNewRequest" method="post">
                    <input type="text" name="request_name" size="100" placeholder="my_request_1" />
                    <input type="submit" value="Create new request" />
                </form>

                <h2>Your existing requests</h2>
                <table border="1">
                    <tr>
                        <th>request_id</th>
                        <th>request_name</th>
                        <th>user_id</th>
                        <th>status</th>
                    </tr>
                    <% Request[] requests=(Request[]) request.getAttribute("requests"); for(Request req : requests) { %>
                        <tr>
                            <td>
                                <%=req.getRequestId() %>
                            </td>
                            <td>
                                <a href="http://localhost:8080/treecutting/requestView?request_id=<%=req.getRequestId()%>"
                                    target="_self">
                                    <%=req.getRequestName()%>
                                </a>
                            <td>
                                <%=req.getUserId() %>
                            </td>
                            <td>
                                <%=req.getStatus() %>
                            </td>
                        </tr>
                    <% } %>
                </table>

                <!--
"    quote_id INT AUTO_INCREMENT PRIMARY KEY,"
        	+ "    request_id INTEGER,"
        	+ "    price DOUBLE,"
        	+ "    start_date DATETIME,"
        	+ "    end_date DATETIME,"
        	+ "    status ENUM('pending', 'accepted', 'denied'),"
                -->
                <h2>Your pending quotes</h2>
                <table border="1">
                    <tr>
                        <th>quote_id</th>
                        <th>request</th>
                        <th>price</th>
                        <th>start_date</th>
                        <th>end_date</th>
                        <th>status</th>
                        <th>decision</th>
                    </tr>
                    <% Quote[] quotes=(Quote[]) request.getAttribute("quotes"); for(Quote quote : quotes) { %>
                        <tr>
                            <td>
                                <%=quote.getQuoteId() %>
                            </td>
                            <td>
                                <%=quote.getRequestId() %>
                            <td>
                                <%=quote.getPrice() %>
                            </td>
                            <td>
                                <%=quote.getStartDate() %>
                            </td>
                            <td>
                                <%=quote.getEndDate() %>
                            </td>
                            <td>
                                <%=quote.getStatus() %>
                            </td>
                            <td>
                                <form action="handleAcceptQuote">
                                    <input type="hidden" name="quote_id" value="<%= quote.getQuoteId() %>"> <!-- Add a hidden input field -->
                                    <input type="hidden" name="request_id" value="<%= quote.getRequestId() %>"> <!-- Add a hidden input field -->
                                    <input type="submit" value="Accept">
                                </form>
                                <form action="handleDenyQuote">
                                    <input type="hidden" name="quote_id" value="<%= quote.getQuoteId() %>"> <!-- Add a hidden input field -->
                                    <input type="hidden" name="request_id" value="<%= quote.getRequestId() %>"> <!-- Add a hidden input field -->
                                    <input type="submit" value="Deny">
                                </form>
                            </td>
                        </tr>
                    <% } %>
                </table>
            </div>
        </body>

        </html>