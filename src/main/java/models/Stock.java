package models;

import java.sql.Date;

public class Stock {
	int StockID;
	int PID;
	String name;
	String color;
	String size;
	Date createdAt;
	Date updatedAt;

	public Stock() {

	}

	public Stock(int pID, String name, String color, String size, Date createdAt, Date updatedAt) {
		super();
		PID = pID;
		this.name = name;
		this.color = color;
		this.size = size;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getStockID() {
		return StockID;
	}

	public void setStockID(int stockID) {
		StockID = stockID;
	}

	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
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
