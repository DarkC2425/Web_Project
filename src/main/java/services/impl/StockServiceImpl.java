package services.impl;

import java.util.List;

import dao.IStockDao;
import dao.impl.StockDaoImpl;
import models.Stock;
import services.IStockService;

public class StockServiceImpl implements IStockService {
	IStockDao iStockDao = new StockDaoImpl();

	@Override
	public void insert(Stock stock) {
		iStockDao.insert(stock);
	}

	@Override
	public void update(Stock stock) {
		iStockDao.update(stock);
	}

	@Override
	public void delete(int StockID) {
		iStockDao.delete(StockID);
	}

	@Override
	public Stock findByStockID(int StockID) {
		return iStockDao.findByStockID(StockID);
	}

	@Override
	public List<Stock> findByPID(int PID) {
		return iStockDao.findByPID(PID);
	}

	@Override
	public List<Stock> listAll() {
		return iStockDao.listAll();
	}

}
