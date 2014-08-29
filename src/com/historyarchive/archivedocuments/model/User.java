package com.historyarchive.archivedocuments.model;

public class User implements Cloneable {
	private String passport;
	private String password;
	private String name;
	private String surname;
	private String authority;

	private boolean enabled;
		
	public User() {
	}
	
	public User(String passport, String password, String name, String surname) {
		
		this.setPassport(passport);
		this.password = password;
		this.setName(name);
		this.setSurname(surname);
		this.authority = "ROLE_USER";
	}
	
	public void setPassport(String passport) {
		this.passport = passport.toUpperCase();
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	public void setSurname(String surname) {
		this.surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassport() {
		return passport;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}
	
	public String getAuthority() {
		return authority;
	}

	public boolean isEnabled() {
		return enabled;
	}
	
	public User clone() throws CloneNotSupportedException {
		return (User) super.clone();
	}
}