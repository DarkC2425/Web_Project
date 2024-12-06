package services;

import models.User;

public interface IUserService {
	public void register(String firstName, String lastName, String email, String phone, String password, String address,
			String role);

	public User login(String email, String password);

	public User findByUID(int UID);

	// Chi update first/last name, phone, avatar, khong update cac cot khac
	void update(User user);

	// Xoa user dua tren UID
	void delete(int UID);

}
