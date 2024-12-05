package services.impl;

import java.sql.Date;

import dao.IUserDao;
import dao.impl.UserDaoImpl;
import models.User;
import services.IUserService;

public class UserServiceImpl implements IUserService {
	public IUserDao iUserDao = new UserDaoImpl();

	@Override
	public User findByUID(int UID) {
		// TODO Auto-generated method stub
		return iUserDao.findByUID(UID);
	}

	@Override
	public void register(String firstName, String lastName, String email, String phone, String password, String address,
			String role) {
		Date createdAt = new Date(System.currentTimeMillis());
		String hash_password = utils.Function.hashWithSHA256(password);
		iUserDao.insert(
				new User(firstName, lastName, email, phone, hash_password, null, address, createdAt, null, role));
	}

	@Override
	public User login(String email, String password) {
		User user = iUserDao.findByEmail(email);
		if (user == null) {
			return null;
		} else if (user.getHashed_pasword().trim().equals(utils.Function.hashWithSHA256(password))) {
			return user;
		}
		return null;
	}

}
