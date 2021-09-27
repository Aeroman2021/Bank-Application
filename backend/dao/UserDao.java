package bank.backend.dao;

import bank.backend.entities.User;
import bank.backend.exceptions.DatabaseException;
import bank.backend.utilities.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    String REGISTER_NEW_USER = "INSERT INTO bank_application.`user` (name) VALUES(?);";
    String SHOW_USER_BY_ID = "SELECT * FROM bank_application.`user` WHERE id=?";
    String SHOW_USER_BY_LASTNAME = "SELECT * FROM bank_application.user WHERE lastname=?";

    public void registerUser(User user) throws DatabaseException, SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_NEW_USER);

        preparedStatement.setString(1, user.getName());

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("The item added successfully!");
        }
    }

    public void showUserById(int id) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SHOW_USER_BY_ID);
        ResultSet rs = preparedStatement.executeQuery();

        preparedStatement.setInt(1, id);
        String name = rs.getString("name");

        System.out.println("id = " + id + " | " +  " name =" + name);
    }

    public void showUserByLastname(String lastname) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SHOW_USER_BY_LASTNAME);
        ResultSet rs = preparedStatement.executeQuery();

        preparedStatement.setString(1,"name");
        int id = rs.getInt("id");

        System.out.println("id = " + id + " | " +  " name =" + lastname);
    }
}
