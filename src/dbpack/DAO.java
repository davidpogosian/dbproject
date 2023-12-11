package dbpack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
    protected Connection connect = null;
    protected Statement statement = null;
    protected PreparedStatement preparedStatement = null;
	protected ResultSet resultSet = null;

    public DAO() {
    }

    protected void connect() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=root&password=pass@1234");
        }
    }

    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }

    public void resetDatabase() throws SQLException, FileNotFoundException, IOException {
        connect();
        statement = (Statement) connect.createStatement();
        String[] db_reset_commands = {
            "drop database if exists testdb; ",
            "create database testdb; ",
            "use testdb; "
        };
        String[] user_table_commands = {
            "drop table if exists Users",
            "CREATE TABLE if not exists Users("
            + "    user_id INT AUTO_INCREMENT PRIMARY KEY,"	
            + "    username VARCHAR(255) UNIQUE NOT NULL,"
            + "    password VARCHAR(20) NOT NULL,"
            + "    email VARCHAR(50) UNIQUE NOT NULL,"
            + "    first_name VARCHAR(10) NOT NULL,"
            + "    last_name VARCHAR(10) NOT NULL,"
            + "    adress_street_num VARCHAR(4),"
            + "    adress_street VARCHAR(30),"
            + "    adress_city VARCHAR(20),"
            + "    adress_state VARCHAR(2),"
            + "    adress_zip_code VARCHAR(5),"
            + "    birthday DATE NOT NULL,"
            + "    credit_card_number VARCHAR(16),"
            + "    credit_card_expiration_date DATE,"
            + "    credit_card_security_code VARCHAR(4)"
            + ");"
        };
        String[] user_table_mock = {
            "INSERT INTO Users (username, password, email, first_name, last_name, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code, birthday, credit_card_number, credit_card_expiration_date, credit_card_security_code) " +
            "VALUES " +
            "('root', 'pass1234', 'root', 'default', 'default', '0000', 'Default', 'Default', '0', '00000', '2020-01-01', '12345678', '2000-06-27', '222'), " +
            "('user2', 'dave', 'dave@gmail.com', 'Dave', 'Crazy', '1234', 'whatever street', 'detroit', 'MI', '48202', '2000-06-27', '12345678', '2000-06-27', '222'), " +
            "('user3', 'don123', 'don@gmail.com', 'Don', 'Cummings', '1000', 'hi street', 'mama', 'MO', '12345', '1969-03-20', '12345678', '2000-06-27', '222')"
        };
        
        String[] requests_table_commands = {
        	"drop table if exists Requests",
        	"CREATE TABLE Requests("
        	+ "    request_id INT AUTO_INCREMENT PRIMARY KEY,"
            + "    request_name VARCHAR(255),"
        	+ "    user_id INT,"
        	+ "    FOREIGN KEY (user_id) REFERENCES Users(user_id),"
        	+ "    status ENUM('pending', 'accepted', 'denied')"
        	+ ");"
        };
        String[] trees_table_commands = {
            "drop table if exists Trees",
            "CREATE TABLE Trees("
            + "    tree_id INT AUTO_INCREMENT PRIMARY KEY ,"
            + "    request_id INT,"
            + "    FOREIGN KEY (request_id) REFERENCES Requests(request_id),"
            + "    height DOUBLE,"
            + "    distance DOUBLE,"
            + "    address TEXT,"
            + "    size DOUBLE"
            + ");"
        };
        String[] images_table_commands = {
        	"drop table if exists Images",
        	"CREATE TABLE Images("
        	+ "    image_id INT PRIMARY KEY,"
        	+ "    tree_id INT,"
        	+ "    FOREIGN KEY (tree_id) REFERENCES Trees(tree_id),"
        	+ "    image_name VARCHAR(255),"
        	+ "    image MEDIUMBLOB"
        	+ ");"
        };
        String[] notes_table_commands = {
        	"drop table if exists Notes",
        	"CREATE TABLE Notes("
        	+ "    note_id INTEGER,"
        	+ "    request_id INTEGER,"
        	+ "    content VARCHAR(255),"
        	+ "    date DATETIME,"
        	+ "    author INTEGER,"
        	+ "    PRIMARY KEY (note_id),"
        	+ "    FOREIGN KEY (request_id) REFERENCES Requests(request_id)"
        	+ ");"
        };
        String[] quotes_table_commands = {
        	"drop table if exists Quotes",
        	"CREATE TABLE Quotes("
        	+ "    quote_id INTEGER,"
        	+ "    request_id INTEGER,"
        	+ "    price DOUBLE,"
        	+ "    start_date DATETIME,"
        	+ "    end_date DATETIME,"
        	+ "    status ENUM('pending', 'accepted', 'denied'),"
        	+ "    PRIMARY KEY (quote_id),"
        	+ "    FOREIGN KEY (request_id) REFERENCES Requests(request_id)"
        	+ ");"
        };
        String[] orders_table_commands = {
        	"drop table if exists Orders",
        	"CREATE TABLE Orders("
        	+ "    order_id INTEGER,"
        	+ "    quote_id INTEGER,"
        	+ "    status ENUM('paid', 'pending'),"
        	+ "    date_paid DATE,"
        	+ "    PRIMARY KEY (order_id),"
        	+ "    FOREIGN KEY (quote_id) REFERENCES Quotes(quote_id)"
        	+ ");"
        };
        
        //for loop to put these in database

        for (int i = 0; i < db_reset_commands.length; i++) {
        	statement.execute(db_reset_commands[i]);
        }
        for (int i = 0; i < user_table_commands.length; i++) {
        	statement.execute(user_table_commands[i]);
        }
        for (int i = 0; i < user_table_mock.length; i++) {
        	statement.execute(user_table_mock[i]);
        }

        for (int i = 0; i < requests_table_commands.length; i++) {
        	statement.execute(requests_table_commands[i]);
        }
        for (int i = 0; i < trees_table_commands.length; i++) {
        	statement.execute(trees_table_commands[i]);
        }
        for (int i = 0; i < images_table_commands.length; i++) {
        	statement.execute(images_table_commands[i]);
        }
        for (int i = 0; i < notes_table_commands.length; i++) {
        	statement.execute(notes_table_commands[i]);
        }
        for (int i = 0; i < quotes_table_commands.length; i++) {
        	statement.execute(quotes_table_commands[i]);
        }
        for (int i = 0; i < orders_table_commands.length; i++) {
        	statement.execute(orders_table_commands[i]);
        }

        statement.close();
        disconnect();
    }
}