package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.accounts.Account;
import com.revature.accounts.AccountStatus;
import com.revature.accounts.AccountType;
import com.revature.users.Admin;
import com.revature.users.Employee;
import com.revature.users.Role;
import com.revature.users.Standard;
import com.revature.users.User;
import com.revature.util.ConnectionFactory;

public class BankingDAOImpl implements BankingDAO {

	//Insert
	@Override
	public void insertUser(User u) {//Admin only
		Connection conn = ConnectionFactory.getConnection();
		String sql = "INSERT INTO banking_users VALUES (?,?,?,?,?,?,?);";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getUserId());
			ps.setString(2, u.getUsername());
			ps.setString(3, u.getPassword());
			ps.setString(4, u.getFirstName());
			ps.setString(5, u.getLastName());
			ps.setString(6, u.getEmail());
			ps.setInt(7, u.getRole().getRoleId());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Override
//	public void insertRole(Role r) {
//		Connection conn = ConnectionFactory.getConnection();
//		String sql = "INSERT INTO banking_roles VALUES (?,?);";
//		
//		try {
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, r.getRoleId());
//			ps.setString(2, r.getRole());
//			ps.execute();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	@Override
	public void insertAccount(Account a) {//Employee, Admin, or select only current user
		Connection conn = ConnectionFactory.getConnection();
		String sql = "INSERT INTO banking_accounts VALUES (?,?,?,?,?);";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getAccountId());
			ps.setDouble(2, a.getBalance());
			ps.setInt(3, a.getStatus().getStatusId());
			ps.setInt(4, a.getType().getTypeId());
			ps.setInt(5, a.getUserId());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Override
//	public void insertAccountStatus(AccountStatus s) {
//		Connection conn = ConnectionFactory.getConnection();
//		String sql = "INSERT INTO banking_account_status VALUES (?,?);";
//		
//		try {
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, s.getStatusId());
//			ps.setString(2, s.getStatus());
//			ps.execute();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	@Override
//	public void insertAccountType(AccountType t) {
//		Connection conn = ConnectionFactory.getConnection();
//		String sql = "INSERT INTO banking_account_type VALUES (?,?);";
//		
//		try {
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, t.getTypeId());
//			ps.setString(2, t.getType());
//			ps.execute();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	//Select
	@Override
	public List<User> selectAllUsers() {//Employee and Admins
		List<User> users = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM banking_users";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				users.add(new User(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						selectRoleById(rs.getInt(7))
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}

	@Override
	public User selectUserById(int userId) {//Employee, Admin, or select only current user
		User u = null;
		Connection conn = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM banking_users WHERE id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				u = new User(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						selectRoleById(rs.getInt(7))
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public List<Role> selectAllRoles() {
		List<Role> roles = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM banking_roles";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if (rs.getString(2).equals("Standard")) {
					roles.add(new Standard(
							rs.getInt(1),
							rs.getString(2)));
				} else if (rs.getString(2).equals("Employee")) {
					roles.add(new Employee(
							rs.getInt(1),
							rs.getString(2)));
				} else if (rs.getString(2).equals("Admin")) {
					roles.add(new Admin(
							rs.getInt(1),
							rs.getString(2)));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return roles;
	}

	@Override
	public Role selectRoleById(int roleId) {
		Role r = null;
		Connection conn = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM banking_roles WHERE id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, roleId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString(2).equals("Standard")) {
					r = new Standard(
							rs.getInt(1),
							rs.getString(2)
							);
				} else if (rs.getString(2).equals("Employee")) {
					r = new Employee(
							rs.getInt(1),
							rs.getString(2)
							);
				} else if (rs.getString(2).equals("Admin")) {
					r = new Admin(
							rs.getInt(1),
							rs.getString(2)
							);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return r;
	}
	
	@Override
	public List<Account> selectAllAccounts() {//Employee and Admin
		List<Account> accounts = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM banking_accounts";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				accounts.add(new Account(
						rs.getInt(1),
						rs.getDouble(2),
						selectStatusById(rs.getInt(3)),
						selectTypeById(rs.getInt(4)),
						rs.getInt(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return accounts;
	}

	@Override
	public Account selectAccountById(int accountId) {//Employee, Admin, or select only current user
		Account a = null;
		Connection conn = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM banking_accounts WHERE id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accountId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				a = new Account(
						rs.getInt(1),
						rs.getDouble(2),
						selectStatusById(rs.getInt(3)),
						selectTypeById(rs.getInt(4)),
						rs.getInt(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return a;
	}

	@Override
	public List<Account> selectAccountByStatus(int statusId) {//Employee and Admin
		List<Account> accounts = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM banking_accounts WHERE status_id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, statusId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				accounts.add(new Account(
						rs.getInt(1),
						rs.getDouble(2),
						selectStatusById(rs.getInt(3)),
						selectTypeById(rs.getInt(4)),
						rs.getInt(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return accounts;
	}

	@Override
	public List<Account> selectAccountByUser(int userId) {//Employee, Admin, or select only current user
		List<Account> accounts = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM banking_accounts WHERE user_id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				accounts.add(new Account(
						rs.getInt(1),
						rs.getDouble(2),
						selectStatusById(rs.getInt(3)),
						selectTypeById(rs.getInt(4)),
						rs.getInt(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return accounts;
	}

	@Override
	public List<AccountStatus> selectAllStatus() {
		List<AccountStatus> status = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM banking_account_status";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				status.add(new AccountStatus(
						rs.getInt(1),
						rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public AccountStatus selectStatusById(int statusId) {
		AccountStatus a = null;
		Connection conn = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM banking_account_status WHERE id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, statusId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				a = new AccountStatus(
						rs.getInt(1),
						rs.getString(2)
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
	@Override
	public List<AccountType> selectAllTypes() {
		List<AccountType> types = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM banking_account_type";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				types.add(new AccountType(
						rs.getInt(1),
						rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return types;
	}

	@Override
	public AccountType selectTypeById(int typeId) {
		AccountType a = null;
		Connection conn = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM banking_account_type WHERE id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, typeId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				a = new AccountType(
						rs.getInt(1),
						rs.getString(2)
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
	//Update
	@Override
	public void updateUser(User u) {//Admin only
		Connection conn = ConnectionFactory.getConnection();
		String sql = "UPDATE banking_users SET username = ?, password = ?, first_name = ?, last_name = ?, email = ?, role_id = ? WHERE id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setString(5, u.getEmail());
			ps.setInt(6, u.getRole().getRoleId());
			ps.setInt(7, u.getUserId());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateAccount(Account a) {//Admin only
		Connection conn = ConnectionFactory.getConnection();
		String sql = "UPDATE banking_accounts SET balance = ?, status_id = ?, type_id = ?,"
				+ " user_id = ? WHERE id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, a.getBalance());
			ps.setInt(2, a.getStatus().getStatusId());
			ps.setInt(3, a.getType().getTypeId());
			ps.setInt(4, a.getUserId());
			ps.setInt(5, a.getAccountId());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
