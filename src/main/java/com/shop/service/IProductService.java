package com.shop.service;

import java.util.List;

import com.shop.model.Product;

public interface IProductService {
	public boolean saveProduct(Product product);

	public List<Product> getAllProducts();

	public List<Product> getProductsByPage(int page);

	public List<Product> getAllLatestProducts();

	public Product getProductsByProductId(int pid);

	public List<Product> getAllProductsByCategoryId(int catId, int page);

	public List<Product> getAllProductsByCategoryId(int catId);

	public List<Product> getAllProductsBySearchKey(String search, int page);

	public List<Product> getDiscountedProducts();

	public void updateProduct(Product product);

	public void updateQuantity(int id, int qty);

	public void deleteProduct(int pid);

	public int productCount();

	public float getProductPriceById(int pid);

	public int getProductQuantityById(int pid);

	public int productCountByCategoryId(int catId);

	public int productCountBySearchKey(String search);
}
