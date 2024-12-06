package services;

import java.util.List;

import models.Store;

public interface IStoreService {
	void insert(Store store);

	// khong update cot SID
	void update(Store store);

	// Xoa product dua tren cot SID
	void delete(int SID);

	// ---Cac ham read---
	Store findBySID(int SID);

	List<Store> findByName(String name);
}
