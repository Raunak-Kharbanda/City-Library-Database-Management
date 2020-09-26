package models;

public class AccountInfo {
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	public AccountInfo(int id, String username, float balance) {
		this.balance = balance;
		this.id = id;
		this.userName = username;
	}
	
	
	int id;
	String userName;
	float balance;

}
