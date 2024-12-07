package com.shop.dao;

import java.util.List;

import com.shop.model.Admin;

public interface IAdminDao {
	public boolean saveAdmin(Admin admin);

	public Admin getAdminByEmailPassword(String email, String password);

	public List<Admin> getAllAdmin();

	public boolean deleteAdmin(int id);
}
