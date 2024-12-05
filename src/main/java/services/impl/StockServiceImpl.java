package services.impl;

import models.Stock;
import services.IStockService;

public class StockServiceImpl implements IStockService {
	public IStockService iStockService = new StockServiceImpl();
	@Override
	public Stock findStocksByPID(int PID) {
		return iStockService.findStocksByPID(PID);
	}

}
