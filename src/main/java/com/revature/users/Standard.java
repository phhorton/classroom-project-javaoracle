package com.revature.users;

public class Standard extends Role {

	public Standard(int roleId, String role) {
		super(roleId, role);
	}

	@Override
	public boolean viewOtherPermissions() {
		return false;
	}

	@Override
	public boolean modifyOtherPermissions() {
		return false;
	}

}
