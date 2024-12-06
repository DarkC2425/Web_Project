package models;

public class Address {
	int AddressID;
	int UID;
	String name;
	String phone;
	String address;

	public Address() {
		super();
	}

	public Address(int uID, String name, String phone, String address) {
		super();
		UID = uID;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	public int getAddressID() {
		return AddressID;
	}

	public void setAddressID(int addressID) {
		AddressID = addressID;
	}

	public int getUID() {
		return UID;
	}

	public void setUID(int uID) {
		UID = uID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
