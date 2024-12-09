package com.shop.service.Impl;

import java.util.List;

import com.shop.config.DatabaseConnection;
import com.shop.dao.ICategoryDao;
import com.shop.dao.Impl.CategoryDaoImpl;
import com.shop.model.Category;
import com.shop.model.Product;
import com.shop.service.ICategoryService;
import com.shop.service.IProductService;

public class CategoryServiceImpl implements ICategoryService {
	private ICategoryDao iCategoryDao = new CategoryDaoImpl(DatabaseConnection.getConnection());

	@Override
	public boolean saveCategory(Category category) {
		return iCategoryDao.saveCategory(category);
	}

	@Override
	public List<Category> getAllCategories() {
		return iCategoryDao.getAllCategories();
	}

	@Override
	public Category getCategoryById(int cid) {
		return iCategoryDao.getCategoryById(cid);
	}

	@Override
	public String getCategoryName(int catId) {
		return iCategoryDao.getCategoryName(catId);
	}

	@Override
	public void updateCategory(Category cat) {
		iCategoryDao.updateCategory(cat);
	}

	@Override
	public void deleteCategory(int cid) {
		IProductService iProductService = new ProductServiceImpl();
		if (iProductService.productCountByCategoryId(cid) == 0) {
			iCategoryDao.deleteCategory(cid);
		} else {
			for (Product product : iProductService.getAllProductsByCategoryId(cid)) {
				iProductService.deleteProduct(product.getProductId());
			}
		}

	}

	@Override
	public int categoryCount() {
		return iCategoryDao.categoryCount();
	}
}
