package com.shop.service;

import java.util.List;

import com.shop.model.Category;

public interface ICategoryService {
	public boolean saveCategory(Category category);

	public List<Category> getAllCategories();

	public Category getCategoryById(int cid);

	public String getCategoryName(int catId);

	public void updateCategory(Category cat);

	public void deleteCategory(int cid);

	public int categoryCount();
}
