package com.shop.service.Impl;

import java.util.List;

import com.shop.config.DatabaseConnection;
import com.shop.dao.ICartDao;
import com.shop.dao.Impl.CartDaoImpl;
import com.shop.model.Cart;
import com.shop.service.ICartService;

public class CartServiceImpl implements ICartService {
	ICartDao iCartDao = new CartDaoImpl(DatabaseConnection.getConnection());

	@Override
	public boolean addToCart(Cart cart) {
		return iCartDao.addToCart(cart);
	}

	@Override
	public List<Cart> getCartListByUserId(int uid) {
		return iCartDao.getCartListByUserId(uid);
	}

	@Override
	public int getQuantity(int uid, int pid) {
		return iCartDao.getQuantity(uid, pid);
	}

	@Override
	public int getQuantityById(int id) {
		return iCartDao.getQuantityById(id);
	}

	@Override
	public void updateQuantity(int id, int qty) {
		iCartDao.updateQuantity(id, qty);
	}

	@Override
	public void removeProduct(int cid) {
		iCartDao.removeProduct(cid);
	}

	@Override
	public void removeAllProduct() {
		iCartDao.removeAllProduct();
	}

	@Override
	public int getIdByUserIdAndProductId(int uid, int pid) {
		return iCartDao.getIdByUserIdAndProductId(uid, pid);
	}

	@Override
	public int getCartCountByUserId(int uid) {
		return iCartDao.getCartCountByUserId(uid);
	}

	@Override
	public int getProductId(int cid) {
		return iCartDao.getProductId(cid);
	}

}
