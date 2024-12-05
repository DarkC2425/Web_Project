package dao;

import java.util.List;

import models.Product;

public interface IProductDao {
	void insert(Product product);

	// khong update cot PID
	void update(Product product);

	// Xoa product dua tren cot PID
	void delete(int PID);

	// ---Cac ham read---
	Product findByPID(int UID);

	List<Product> findByName(String name);
	
	List<Product> listByStoreID(int storeID);
	
	List<Product> listAll();
}
