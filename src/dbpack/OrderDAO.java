package dbpack;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DAO {


    public List<User> listGoodClients() throws SQLException {
        List<User> goodClients = new ArrayList<>();
        String sql = "SELECT u.user_id, u.first_name, u.last_name, u.email FROM Users u " +
                     "JOIN Requests r ON u.user_id = r.user_id " +
                     "JOIN Quotes q ON r.request_id = q.request_id " +
                     "JOIN Orders o ON q.quote_id = o.quote_id " +
                     "WHERE o.status = 'paid' AND " +
                     "TIMESTAMPDIFF(HOUR, q.end_date, o.date_paid) <= 24 " +
                     "GROUP BY u.user_id, u.first_name, u.last_name, u.email";

        connect();
        PreparedStatement statement = connect.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            User user = new User();
            user.setUserId(resultSet.getString("user_id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            goodClients.add(user);
        }

        resultSet.close();
        statement.close();
        disconnect();

        return goodClients;
    }

    // Method to list bad clients
    public List<User> listBadClients() throws SQLException {
        List<User> badClients = new ArrayList<>();
        String sql = "SELECT u.user_id, u.first_name, u.last_name, u.email FROM Users u " +
                     "JOIN Requests r ON u.user_id = r.user_id " +
                     "JOIN Quotes q ON r.request_id = q.request_id " +
                     "JOIN Orders o ON q.quote_id = o.quote_id " +
                     "WHERE (o.status = 'pending' AND DATEDIFF(CURDATE(), q.end_date) > 7) " +
                     "GROUP BY u.user_id, u.first_name, u.last_name, u.email";

        connect();
        PreparedStatement statement = connect.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            User user = new User();
            user.setUserId(resultSet.getString("user_id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            badClients.add(user);
        }

        resultSet.close();
        statement.close();
        disconnect();

        return badClients;
    }
    public List<Order> listOverdueBills() throws SQLException {
        List<Order> overdueBills = new ArrayList<>();
        String sql = "SELECT o.* FROM Orders o " +
                     "JOIN Quotes q ON o.quote_id = q.quote_id " +
                     "WHERE (o.status = 'pending' AND DATEDIFF(CURDATE(), q.end_date) > 7) " +
                     "OR (o.status = 'paid' AND DATEDIFF(o.date_paid, q.end_date) > 7)";
    
        connect();
        PreparedStatement statement = connect.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
    
        while (resultSet.next()) {
            Order order = new Order();
            // Assuming Order class has these setters
            order.setOrderId(resultSet.getInt("order_id"));
            order.setQuoteId(resultSet.getInt("quote_id"));
            order.setStatus(resultSet.getString("status"));
            order.setDatePaid(resultSet.getDate("date_paid")); // Ensure this matches your column name and type
            overdueBills.add(order);
        }
    
        resultSet.close();
        statement.close();
        disconnect();
    
        return overdueBills;
    }

    // ... other DAO methods ...

    // Connection management methods will be here as well
    // These should establish and close connection to the database
}
