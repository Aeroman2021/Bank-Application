package bank.backend.entities;

import java.sql.Time;

public class Transaction {
	private int id;
	private double amount;
	private Time time;
	private Integer withdrawNumber;
	private Integer depositNumber;

	public Transaction(int id, double amount, Time time, Integer withdrawNumber, Integer depositNumber) {
		this.id = id;
		this.amount = amount;
		this.time = time;
		this.withdrawNumber = withdrawNumber;
		this.depositNumber = depositNumber;
	}

	public int getId() {
		return id;
	}

	public double getAmount() {
		return amount;
	}

	public Time getTime() {
		return time;
	}

	public Integer getWithdrawNumber() {
		return withdrawNumber;
	}

	public Integer getDepositNumber() {
		return depositNumber;
	}

}
