package dao;

import java.util.List;

import models.Address;


public interface IAddressDao {
	void insert(Address address);

	//Update bang cot AddressID
	void update(Address address);

	//Xoa product dua tren cot AddressID
	void delete(int AddressID);

	// ---Cac ham read---
	Address findByAddressID(int AddressID);

	List<Address> findByUID(int UID);
	
	List<Address> listAll();
}
