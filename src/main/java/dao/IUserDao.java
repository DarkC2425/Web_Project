package dao;

import models.User;

public interface IUserDao {

	void insert(User user);

	User findByUID(int UID);

	User findByEmail(String Email);
}
