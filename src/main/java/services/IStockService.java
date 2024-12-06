package services;

import models.User;

public interface IStockService {
	public void register(String firstName, String lastName, String email, String phone, String password, String address,
			String role);

	public User login(String email, String password);

	public User findByUID(int UID);

}