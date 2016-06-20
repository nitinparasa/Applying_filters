package se.bth.softhouse.entities;

public class Users {
	int id;
	String username;
	String emailid;
	String password;
	String confirm_password;

	public Users() {
		super();
	}

	public Users(int id) {
		super();
		this.id = id;
	}

	public Users(int id, String username, String emailid) {
		super();
		this.id = id;
		this.username = username;
		this.emailid = emailid;
	}

	public Users(int id, String username, String emailid, String password) {
		super();
		this.id = id;
		this.username = username;
		this.emailid = emailid;
		this.password = password;
	}

	public Users(int id, String username, String emailid, String password, String confirm_password) {
		super();
		this.id = id;
		this.username = username;
		this.emailid = emailid;
		this.password = password;
		this.confirm_password = confirm_password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

}
