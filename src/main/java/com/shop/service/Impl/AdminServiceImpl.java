package com.shop.service.Impl;

import java.util.List;

import com.shop.config.DatabaseConnection;
import com.shop.dao.IAdminDao;
import com.shop.dao.Impl.AdminDaoImpl;
import com.shop.model.Admin;
import com.shop.service.IAdminService;

public class AdminServiceImpl implements IAdminService {
	IAdminDao iAdminDao = new AdminDaoImpl(DatabaseConnection.getConnection());

	@Override
	public boolean saveAdmin(Admin admin) {
		return iAdminDao.saveAdmin(admin);
	}

	@Override
	public Admin getAdminByEmailPassword(String email, String password) {
		return iAdminDao.getAdminByEmailPassword(email, password);
	}

	@Override
	public List<Admin> getAllAdmin() {
		// TODO Auto-generated method stub
		return iAdminDao.getAllAdmin();
	}

	@Override
	public boolean deleteAdmin(int id) {
		// TODO Auto-generated method stub
		return iAdminDao.deleteAdmin(id);
	}

}
