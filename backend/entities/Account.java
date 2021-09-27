package bank.backend.entities;

public class Account {
	private int userId;
	private Integer number;
	private double balance;

	public Account(int userId, Integer number, double balance) {
		this.userId = userId;
		this.number = number;
		this.balance = balance;
	}

	public int getNumber() {
		return number;
	}

	public double getBalance() {
		return balance;
	}

	public void increaseBalance(double amount) {
		balance += amount;
	}

	public void decreaseBalance(double amount) {
		balance -= amount;
	}

	public int getUserId() {
		return userId;
	}
}
