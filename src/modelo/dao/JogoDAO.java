package modelo.dao;

import java.util.List;

import modelo.vo.Jogo;
import modelo.vo.Monstro;

public abstract class JogoDAO {

	public abstract void inserirJogo(Jogo j);
	public abstract Jogo buscarJogo(int id);
	public abstract void alterarJogo(Jogo j);
	public abstract void deletarJogo(Jogo j);
	public abstract List<Monstro> buscarJogoMonstros(int id);
	public abstract int ultimoJogo();
	public abstract void addMonstrosJogo(int id,int idMonstro);
}
