package models;

import java.sql.Date;

public class Order {
	int OID;
	int UID;
	int StockID;
	int amount;
	int AddressID;
	int payment;
	Date createdAt;
	Date updatedAt;
	int status;

	public Order() {
		super();
	}

	public Order(int uID, int stockID, int amount, int addressID, int payment, Date createdAt, Date updateAt,
			int status) {
		super();
		UID = uID;
		StockID = stockID;
		this.amount = amount;
		AddressID = addressID;
		this.payment = payment;
		this.createdAt = createdAt;
		this.updatedAt = updateAt;
		this.status = status;
	}

	public int getOID() {
		return OID;
	}

	public void setOID(int oID) {
		OID = oID;
	}

	public int getUID() {
		return UID;
	}

	public void setUID(int uID) {
		UID = uID;
	}

	public int getStockID() {
		return StockID;
	}

	public void setStockID(int stockID) {
		StockID = stockID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getAddressID() {
		return AddressID;
	}

	public void setAddressID(int addressID) {
		AddressID = addressID;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
