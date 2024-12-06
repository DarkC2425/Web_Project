package services.impl;

import java.util.List;

import dao.IProductDao;
import dao.impl.ProductDaoImpl;
import models.Product;
import services.IProductService;

public class ProductServiceImpl implements IProductService {
	IProductDao iProductDao = new ProductDaoImpl();

	@Override
	public List<Product> getProductsByPage(int page, int pageSize) {
		List<Product> allProducts = iProductDao.listAll();
		int fromIndex = (page - 1) * pageSize;
		int toIndex = Math.min(fromIndex + pageSize, allProducts.size());
		if (fromIndex >= allProducts.size()) {
			return List.of();
		}
		return allProducts.subList(fromIndex, toIndex);
	}

	@Override
	public List<Product> getAllProducts() {
		return iProductDao.listAll();
	}

	@Override
	public int getTotalProducts() {
		return iProductDao.listAll().size();
	}

	@Override
	public void insert(Product product) {
		iProductDao.insert(product);

	}

	@Override
	public void update(Product product) {
		iProductDao.update(product);

	}

	@Override
	public void delete(int PID) {
		iProductDao.delete(PID);
	}

	@Override
	public Product findByPID(int PID) {
		return iProductDao.findByPID(PID);
	}
}
