package com.revature.users;

public class Employee extends Role {

	public Employee(int roleId, String role) {
		super(roleId, role);
	}

	@Override
	public boolean viewOtherPermissions() {
		return true;
	}

	@Override
	public boolean modifyOtherPermissions() {
		return false;
	}

}
