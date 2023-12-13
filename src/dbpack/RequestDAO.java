package dbpack;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestDAO extends DAO {
    
    public Request[] getRequestsByUserId(String user_id) throws SQLException {
        connect();
        String query = "SELECT * FROM Requests WHERE user_id = ?";
        preparedStatement = (PreparedStatement) connect.prepareStatement(query);
        preparedStatement.setString(1, user_id);
        resultSet = preparedStatement.executeQuery();
        resultSet.last();
    	int set_size = resultSet.getRow();
    	resultSet.beforeFirst();
        Request[] requests = new Request[set_size];
        for (int i = 0; i < set_size; i++) {
            resultSet.next();
            requests[i] = new Request();
            requests[i].setRequestId(resultSet.getString("request_id"));
            requests[i].setRequestName(resultSet.getString("request_name"));
            requests[i].setUserId(user_id);
            requests[i].setStatus(resultSet.getString("status"));
        }
        preparedStatement.close();
        resultSet.close();
        disconnect();
        return requests;
    }

    public Request[] getAllPendingRequests() throws SQLException {
        connect();
        String query = "SELECT * FROM Requests WHERE status = \'pending\'";
        statement =  (Statement) connect.createStatement();
        resultSet = statement.executeQuery(query);
        resultSet.last();
    	int set_size = resultSet.getRow();
    	resultSet.beforeFirst();
        Request[] requests = new Request[set_size];
        for (int i = 0; i < set_size; i++) {
            resultSet.next();
            requests[i] = new Request();
            requests[i].setRequestId(resultSet.getString("request_id"));
            requests[i].setRequestName(resultSet.getString("request_name"));
            requests[i].setStatus(resultSet.getString("status"));
            requests[i].setUserId(resultSet.getString("user_id"));
        }
        resultSet.close();
        disconnect();
        return requests;
    }

    public void addRequest(Request new_request) throws SQLException {
        connect();
        String query = "INSERT INTO Requests "
            + "(request_name, user_id, status) "
            + "VALUES (?, ?, ?)";
        preparedStatement = (PreparedStatement) connect.prepareStatement(query);
        preparedStatement.setString(1, new_request.getRequestName());
        preparedStatement.setString(2, new_request.getUserId());
        preparedStatement.setString(3, new_request.getStatus());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }
}
