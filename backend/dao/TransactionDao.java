package bank.backend.dao;

import bank.backend.entities.Transaction;
import bank.backend.utilities.DbConnection;

import java.sql.*;

public class TransactionDao {

    Transaction transaction;
    String ADD_NEW_TRANSACTION = "INSERT INTO bank_application.transaction (amount, time, withdraw_number,deposit_number) " +
            " VALUES(?,?,?,?);";
    String SHOW_TRANSACTION_BY_ID = "SELECT  amount, time, withdraw_number, deposit_number " +
            "FROM bank_application.transaction WHERE id=?; ";
    String SHOW_TRANSACTION_BY_Withdraw_NUMBER = "SELECT  id, amount, time, deposit_number " +
            "FROM bank_application.transaction WHERE withdraw_number=?; ";

    public void addNewTransaction(int sourceNumber, int destinationNumber, double amount) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_TRANSACTION);

        preparedStatement.setTime(1, transaction.getTime());
        preparedStatement.setDouble(2, amount);
        preparedStatement.setInt(3, sourceNumber);
        preparedStatement.setInt(4, destinationNumber);

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("The item added successfully!");
        }
    }

    public void showTransactionByID(int userId) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SHOW_TRANSACTION_BY_ID);

        preparedStatement.setInt(1,userId);
        ResultSet rs =preparedStatement.executeQuery();

        int id = rs.getInt("id");
        double amount = rs.getDouble("amount");
        Time time = rs.getTime("time");
        int depositNumber = rs.getInt("deposit_number");
        int withdrawNumber = rs.getInt("withdraw_number");

        System.out.println("======================================================================");
        System.out.println("The  transaction information for the input id");
        System.out.println("id = " + id + " | " + " amount = " + " | " + " time = " + time +
                            " deposit_number = " + depositNumber + " | " + " withdraw_number = " +
                             withdrawNumber);
        System.out.println("======================================================================");
    }

    public void showTransactionByNumber(int number) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SHOW_TRANSACTION_BY_Withdraw_NUMBER);

        preparedStatement.setInt(1,number);
        ResultSet rs =preparedStatement.executeQuery();

        int id = rs.getInt("id");
        double amount = rs.getDouble("amount");
        Time time = rs.getTime("time");
        int withdrawNumber = rs.getInt("withdraw_number");

        System.out.println("======================================================================");
        System.out.println("The  transaction information for the input id");
        System.out.println("id = " + id + " | " + " amount = " + " | " + " time = " + time +
               " withdraw_number = " + withdrawNumber);
        System.out.println("======================================================================");
    }



}
