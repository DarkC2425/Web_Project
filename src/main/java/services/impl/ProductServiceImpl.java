package services.impl;

import java.util.List;

import dao.impl.ProductDaoImpl;
import models.Product;
import services.IProductService;

public class ProductServiceImpl implements IProductService{

	ProductDaoImpl productDao = new ProductDaoImpl();
	@Override
	public void insert(Product product) {
		productDao.insert(product);
	}

	@Override
	public void update(Product product) {
		productDao.update(product);
		
	}

	@Override
	public void delete(int PID) {
		productDao.delete(PID);
		
	}

	@Override
	public Product findByPID(int PID) {
		return productDao.findByPID(PID);
		
	}

	@Override
	public List<Product> findByName(String name) {
		return productDao.findByName(name);
	}

	@Override
	public List<Product> findByStoreID(int storeID) {
		return productDao.findByStoreID(storeID);
	}

	@Override
	public List<Product> listAll() {
		return productDao.listAll();
	}

}
