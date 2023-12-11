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
                    <th>Price</th>
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
                            <form action="submitQuote">
                                <input type="number" name="Price" id="">
                                <input type="submit" value="Send">
                                </form>
                        </td>
                       
                    </tr>
                    <% } %>

            </table>

        </body>

        </html>