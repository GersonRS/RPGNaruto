package modelo.dao;

import java.util.List;

import modelo.vo.Item;

public abstract class ItemDAO {
	
	public abstract void addItem(int idHeroi, int idItem);

	public abstract List<Item> buscarInventario(int id);
	
	public abstract Item buscarItem(int id);
	
}
