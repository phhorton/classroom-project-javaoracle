package com.revature.users;

public abstract class Role {
	private int roleId;
	private String role;
	//Admin, Employee, Standard, Premium (optional)
	
	public Role(int roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public abstract boolean viewOtherPermissions();
	public abstract boolean modifyOtherPermissions();
	
}
