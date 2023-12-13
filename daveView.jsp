<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.*, dbpack.RequestDAO, dbpack.Request" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Dave's Tree View</title>
            <link rel="stylesheet" href="/css/index.css" />
        </head>

        <body>
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

        </body>

        </html>