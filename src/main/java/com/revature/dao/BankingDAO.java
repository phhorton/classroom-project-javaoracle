package com.revature.dao;

import java.util.List;

import com.revature.accounts.Account;
import com.revature.accounts.AccountStatus;
import com.revature.accounts.AccountType;
import com.revature.users.Role;
import com.revature.users.User;

public interface BankingDAO {
	
	//CREATE
	public void insertUser(User u);
//	public void insertRole(Role r);
	public void insertAccount(Account a);
//	public void insertAccountStatus(AccountStatus s);
//	public void insertAccountType(AccountType t);
	
	//READ
	public List<User> selectAllUsers();
	public User selectUserById(int userId);
	public List<Role> selectAllRoles();
	public Role selectRoleById(int roleId);
	public List<Account> selectAllAccounts();
	public Account selectAccountById(int accountId);
	public List<Account> selectAccountByStatus(int statusId);
	public List<Account> selectAccountByUser(int userId);
	public List<AccountStatus> selectAllStatus();
	public AccountStatus selectStatusById(int statusId);
	public List<AccountType> selectAllTypes();
	public AccountType selectTypeById(int typeId);
	
	//UPDATE
	public void updateUser(User u);
	public void updateAccount(Account a);
	
	//DELETE
	//None

}
