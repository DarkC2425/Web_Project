package com.shop.service;

import java.util.List;

import com.shop.model.User;

public interface IUserService {

	boolean saveUser(User user);

	User getUserByEmailPassword(String userEmail, String userPassword);

	List<User> getAllUser();

	void updateUserAddresss(User user);

	void updateUserPasswordByEmail(String password, String mail);

	void updateUser(User user);

	int userCount();

	String getUserAddress(int uid);

	String getUserName(int uid);

	String getUserEmail(int uid);

	String getUserPhone(int uid);

	void deleteUser(int uid);

	List<String> getAllEmail();

}