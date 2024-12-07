package com.shop.dao;

import java.util.List;

import com.shop.model.Product;

public interface IProductDao {
	public boolean saveProduct(Product product);

	public List<Product> getAllProducts();

	public List<Product> getAllLatestProducts();

	public Product getProductsByProductId(int pid);

	public List<Product> getAllProductsByCategoryId(int catId);

	public List<Product> getAllProductsBySearchKey(String search);

	public List<Product> getDiscountedProducts();

	public void updateProduct(Product product);

	public void updateQuantity(int id, int qty);

	public void deleteProduct(int pid);

	public int productCount();

	public float getProductPriceById(int pid);

	public int getProductQuantityById(int pid);
}
