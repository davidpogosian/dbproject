package dbpack;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TreeDAO extends DAO {

    public TreeDAO() {
        super();
    }

    public boolean addTree(Tree tree) throws SQLException {
        String sql = "INSERT INTO Trees (tree_id, request_id, height, distance, address, size) VALUES (?, ?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = connect.prepareStatement(sql);
        statement.setInt(1, tree.getTreeId());
        statement.setInt(2, tree.getRequestId());
        statement.setDouble(3, tree.getHeight());
        statement.setDouble(4, tree.getDistance());
        statement.setString(5, tree.getAddress());
        statement.setDouble(6, tree.getSize());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Tree> listAllTrees() throws SQLException {
        List<Tree> listTree = new ArrayList<>();

        String sql = "SELECT * FROM Trees";

        connect();

        PreparedStatement statement = connect.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int treeId = resultSet.getInt("tree_id");
            int requestId = resultSet.getInt("request_id");
            double height = resultSet.getDouble("height");
            double distance = resultSet.getDouble("distance");
            String address = resultSet.getString("address");
            double size = resultSet.getDouble("size");

            Tree tree = new Tree(treeId, requestId, height, distance, address, size);
            listTree.add(tree);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listTree;
    }

    public Tree[] getTreesByRequestId(String request_id) throws SQLException {
        connect();
        String query = "SELECT * FROM Trees WHERE request_id = ?";
        preparedStatement = (PreparedStatement) connect.prepareStatement(query);
        preparedStatement.setString(1, request_id);
        resultSet = preparedStatement.executeQuery();
        resultSet.last();
        int set_size = resultSet.getRow();
        resultSet.beforeFirst();
        Tree[] trees = new Tree[set_size];
        for (int i = 0; i < set_size; i++) {
            resultSet.next();
            trees[i] = new Tree();
            trees[i].setTreeId(resultSet.getInt("tree_id"));
            trees[i].setRequestId(resultSet.getInt("request_id"));
            trees[i].setHeight(resultSet.getDouble("height"));
            trees[i].setDistance(resultSet.getDouble("distance"));
            trees[i].setAddress(resultSet.getString("address"));
            trees[i].setSize(resultSet.getDouble("size"));
        }
        preparedStatement.close();
        resultSet.close();
        disconnect();
        return trees;
    }

    public List<Tree> findHighestTreesCut() throws SQLException {
        List<Tree> highestTrees = new ArrayList<>();
        
        // SQL query to find the highest cut tree(s)
        String sql = "SELECT t.* FROM Trees t " +
                     "JOIN Requests r ON t.request_id = r.request_id " +
                     "JOIN Quotes q ON r.request_id = q.request_id " +
                     "WHERE q.status = 'accepted' " +
                     "AND t.height = (SELECT MAX(height) FROM Trees t2 " +
                     "JOIN Requests r2 ON t2.request_id = r2.request_id " +
                     "JOIN Quotes q2 ON r2.request_id = q2.request_id " +
                     "WHERE q2.status = 'accepted')";
    
        connect();
        PreparedStatement statement = connect.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
    
        while (resultSet.next()) {
            Tree tree = new Tree();
            tree.setTreeId(resultSet.getInt("tree_id"));
            tree.setRequestId(resultSet.getInt("request_id"));
            tree.setHeight(resultSet.getDouble("height"));
            tree.setDistance(resultSet.getDouble("distance"));
            tree.setAddress(resultSet.getString("address"));
            tree.setSize(resultSet.getDouble("size"));
            highestTrees.add(tree);
        }
    
        resultSet.close();
        statement.close();
        disconnect();
    
        return highestTrees;
    }
    public List<User> getUsersWithHighestTrees() throws SQLException {
        List<User> users = new ArrayList<>();

        String sql = "SELECT u.*, COUNT(t.tree_id) AS tree_count FROM Users u " +
                     "JOIN Requests r ON u.user_id = r.user_id " +
                     "JOIN Trees t ON r.request_id = t.request_id " +
                     "JOIN Quotes q ON r.request_id = q.request_id " +
                     "WHERE q.status = 'accepted' " +
                     "GROUP BY u.user_id " +
                     "HAVING COUNT(t.tree_id) = ( " +
                     "  SELECT MAX(tree_count) FROM ( " +
                     "    SELECT COUNT(t.tree_id) AS tree_count FROM Users u " +
                     "    JOIN Requests r ON u.user_id = r.user_id " +
                     "    JOIN Trees t ON r.request_id = t.request_id " +
                     "    JOIN Quotes q ON r.request_id = q.request_id " +
                     "    WHERE q.status = 'accepted' " +
                     "    GROUP BY u.user_id " +
                     "  ) AS subquery " +
                     ")";

        connect();

        PreparedStatement statement = connect.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            User user = new User(
                Integer.toString(resultSet.getInt("user_id")),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("email"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
                // Add other necessary fields from the Users table
            );
            users.add(user);
        }

        resultSet.close();
        statement.close();
        disconnect();

        return users;
    }


    
    
}
