package models;

import java.sql.Date;

public class Product {
	private int PID;
	private String name;
	private String description;
	private boolean isActive;
	private String listImages;
	private int categoryID;
	private float rating;
	private int storeID;
	private Date createdAt;
	private Date updatedAt;
	private int price;

	public Product() {
		super();
	}

	public Product(String name, String description, boolean isActive, String listImages, int categoryID, float rating,
			int storeID, Date createdAt, Date updatedAt, int price) {
		super();
		this.name = name;
		this.description = description;
		this.isActive = isActive;
		this.listImages = listImages;
		this.categoryID = categoryID;
		this.rating = rating;
		this.storeID = storeID;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.price = price;
	}

	public int getPID() {
		return PID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPID(int pID) {
		PID = pID;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getListImages() {
		return listImages;
	}

	public void setListImages(String listImages) {
		this.listImages = listImages;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getStoreID() {
		return storeID;
	}

	public void setStoreID(int storeID) {
		this.storeID = storeID;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
