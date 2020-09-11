package com.revature.accounts;

public class Account {
	private int accountId;	//primary key
	private double balance;	//not null
	private AccountStatus status;
	private AccountType type;
	private int userId;		//This is to connect each account to a user (M to 1 relationship)
	
	public Account(int accountId, double initBalance, AccountStatus status, AccountType type, int userId) {
		super();
		this.accountId = accountId;
		this.balance = initBalance;
		this.status = status;
		this.type = type;
		this.userId = userId;
	}

	public int getAccountId() {
		return this.accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void withdraw(double balance) {
		this.balance -= balance;
	}
	
	public void deposit(double balance) {
		this.balance += balance;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
