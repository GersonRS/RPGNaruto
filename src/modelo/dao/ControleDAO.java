package modelo.dao;

import modelo.vo.Controle;

public abstract class ControleDAO {

	public abstract Controle getControlePorChave(int id);
	
	public abstract void inserir(Controle c);
	
	public abstract int ultimoControle();
	
}
