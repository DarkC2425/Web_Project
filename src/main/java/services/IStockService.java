package services;

import java.util.List;

import models.Stock;

public interface IStockService {
	void insert(Stock stock);

	// Update dua tren cot SID
	void update(Stock stock);

	// Xoa product dua tren cot SID
	void delete(int StockID);

	// ---Cac ham read---
	Stock findByStockID(int StockID);

	List<Stock> findByPID(int PID);

	List<Stock> listAll();

}
