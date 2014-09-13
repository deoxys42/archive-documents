package com.historyarchive.archivedocuments.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User implements Cloneable {
	@Pattern(regexp="[A-Za-zА-ЯЄІа-яєі0-9]*")
	@Size(min=5, max=20)
	private String passport;
	
	@Pattern(regexp="[^ ]*")
	@Size(min=8, max=30)
	private String password;
	
	@Pattern(regexp="[A-Za-zА-ЯЄІа-яєі]+[A-Za-zА-ЯЄІа-яєі']*[A-Za-zА-ЯЄІа-яєі]+")
	@Size(min=2, max=20)
	private String name;

	@Pattern(regexp="[A-Za-zА-ЯЄІа-яєі]+[A-Za-zА-ЯЄІа-яєі']*[A-Za-zА-ЯЄІа-яєі]+")
	@Size(min=2, max=20)
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
	
	public String getPassport() {
		return passport;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		if (!name.isEmpty()) {
			this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
		} else {
			this.name = name;
		}
	}
	
	public String getName() {
		return name;
	}

	public void setSurname(String surname) {
		if (!surname.isEmpty()) {
			this.surname = surname.substring(0, 1).toUpperCase() + 
					surname.substring(1);
		} else {
			this.surname = surname;
		}
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public String getAuthority() {
		return authority;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public User clone() throws CloneNotSupportedException {
		return (User) super.clone();
	}
}