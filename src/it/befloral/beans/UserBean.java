package it.befloral.beans;

import java.io.Serializable;

public class UserBean implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String email;
	private String password;
	private String role;
	private boolean active;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	@Override
	public String toString() {
		return "UserBean [id=" + id + ", username=" + email + ", password="
				+ password + ", role=" + role + ", active=" + active + "]";
	}

}
