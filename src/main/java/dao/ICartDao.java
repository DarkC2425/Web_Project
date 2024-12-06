package dao;

import java.util.List;

import models.Cart;

public interface ICartDao {
	//--ham read--//
	public void insert(Cart cart);
	//--update dua tren CartID--//
	public void update(Cart cart);
	//--Delete Cart dua tren CartID--//
	public void delete(int CartID);

	public Cart findByCartID(int CartID);

	public List<Cart> findByUID(int UID);

	public List<Cart> listAll();
}
