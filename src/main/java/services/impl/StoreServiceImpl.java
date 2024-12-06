package services.impl;

import java.util.List;

import dao.impl.StoreDaoImpl;
import models.Store;
import services.IStoreService;

public class StoreServiceImpl implements IStoreService {

	StoreDaoImpl storeDao = new StoreDaoImpl();
	@Override
	public void insert(Store store) {
		storeDao.insert(store);
		
	}

	@Override
	public void update(Store store) {
		storeDao.update(store);
		
	}

	@Override
	public void delete(int SID) {
		storeDao.delete(SID);
		
	}

	@Override
	public Store findBySID(int SID) {
		return storeDao.findBySID(SID);
	}

	@Override
	public List<Store> findByName(String name) {
		return storeDao.findByName(name);
	}

}
