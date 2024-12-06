package models;

import java.sql.Date;

public class Store {
	private int SID;
	private String name;
	private String email;
	private String phone;
	private int ownerID;
	private boolean isActive;
	private String avatar;
	private float rating;
	private Date createdAt;
	private Date updatedAt;

	public Store() {
		super();
	}

	public Store(String name, String email, String phone, int ownerID, boolean isActive, String avatar, float rating,
			Date createdAt, Date updatedAt) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.ownerID = ownerID;
		this.isActive = isActive;
		this.avatar = avatar;
		this.rating = rating;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getSID() {
		return SID;
	}

	public void setSID(int sID) {
		SID = sID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
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

}
