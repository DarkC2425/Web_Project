package com.shop.service.Impl;

import java.util.List;

import com.shop.config.DatabaseConnection;
import com.shop.dao.IUserDao;
import com.shop.dao.Impl.UserDaoImpl;
import com.shop.model.User;
import com.shop.service.IUserService;
import com.shop.util.HashPassword;

public class UserServiceImpl implements IUserService {
	private IUserDao iUserDao = new UserDaoImpl(DatabaseConnection.getConnection());

	@Override
	public boolean saveUser(User user) {
		user.setUserPassword(HashPassword.hashWithSHA256(user.getUserPassword()).trim());
		return iUserDao.saveUser(user);
	}

	@Override
	public User getUserByEmailPassword(String userEmail, String userPassword) {
		return iUserDao.getUserByEmailPassword(userEmail, HashPassword.hashWithSHA256(userPassword).trim());
	}

	@Override
	public List<User> getAllUser() {
		return iUserDao.getAllUser();
	}

	@Override
	public void updateUserAddresss(User user) {
		iUserDao.updateUserAddresss(user);
	}

	@Override
	public void updateUserPasswordByEmail(String password, String mail) {
		iUserDao.updateUserPasswordByEmail(password, mail);
	}

	@Override
	public void updateUser(User user) {
		iUserDao.updateUser(user);
	}

	@Override
	public int userCount() {
		return iUserDao.userCount();
	}

	@Override
	public String getUserAddress(int uid) {
		return iUserDao.getUserAddress(uid);
	}

	@Override
	public String getUserName(int uid) {
		return iUserDao.getUserName(uid);
	}

	@Override
	public String getUserEmail(int uid) {
		return iUserDao.getUserEmail(uid);
	}

	@Override
	public String getUserPhone(int uid) {
		return iUserDao.getUserPhone(uid);
	}

	@Override
	public void deleteUser(int uid) {
		iUserDao.deleteUser(uid);
	}

	@Override
	public List<String> getAllEmail() {
		return iUserDao.getAllEmail();
	}
}
