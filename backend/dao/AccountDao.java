package bank.backend.dao;

import bank.backend.entities.Account;
import bank.backend.exceptions.DatabaseException;
import bank.backend.utilities.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {

    String ADD_NEW_ACCOUNT = "INSERT INTO bank_application.account ( id,number,balance) " +
            " VALUES(?,?,?);";
    String SHOW_ACCOUNT_BY_ACCOUNT_NUMBER = "SELECT  id, number, balance " +
            "FROM bank_application.account WHERE number=?; ";
    String TRANSFER_MONEY_FROM_SOURCE = "UPDATE bank_application.account SET balance=? WHERE number=?;";
    String TRANSFER_MONEY_TO_DESTINATION = "UPDATE bank_application.account SET balance=? WHERE number=?;";


    public void addAccount(Account account) throws DatabaseException, SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_ACCOUNT);

        preparedStatement.setInt(1, account.getUserId());
        preparedStatement.setInt(2, account.getNumber());
        preparedStatement.setDouble(3, account.getBalance());

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("The item added successfully!");
        }
    }

    public double getBalanceByAccountNumber(int accountNumber) throws DatabaseException, SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SHOW_ACCOUNT_BY_ACCOUNT_NUMBER);

        preparedStatement.setInt(1, accountNumber);
        ResultSet rs = preparedStatement.executeQuery();

        double balance = rs.getDouble("balance");
        return balance;
    }

    public void transferMoney(int sourceNumber, int destinationNumber, double amount) throws DatabaseException, SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(TRANSFER_MONEY_FROM_SOURCE);

        ResultSet rs = preparedStatement.executeQuery();

        double balance = rs.getDouble("balance");
        double updatedSourceBalance = balance - amount;

        preparedStatement.setDouble(1, updatedSourceBalance);
        preparedStatement.setInt(2, sourceNumber);

        preparedStatement = connection.prepareStatement(TRANSFER_MONEY_TO_DESTINATION);
        balance = rs.getDouble("balance");
        double updatedDistinationBalance = balance + amount;

        preparedStatement.setDouble(1, updatedDistinationBalance);
        preparedStatement.setInt(2, destinationNumber);


        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("The item added successfully!");
        }
    }
}
