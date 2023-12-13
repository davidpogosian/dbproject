package dbpack;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class QuoteDAO extends DAO {

    // Method to add a new quote
    public boolean addQuote(Quote quote) throws SQLException {
        String sql = "INSERT INTO Quotes (request_id, price, start_date, end_date, status) VALUES (?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = connect.prepareStatement(sql);
        statement.setInt(1, quote.getRequestId());
        statement.setDouble(2, quote.getPrice());
        statement.setTimestamp(3, new Timestamp(quote.getStartDate().getTime()));
        statement.setTimestamp(4, new Timestamp(quote.getEndDate().getTime()));
        statement.setString(5, quote.getStatus());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    // Method to list all quotes
    public List<Quote> listAllQuotes() throws SQLException {
        List<Quote> listQuote = new ArrayList<>();
        String sql = "SELECT * FROM Quotes";

        connect();

        PreparedStatement statement = connect.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int quoteId = resultSet.getInt("quote_id");
            int requestId = resultSet.getInt("request_id");
            double price = resultSet.getDouble("price");
            Timestamp startDate = resultSet.getTimestamp("start_date");
            Timestamp endDate = resultSet.getTimestamp("end_date");
            String status = resultSet.getString("status");

            Quote quote = new Quote(quoteId, requestId, price, startDate, endDate, status);
            listQuote.add(quote);
        }

        resultSet.close();
        statement.close();
        disconnect();

        return listQuote;
    }

    public List<Quote> listAcceptedQuotesWithOneTree() throws SQLException {
        List<Quote> quotes = new ArrayList<>();
        String sql = "SELECT q.* FROM Quotes q " +
                     "JOIN Requests r ON q.request_id = r.request_id " +
                     "JOIN Trees t ON r.request_id = t.request_id " +
                     "GROUP BY q.quote_id " +
                     "HAVING q.status = 'accepted' AND COUNT(t.tree_id) = 1";

        connect();
        

        PreparedStatement statement = connect.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int quoteId = resultSet.getInt("quote_id");
            int requestId = resultSet.getInt("request_id");
            double price = resultSet.getDouble("price");
            Timestamp startDate = resultSet.getTimestamp("start_date");
            Timestamp endDate = resultSet.getTimestamp("end_date");
            String status = resultSet.getString("status");

            Quote quote = new Quote(quoteId, requestId, price, startDate, endDate, status);
            quotes.add(quote);
        }

        resultSet.close();
        statement.close();
        disconnect();

        return quotes;
    }

    public Quote[] getAllPendingQuotesByUserId(String user_id) throws SQLException {
        connect();
        String query = "SELECT Quotes.* FROM Quotes " +
        "JOIN Requests ON Quotes.request_id = Requests.request_id " +
        "WHERE Requests.user_id = ? AND Quotes.status = 'pending'";
        preparedStatement = (PreparedStatement) connect.prepareStatement(query);
        preparedStatement.setString(1, user_id);
        resultSet = preparedStatement.executeQuery();
        resultSet.last();
    	int set_size = resultSet.getRow();
    	resultSet.beforeFirst();
        Quote[] quotes = new Quote[set_size];
        for (int i = 0; i < set_size; i++) {
            resultSet.next();
            quotes[i] = new Quote();
            quotes[i].setQuoteId(Integer.parseInt(resultSet.getString("quote_id")));
            quotes[i].setRequestId(Integer.parseInt(resultSet.getString("request_id")));
            quotes[i].setPrice(Double.parseDouble(resultSet.getString("price")));
            quotes[i].setStartDate(resultSet.getDate("start_date"));
            quotes[i].setEndDate(resultSet.getDate("end_date"));
            quotes[i].setStatus(resultSet.getString("status"));
        }
        preparedStatement.close();
        resultSet.close();
        disconnect();
        return quotes;
    }

    public void updateQuoteStatus(String quote_id, String new_status) throws SQLException {
        connect();
        String query = "UPDATE Quotes " +
        "SET status = ?" +
        "WHERE quote_id = ?";
        preparedStatement = (PreparedStatement) connect.prepareStatement(query);
        preparedStatement.setString(1, new_status);        
        preparedStatement.setString(2, quote_id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }

    public List<User> usersWithPendingQuotes() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT DISTINCT u.user_id, u.first_name, u.last_name, u.email " +
        "FROM Quotes q " +
        "JOIN Requests r ON q.request_id = r.request_id " +
        "JOIN Users u ON r.user_id = u.user_id " +
        "WHERE q.status = 'pending';";

    
        connect();
        PreparedStatement statement = connect.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
    
        while (resultSet.next()) {
            User user = new User();
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            users.add(user);
        }
        
    
        resultSet.close();
        statement.close();
        disconnect();
    
        return users;
    }
    

    // Other methods for updating, deleting, and fetching quotes by various criteria can be added here.

}
