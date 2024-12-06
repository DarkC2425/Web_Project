package models;

import java.sql.Date;

public class User {
	private int UID;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String hashed_pasword;
	private String avatar;
	private String address;
	private Date createdAt;
	private Date updatedAt;
	private String role;

	public User() {
		super();
	}

	public User(String firstName, String lastName, String email, String phone, String hashed_pasword, String avatar,
			String address, Date createdAt, Date updatedAt, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.hashed_pasword = hashed_pasword;
		this.avatar = avatar;
		this.address = address;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.role = role;
	}

	public int getUID() {
		return UID;
	}

	public void setUID(int uID) {
		UID = uID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHashed_pasword() {
		return hashed_pasword;
	}

	public void setHashed_pasword(String hashed_pasword) {
		this.hashed_pasword = hashed_pasword;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
