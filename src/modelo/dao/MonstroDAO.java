package modelo.dao;

import java.util.ArrayList;

import modelo.vo.Monstro;

public abstract class MonstroDAO {
	
public abstract void inserir(Monstro m);
	
	public abstract Monstro buscarMonstro(int id);

	public abstract void deletarMonstro();

	public abstract ArrayList<Monstro> listar();

	public abstract void atualizar(Monstro m);
	
	public abstract int ultimoMonstro();

}
