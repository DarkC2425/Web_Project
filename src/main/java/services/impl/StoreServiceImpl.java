package services.impl;

import java.util.List;

import dao.IStoreDao;
import dao.impl.StoreDaoImpl;
import models.Store;
import services.IStoreService;

public class StoreServiceImpl implements IStoreService {
	IStoreDao iStoreDao = new StoreDaoImpl();

	@Override
	public void insert(Store store) {
		iStoreDao.insert(store);
	}

	@Override
	public void update(Store store) {
		iStoreDao.update(store);
	}

	@Override
	public void delete(int SID) {
		iStoreDao.delete(SID);
	}

	@Override
	public Store findBySID(int SID) {
		return iStoreDao.findBySID(SID);
	}

	@Override
	public List<Store> findByName(String name) {
		return iStoreDao.findByName(name);
	}

}
