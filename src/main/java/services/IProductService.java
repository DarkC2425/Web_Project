package services;

import java.util.List;

import models.Product;

public interface IProductService {
	public List<Product> getAllProducts();

	public List<Product> getProductsByPage(int page, int pageSize);

	public Product findByPID(int PID);

	public void insert(Product product);

	public void update(Product product);

	public void delete(int PID);

	public int getTotalProducts();

}
