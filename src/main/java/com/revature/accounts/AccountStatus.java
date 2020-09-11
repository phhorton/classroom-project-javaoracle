package com.revature.accounts;

public class AccountStatus {
	private int statusId;	//primary key
	private String status;	//not null, unique
	//Pending, Open, Closed, Denied
	
	public AccountStatus(int statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		if (status.equals("Pending") || status.equals("Open") || status.equals("Closed") || status.equals("Denied")) {
			this.status = status;
		}
	}
	
	
}
