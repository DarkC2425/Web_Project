package dao;

import models.User;

public interface IUserDao {
	void insert(User user);
	
	//Chi update first/last name, phone, avatar, khong update cac cot khac
	void update(User user);
	
	//Xoa user dua tren UID
	void delete(int UID);
	
	//---Cac ham read---
	User findByUID(int UID);

	User findByEmail(String Email);
}
