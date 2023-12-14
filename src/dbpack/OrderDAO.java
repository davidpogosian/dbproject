package dbpack;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DAO {
    public void addOrder(Order new_order) throws SQLException {
        connect();
        System.out.printf("going to insert into ORDERS quote_id = %d ", new_order.getQuoteId());
        String query = "INSERT INTO Orders "
            + "(quote_id, status, date_paid) "
            + "VALUES (?, ?, ?)";
        preparedStatement = (PreparedStatement) connect.prepareStatement(query);
        preparedStatement.setInt(1, new_order.getQuoteId());
        preparedStatement.setString(2, new_order.getStatus());
        preparedStatement.setTimestamp(3,  new Timestamp(new_order.getDatePaid().getTime()));
        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }

    public Order[] getPendingOrders() throws SQLException {
        connect();
        String query = "SELECT * FROM Orders WHERE status = 'pending'";
        statement = (Statement) connect.createStatement();
        resultSet = statement.executeQuery(query);
        resultSet.last();
    	int set_size = resultSet.getRow();
    	resultSet.beforeFirst();
        Order[] orders = new Order[set_size];
        for (int i = 0; i < set_size; i++) {
            resultSet.next();
            orders[i] = new Order();
            orders[i].setOrderId(resultSet.getInt("order_id"));
            orders[i].setQuoteId(resultSet.getInt("quote_id"));
            orders[i].setStatus(resultSet.getString("status"));
            orders[i].setDatePaid(resultSet.getDate("date_paid"));
        }
        resultSet.close();
        disconnect();
        return orders;
    }

    public Order[] getPendingOrdersByUserId(String user_id) throws SQLException {
        connect();
        String query = "SELECT Orders.* FROM Orders " +
                        "JOIN Quotes ON Orders.quote_id = Quotes.quote_id " +
                        "JOIN Requests ON Quotes.request_id = Requests.request_id " +
                        "WHERE Requests.user_id = ? " +
                        "AND Orders.status = 'pending'";
        PreparedStatement preparedStatement = connect.prepareStatement(query);
        preparedStatement.setString(1, user_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.last();
    	int set_size = resultSet.getRow();
    	resultSet.beforeFirst();
        Order[] orders = new Order[set_size];
        for (int i = 0; i < set_size; i++) {
            resultSet.next();
            orders[i] = new Order();
            orders[i].setOrderId(resultSet.getInt("order_id"));
            orders[i].setQuoteId(resultSet.getInt("quote_id"));
            orders[i].setStatus(resultSet.getString("status"));
            orders[i].setDatePaid(resultSet.getDate("date_paid"));
        }
        resultSet.close();
        disconnect();
        return orders;
    }

    public Order[] getPaidOrders() throws SQLException {
        connect();
        String query = "SELECT * FROM Orders WHERE status = 'paid'";
        statement = (Statement) connect.createStatement();
        resultSet = statement.executeQuery(query);
        resultSet.last();
    	int set_size = resultSet.getRow();
    	resultSet.beforeFirst();
        Order[] orders = new Order[set_size];
        for (int i = 0; i < set_size; i++) {
            resultSet.next();
            orders[i] = new Order();
            orders[i].setOrderId(resultSet.getInt("order_id"));
            orders[i].setQuoteId(resultSet.getInt("quote_id"));
            orders[i].setStatus(resultSet.getString("status"));
            orders[i].setDatePaid(resultSet.getDate("date_paid"));
        }
        resultSet.close();
        disconnect();
        return orders;
    }

    public void updateOrderStatus(String order_id, String new_status) throws SQLException {
        connect();
        String query = "UPDATE Orders " +
        "SET status = ?" +
        "WHERE order_id = ?";
        preparedStatement = (PreparedStatement) connect.prepareStatement(query);
        preparedStatement.setString(1, new_status);        
        preparedStatement.setString(2, order_id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }


    public List<User> listGoodClients() throws SQLException {
        List<User> goodClients = new ArrayList<>();
        String sql = "SELECT u.user_id, u.first_name, u.last_name, u.email FROM Users u " +
                     "JOIN Requests r ON u.user_id = r.user_id " +
                     "JOIN Quotes q ON r.request_id = q.request_id " +
                     "JOIN Orders o ON q.quote_id = o.quote_id " +
                     "WHERE o.status = 'paid' AND " +
                     "TIMESTAMPDIFF(HOUR, q.end_date, o.date_paid) <= 70 " +
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
                     "WHERE ( TIMESTAMPDIFF(DAY, q.end_date, CURDATE()) > 7) " +
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
