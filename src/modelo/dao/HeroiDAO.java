package modelo.dao;

import java.util.ArrayList;

import modelo.vo.Heroi;

public abstract class HeroiDAO {

	public abstract void inserir(Heroi heroi);
	
	public abstract int ultimoHeroi();
	
	public abstract Heroi buscarHeroi(int id);

	public abstract void deletarHeroi(int id);

	public abstract ArrayList<Heroi> listar();

	public abstract void atualizar(Heroi heroi);

}
