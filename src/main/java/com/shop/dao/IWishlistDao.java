package com.shop.dao;

import java.util.List;

import com.shop.model.Wishlist;

public interface IWishlistDao {

	boolean addToWishlist(Wishlist w);

	boolean getWishlist(int uid, int pid);

	List<Wishlist> getListByUserId(int uid);

	void deleteWishlist(int uid, int pid);

}