package com.shop.service.Impl;

import java.util.List;

import com.shop.config.DatabaseConnection;
import com.shop.dao.IProductDao;
import com.shop.dao.Impl.ProductDaoImpl;
import com.shop.model.Product;
import com.shop.service.IProductService;

public class ProductServiceImpl implements IProductService {
	private IProductDao iProductDao = new ProductDaoImpl(DatabaseConnection.getConnection());

	@Override
	public boolean saveProduct(Product product) {
		return iProductDao.saveProduct(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return iProductDao.getAllProducts();
	}

	@Override
	public List<Product> getProductsByPage(int page) {
		return iProductDao.getProductsByPage(page);
	}

	@Override
	public List<Product> getAllLatestProducts() {
		return iProductDao.getAllLatestProducts();
	}

	@Override
	public Product getProductsByProductId(int pid) {
		return iProductDao.getProductsByProductId(pid);
	}

	@Override
	public List<Product> getAllProductsByCategoryId(int catId, int page) {
		return iProductDao.getAllProductsByCategoryId(catId, page);
	}

	@Override
	public List<Product> getAllProductsBySearchKey(String search, int page) {
		return iProductDao.getAllProductsBySearchKey(search, page);
	}

	@Override
	public List<Product> getDiscountedProducts() {
		return iProductDao.getDiscountedProducts();
	}

	@Override
	public void updateProduct(Product product) {
		iProductDao.updateProduct(product);
	}

	@Override
	public void updateQuantity(int id, int qty) {
		iProductDao.updateQuantity(id, qty);
	}

	@Override
	public void deleteProduct(int pid) {
		iProductDao.deleteProduct(pid);
	}

	@Override
	public int productCount() {
		return iProductDao.productCount();
	}

	@Override
	public float getProductPriceById(int pid) {
		return iProductDao.getProductPriceById(pid);
	}

	@Override
	public int getProductQuantityById(int pid) {
		return iProductDao.getProductQuantityById(pid);
	}

	@Override
	public int productCountByCategoryId(int catId) {
		// TODO Auto-generated method stub
		return iProductDao.productCountByCategoryId(catId);
	}

	@Override
	public int productCountBySearchKey(String search) {
		// TODO Auto-generated method stub
		return iProductDao.productCountBySearchKey(search);
	}

	@Override
	public List<Product> getAllProductsByCategoryId(int catId) {
		// TODO Auto-generated method stub
		return iProductDao.getAllProductsByCategoryId(catId);
	}
}
