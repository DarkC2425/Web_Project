package com.shop.service.Impl;

import java.util.List;

import com.shop.config.DatabaseConnection;
import com.shop.dao.IOrderedProductDao;
import com.shop.dao.Impl.OrderedProductDaoImpl;
import com.shop.model.OrderedProduct;
import com.shop.service.IOrderedProductService;

public class OrderedProductServiceImpl implements IOrderedProductService {
	private IOrderedProductDao iOrderedProductDao = new OrderedProductDaoImpl(DatabaseConnection.getConnection());

	@Override
	public void insertOrderedProduct(OrderedProduct ordProduct) {
		iOrderedProductDao.insertOrderedProduct(ordProduct);
	}

	@Override
	public List<OrderedProduct> getAllOrderedProduct(String oid) {
		return iOrderedProductDao.getAllOrderedProduct(oid);
	}
}
