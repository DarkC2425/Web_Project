package dao;

import java.util.List;

import models.Order;

public interface IOrdersDao {
	 public void insert(Order order);
	 
	 public void update(Order order);
	 
	 public void delete(int OID);
	 
	 public Order findByOID(int OID);
	 
	 public List<Order> findByUID(int UID);
	 
	 public List<Order> listAll();
}
