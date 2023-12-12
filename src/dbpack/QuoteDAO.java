package dbpack;

import java.sql.*;
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

    // Other methods for updating, deleting, and fetching quotes by various criteria can be added here.

}
