package com.shop.service.Impl;

import java.util.List;

import com.shop.config.DatabaseConnection;
import com.shop.dao.IOrderDao;
import com.shop.dao.Impl.OrderDaoImpl;
import com.shop.model.Order;
import com.shop.service.IOrderService;

public class OrderServiceImpl implements IOrderService {
	private IOrderDao iOrderDao = new OrderDaoImpl(DatabaseConnection.getConnection());

	@Override
	public int insertOrder(Order order) {
		return iOrderDao.insertOrder(order);
	}

	@Override
	public List<Order> getAllOrderByUserId(int uid) {
		return iOrderDao.getAllOrderByUserId(uid);
	}

	@Override
	public Order getOrderById(int id) {
		return iOrderDao.getOrderById(id);
	}

	@Override
	public List<Order> getAllOrder() {
		return iOrderDao.getAllOrder();
	}

	@Override
	public void updateOrderStatus(int oid, String status) {
		iOrderDao.updateOrderStatus(oid, status);
	}
}
