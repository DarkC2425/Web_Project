package com.shop.dao;

import java.util.List;

import com.shop.model.OrderedProduct;

public interface IOrderedProductDao {
	public void insertOrderedProduct(OrderedProduct ordProduct);

	public List<OrderedProduct> getAllOrderedProduct(String oid);
}
