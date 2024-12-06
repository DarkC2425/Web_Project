package services;

import java.util.List;

import models.Product;

public interface IProductService {
	void insert(Product product);

	// khong update cot PID
	void update(Product product);

	// Xoa product dua tren cot PID
	void delete(int PID);

	// ---Cac ham read---
	Product findByPID(int PID);

	List<Product> findByName(String name);
	
	List<Product> findByStoreID(int storeID);
	
	List<Product> listAll();
}
