package com.shop.service.Impl;

import java.util.List;

import com.shop.config.DatabaseConnection;
import com.shop.dao.IWishlistDao;
import com.shop.dao.Impl.WishlistDaoImpl;
import com.shop.model.Wishlist;
import com.shop.service.IWishlistService;

public class WishlistServiceImpl implements IWishlistService {
	private IWishlistDao iWishlistDao = new WishlistDaoImpl(DatabaseConnection.getConnection());

	@Override
	public boolean addToWishlist(Wishlist w) {
		return iWishlistDao.addToWishlist(w);
	}

	@Override
	public boolean getWishlist(int uid, int pid) {
		return iWishlistDao.getWishlist(uid, pid);
	}

	@Override
	public List<Wishlist> getListByUserId(int uid) {
		return iWishlistDao.getListByUserId(uid);
	}

	@Override
	public void deleteWishlist(int uid, int pid) {
		iWishlistDao.deleteWishlist(uid, pid);
	}
}
