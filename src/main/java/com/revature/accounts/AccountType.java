package com.revature.accounts;

public class AccountType {
	private int typeId;		//primary key
	private String type;	//not null, unique
	//Checking, Savings
	
	public AccountType(int typeId, String type) {
		super();
		this.typeId = typeId;
		this.type = type;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (type.equals("Checking") || type.equals("Savings")) {
			this.type = type;
		}
	}
	
	
}
