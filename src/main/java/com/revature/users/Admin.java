package com.revature.users;

public class Admin extends Role {

	public Admin(int roleId, String role) {
		super(roleId, role);
	}

	@Override
	public boolean viewOtherPermissions() {
		return true;
	}

	@Override
	public boolean modifyOtherPermissions() {
		return true;
	}

}
