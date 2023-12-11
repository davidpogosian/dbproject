package dbpack;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;

public class UserDAO extends DAO {

    public UserDAO() {
        try {
            System.out.println("UserDAO reset database");
            resetDatabase();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public String getUserId(String email) throws SQLException {
        connect();
        String query = "SELECT user_id FROM Users WHERE email = ?";
        preparedStatement = (PreparedStatement) connect.prepareStatement(query);
        preparedStatement.setString(1, email);
        resultSet = preparedStatement.executeQuery();
        String user_id = "";
        if (resultSet.next()) {
            user_id = resultSet.getString("user_id");
        }
        preparedStatement.close();
        resultSet.close();
        disconnect();
        return user_id;
    }

    public User authenticate(String email, String password) throws SQLException {
        connect();
        String query = "SELECT * FROM Users WHERE email = ?";
        preparedStatement = (PreparedStatement) connect.prepareStatement(query);
        preparedStatement.setString(1, email);
        resultSet = preparedStatement.executeQuery();
        User user = null;
        // handle case where email is not in db
        if (resultSet.next()) {
            if (password.equals(resultSet.getString("password"))) {
                user = new User();
                user.setUserId(resultSet.getString("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAddressStreetNum(resultSet.getString("adress_street_num"));
                user.setAddressStreet(resultSet.getString("adress_street"));
                user.setAddressCity(resultSet.getString("adress_city"));
                user.setAddressState(resultSet.getString("adress_state"));
                user.setAddressZipCode(resultSet.getString("adress_zip_code"));
                user.setBirthday(resultSet.getString("birthday"));
                user.setCreditCardNumber(resultSet.getString("credit_card_number"));
                user.setCreditCardExpirationDate(resultSet.getString("credit_card_expiration_date"));
                user.setCreditCardSecurityCode(resultSet.getString("credit_card_security_code"));
            }
        }
        preparedStatement.close();
        resultSet.close();
        disconnect();
        return user;
    }

    public void addUser(User newUser) throws SQLException {
        connect();
        String query = "INSERT INTO Users "
            + "(username, password, email, first_name, last_name, "
            + "adress_street_num, adress_street, adress_city, adress_state, adress_zip_code, "
            + "birthday, credit_card_number, credit_card_expiration_date, credit_card_security_code) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = (PreparedStatement) connect.prepareStatement(query);
        preparedStatement.setString(1, newUser.getUsername());
        preparedStatement.setString(2, newUser.getPassword());
        preparedStatement.setString(3, newUser.getEmail());
        preparedStatement.setString(4, newUser.getFirstName());
        preparedStatement.setString(5, newUser.getLastName());
        preparedStatement.setString(6, newUser.getAddressStreetNum());
        preparedStatement.setString(7, newUser.getAddressStreet());
        preparedStatement.setString(8, newUser.getAddressCity());
        preparedStatement.setString(9, newUser.getAddressState());
        preparedStatement.setString(10, newUser.getAddressZipCode());
        preparedStatement.setString(11, newUser.getBirthday());
        preparedStatement.setString(12, newUser.getCreditCardNumber());
        preparedStatement.setString(13, newUser.getCreditCardExpirationDate());
        preparedStatement.setString(14, newUser.getCreditCardSecurityCode());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }
}