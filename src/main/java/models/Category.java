package models;

import java.sql.Date;

public class Category {
	int CID;
	String name;
	String image;
	Date createdAt;
	Date updatedAt;

	public Category() {

	}

	public Category(String name, String image, Date createdAt, Date updatedAt) {
		super();
		this.name = name;
		this.image = image;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getCID() {
		return CID;
	}

	public void setCID(int cID) {
		CID = cID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
