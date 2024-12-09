package com.shop.service;

import java.util.List;

import com.shop.model.Order;

public interface IOrderService {
	public int insertOrder(Order order);

	public List<Order> getAllOrderByUserId(int uid);

	public Order getOrderById(int id);

	public List<Order> getAllOrder();

	public void updateOrderStatus(int oid, String status);
}
