package com.shop.service;

import java.util.List;

import com.shop.model.OrderedProduct;

public interface IOrderedProductService {
	public void insertOrderedProduct(OrderedProduct ordProduct);

	public List<OrderedProduct> getAllOrderedProduct(String oid);
}
