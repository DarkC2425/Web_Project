package dao;

import java.util.List;

import models.Category;


public interface ICategoryDao {
	void insert(Category category);

	//Update bang cot CID
	void update(Category category);

	// Xoa product dua tren cot CID
	void delete(int CID);

	// ---Cac ham read---
	Category findByPID(int CID);

	List<Category> findByName(String name);
	
	List<Category> listAll();
}
