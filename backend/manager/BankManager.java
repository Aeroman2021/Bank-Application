package bank.backend.manager;

import bank.backend.dao.AccountDao;
import bank.backend.dao.TransactionDao;
import bank.backend.dao.UserDao;
import bank.backend.entities.Account;
import bank.backend.entities.User;
import bank.backend.exceptions.DatabaseException;
import bank.backend.exceptions.ManagerException;

import java.sql.SQLException;

public class BankManager {
	private UserDao userDao;
	private AccountDao accountDao;
	private TransactionDao transactionDao;

	public BankManager() {
		userDao = new UserDao();
		accountDao = new AccountDao();
		transactionDao = new TransactionDao();
	}

	public void registerUser(String name) throws ManagerException {
		User user = new User(null, name);
		try {
			userDao.registerUser(user);
		} catch (DatabaseException | SQLException e) {
			e.printStackTrace();
			throw new ManagerException(String.format("Can not add user to database.%n Error: %s", e.getMessage()));
		}
	}

	public void addAccount(int userId, double balance) throws ManagerException {

		if (balance <= 0)
			throw new ManagerException(String.format("Can not add account.%n Error: %s", "Balance is invalid."));

		Account account = new Account(userId, null, balance);
		try {
			accountDao.addAccount(account);
		} catch (DatabaseException | SQLException e) {
			e.printStackTrace();
			throw new ManagerException(String.format("Can not add account to database.%n Error: %s", e.getMessage()));
		}
	}

	public void transferMoney(int sourceNumber, int destinationNumber, double amount) throws ManagerException {
		try {

			if (amount > accountDao.getBalanceByAccountNumber(sourceNumber))
				throw new ManagerException(
						String.format("Can not transfer money.%n Error: %s", "Balance is not enough"));

			accountDao.transferMoney(sourceNumber, destinationNumber, amount);
			transactionDao.addNewTransaction(sourceNumber, destinationNumber, amount);

		} catch (DatabaseException | SQLException e) {
			e.printStackTrace();
			throw new ManagerException(String.format("Can not transfer money.%n Error: %s", e.getMessage()));
		}
	}

	public void printTransactionInformationById(int transactionId) throws ManagerException {
		try{
			transactionDao.showTransactionByID(transactionId);
		}catch (DatabaseException | SQLException e){
			e.printStackTrace();
			throw new ManagerException(String.format("The entered id is not exist in database.%n Error: %s", e.getMessage()));
		}
	}

	public void printTransactionInformationByAccountNumber(int number) throws ManagerException {
		try{
			transactionDao.showTransactionByNumber(number);
		}catch (DatabaseException | SQLException e){
			e.printStackTrace();
			throw new ManagerException(String.format("The entered number is not exist in database.%n Error: %s", e.getMessage()));
		}
	}

	public void printUsernamesById(int id) throws ManagerException {
		try {
			userDao.showUserById(id);
		}catch (DatabaseException | SQLException e){
			e.printStackTrace();
			throw new ManagerException(String.format("The entered id is not exist in database.%n Error: %s", e.getMessage()));
		}
	}

	public void printUserIdBylastname(String lastname) throws ManagerException {
		try {
			userDao.showUserByLastname(lastname);
		}catch (DatabaseException | SQLException e){
			e.printStackTrace();
			throw new ManagerException(String.format("The entered id is not exist in database.%n Error: %s", e.getMessage()));
		}
	}
}
