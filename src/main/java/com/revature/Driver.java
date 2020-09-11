package com.revature;

import java.util.List;

import com.revature.accounts.Account;
import com.revature.accounts.AccountStatus;
import com.revature.accounts.AccountType;
import com.revature.dao.BankingDAO;
import com.revature.dao.BankingDAOImpl;
import com.revature.users.Role;
import com.revature.users.User;

public class Driver {

	public static final int ROLE_STANDARD_ID = 1;
	public static final int ROLE_EMPLOYEE_ID = 2;
	public static final int ROLE_ADMIN_ID = 3;
	public static final int TYPE_CHECKING_ID = 1;
	public static final int TYPE_SAVINGS_ID = 2;
	public static final int STATUS_PENDING_ID = 1;
	public static final int STATUS_OPEN_ID = 2;
	public static final int STATUS_CLOSED_ID = 3;
	public static final int STATUS_DENIED_ID = 4;

	public static void main(String[] args) {
		/*
		 * Set up the status, type, and roles tables
		 * roles: Admin, Employee, Standard, Premium (optional)
		 * status: Pending, Open, Closed, Denied
		 * type: Checking, Savings
		 * 
		 * Grab the list of the Users and Accounts from the database; keep them separate
		 * Make sure to store the user_id to the account to ensure that a User has 1+ Accounts
		 */

		BankingDAO dao = new BankingDAOImpl();
		
//		Role standard = new Standard(ROLE_STANDARD_ID, "Standard");
//		Role employee = new Employee(ROLE_EMPLOYEE_ID, "Employee");
//		Role admin = new Admin(ROLE_ADMIN_ID, "Admin");
//		AccountStatus pending = new AccountStatus(STATUS_PENDING_ID, "Pending");
//		AccountStatus open = new AccountStatus(STATUS_OPEN_ID, "Open");
//		AccountStatus closed = new AccountStatus(STATUS_CLOSED_ID, "Closed");
//		AccountStatus denied = new AccountStatus(STATUS_DENIED_ID, "Denied");
//		AccountType checking = new AccountType(TYPE_CHECKING_ID, "Checking");
//		AccountType savings = new AccountType(TYPE_SAVINGS_ID, "Savings");
//		User u1 = new User(1, "philip", "hi", "Philip", "Horton", "philip.horton@example.com", standard);
//		Account a1 = new Account(1, 50.00, open, savings, u1.getUserId());
//		Account a2 = new Account(2, 150.00, pending, checking, u1.getUserId());
//		User u2 = new User(2, "jdawg", "jdawg", "JDawg", "HotDogs", "jdawg@example.com", employee);
//		Account a3 = new Account(3, 0.00, denied, savings, u2.getUserId());
//		User u3 = new User(3, "admin", "admin", "Admin", "Admin", "admin@example.com", admin);
//		Account a4 = new Account(4, 500.00, closed, savings, u3.getUserId());
//		
//		//Insert
//		dao.insertRole(standard);
//		dao.insertRole(employee);
//		dao.insertRole(admin);
//		dao.insertAccountType(checking);
//		dao.insertAccountType(savings);
//		dao.insertAccountStatus(pending);
//		dao.insertAccountStatus(open);
//		dao.insertAccountStatus(closed);
//		dao.insertAccountStatus(denied);
//		dao.insertUser(u1);
//		dao.insertAccount(a1);
//		dao.insertAccount(a2);
//		dao.insertUser(u2);
//		dao.insertAccount(a3);
//		dao.insertUser(u3);
//		dao.insertAccount(a4);
		
		//Read All
		System.out.println("Roles:");
		List<Role> roles = dao.selectAllRoles();
		for (Role r : roles) {
			System.out.print(r.getRole() + "\t");
		}
		System.out.println("\n\nUsers:");
		List<User> users = dao.selectAllUsers();
		for (User u : users) {
			System.out.print(u.getFirstName() + "\t\t");
		}
		System.out.println("\n\nStatus:");
		List<AccountStatus> status = dao.selectAllStatus();
		for (AccountStatus a : status) {
			System.out.print(a.getStatus() + "\t\t");
		}
		System.out.println("\n\nType:");
		List<AccountType> type = dao.selectAllTypes();
		for (AccountType t : type) {
			System.out.print(t.getType() + "\t");
		}
		System.out.println("\n\nAccounts:");
		List<Account> accounts = dao.selectAllAccounts();
		for (Account a : accounts) {
			System.out.print(a.getBalance() + "\t\t");
		}
		
		//Read One Standard
//		System.out.println("User by id (Standard user):");
//		User u = dao.selectUserById(1);
//		if (u.getRole().viewOtherPermissions()) {
//			System.out.println("Permission to view all");
//			List<User> users = dao.selectAllUsers();
//			for (User view : users) {
//				System.out.println("ID:\t\t" + view.getUserId());
//				System.out.println("Username:\t" + view.getUsername());
//				System.out.println("Password:\t" + view.getPassword());
//				System.out.println("First Name:\t" + view.getFirstName());
//				System.out.println("Last Name:\t" + view.getLastName());
//				System.out.println("Email:\t\t" + view.getEmail());
//				System.out.println("Role:\t\t" + view.getRole().getRole());
//			}
//		} else {
//			System.out.println("Only allowed to view self");
//			System.out.println("ID:\t\t" + u.getUserId());
//			System.out.println("Username:\t" + u.getUsername());
//			System.out.println("Password:\t" + u.getPassword());
//			System.out.println("First Name:\t" + u.getFirstName());
//			System.out.println("Last Name:\t" + u.getLastName());
//			System.out.println("Email:\t\t" + u.getEmail());
//			System.out.println("Role:\t\t" + u.getRole().getRole());
//		}
		
		//selectAccountByUser
//		System.out.println("View accounts by user");
//		User u = dao.selectUserById(1);
//		List<Account> accounts = dao.selectAccountByUser(u.getUserId());
//		for (Account a : accounts) {
//			System.out.println("ID:\t\t" + a.getAccountId());
//			System.out.println("Balance:\t" + a.getBalance());
//			System.out.println("Status:\t\t" + a.getStatus().getStatus());
//			System.out.println("Type:\t\t" + a.getType().getType());
//			System.out.println("User:\t\t" + dao.selectUserById(a.getUserId()).getFirstName());
//		}
//		
//		//selectAccountById
//		System.out.println("View account by id");
//		Account a = dao.selectAccountById(1);
//		System.out.println("ID:\t\t" + a.getAccountId());
//		System.out.println("Balance:\t" + a.getBalance());
//		System.out.println("Status:\t\t" + a.getStatus().getStatus());
//		System.out.println("Type:\t\t" + a.getType().getType());
//		System.out.println("User:\t\t" + dao.selectUserById(a.getUserId()).getFirstName());
//		
//		//selectAccountByStatus
//		System.out.println("View accounts by status");
//		AccountStatus s = dao.selectStatusById(STATUS_PENDING_ID);
//		accounts = dao.selectAccountByStatus(s.getStatusId());
//		for (Account ac : accounts) {
//			System.out.println("ID:\t\t" + ac.getAccountId());
//			System.out.println("Balance:\t" + ac.getBalance());
//			System.out.println("Status:\t\t" + ac.getStatus().getStatus());
//			System.out.println("Type:\t\t" + ac.getType().getType());
//			System.out.println("User:\t\t" + dao.selectUserById(ac.getUserId()).getFirstName());
//		}
		
		//Read One/All Employee/Admin
//		System.out.println("User by id (Employee user):");
//		User u = dao.selectUserById(2);
//		if (u.getRole().viewOtherPermissions()) {
//			System.out.println("Permission to view all");
//			List<User> users = dao.selectAllUsers();
//			for (User view : users) {
//				System.out.println("ID:\t\t" + view.getUserId());
//				System.out.println("Username:\t" + view.getUsername());
//				System.out.println("Password:\t" + view.getPassword());
//				System.out.println("First Name:\t" + view.getFirstName());
//				System.out.println("Last Name:\t" + view.getLastName());
//				System.out.println("Email:\t\t" + view.getEmail());
//				System.out.println("Role:\t\t" + view.getRole().getRole());
//			}
//		} else {
//			System.out.println("Only allowed to view self");
//			System.out.println("ID:\t\t" + u.getUserId());
//			System.out.println("Username:\t" + u.getUsername());
//			System.out.println("Password:\t" + u.getPassword());
//			System.out.println("First Name:\t" + u.getFirstName());
//			System.out.println("Last Name:\t" + u.getLastName());
//			System.out.println("Email:\t\t" + u.getEmail());
//			System.out.println("Role:\t\t" + u.getRole().getRole());
//		}
//		System.out.println("User by id (Admin user):");
//		User u = dao.selectUserById(3);
//		if (u.getRole().viewOtherPermissions()) {
//			System.out.println("Permission to view all");
//			List<User> users = dao.selectAllUsers();
//			for (User view : users) {
//				System.out.println("ID:\t\t" + view.getUserId());
//				System.out.println("Username:\t" + view.getUsername());
//				System.out.println("Password:\t" + view.getPassword());
//				System.out.println("First Name:\t" + view.getFirstName());
//				System.out.println("Last Name:\t" + view.getLastName());
//				System.out.println("Email:\t\t" + view.getEmail());
//				System.out.println("Role:\t\t" + view.getRole().getRole());
//			}
//		} else {
//			System.out.println("Only allowed to view self");
//			System.out.println("ID:\t\t" + u.getUserId());
//			System.out.println("Username:\t" + u.getUsername());
//			System.out.println("Password:\t" + u.getPassword());
//			System.out.println("First Name:\t" + u.getFirstName());
//			System.out.println("Last Name:\t" + u.getLastName());
//			System.out.println("Email:\t\t" + u.getEmail());
//			System.out.println("Role:\t\t" + u.getRole().getRole());
//		}
		
		//Update
		//Update user
//		User u = dao.selectUserById(1);
//		System.out.println("Standard user update with user: " + u.getFirstName());
//		List<User> users = dao.selectAllUsers();
//		for (User user : users) {
//			if (u.getUserId() == user.getUserId() || u.getRole().modifyOtherPermissions()) {
//				System.out.println("Have permission to modify user: " + user.getFirstName());
//				//Modify
//				user.setEmail("update1@email.com");
//				dao.updateUser(user);
//			} else {
//				System.out.println("Not given permission to modify user: " + user.getFirstName());
//			}
//			System.out.println(user.getEmail());
//		}
//		u = dao.selectUserById(2);
//		System.out.println("Employee user update with user: " + u.getFirstName());
//		for (User user : users) {
//			if (u.getUserId() == user.getUserId() || u.getRole().modifyOtherPermissions()) {
//				System.out.println("Have permission to modify user: " + user.getFirstName());
//				//Modify
//				user.setEmail("update2@email.com");
//				dao.updateUser(user);
//			} else {
//				System.out.println("Not given permission to modify user: " + user.getFirstName());
//			}
//			System.out.println(user.getEmail());
//		}
//		u = dao.selectUserById(3);
//		System.out.println("Admin user update with user: " + u.getFirstName());
//		for (User user : users) {
//			if (u.getUserId() == user.getUserId() || u.getRole().modifyOtherPermissions()) {
//				System.out.println("Have permission to modify user: " + user.getFirstName());
//				//Modify
//				user.setEmail("update3@email.com");
//				dao.updateUser(user);
//			} else {
//				System.out.println("Not given permission to modify user: " + user.getFirstName());
//			}
//			System.out.println(user.getEmail());
//		}
		
		//Update account
//		Scanner sc = new Scanner(System.in);
//		List<Account> accounts = dao.selectAllAccounts();
//		System.out.println("Which account do you want to modify?");
//		for (Account a : accounts) {
//			System.out.println(dao.selectUserById(a.getUserId()).getFirstName() + ": "
//					+ a.getAccountId() + " - " + a.getType().getType() + " "
//					+ a.getStatus().getStatus() + " " + a.getBalance());
//		}
//		int id = sc.nextInt();
//		Account a = dao.selectAccountById(id);
//		a.setStatus(dao.selectStatusById(STATUS_OPEN_ID));
//		dao.updateAccount(a);
	}

}
