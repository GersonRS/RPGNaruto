package modelo;

import java.util.List;

import modelo.dao.FabricaDeDAOs;
import modelo.dao.FabricaDeDAOsMySql;
import modelo.dao.HeroiDAO;
import modelo.dao.JogoDAO;
import modelo.dao.MonstroDAO;
import modelo.vo.Heroi;
import modelo.vo.Jogo;
import view.MundoShinobi;

public class Fachada {
	
	public static FabricaDeDAOs fabrica = new FabricaDeDAOsMySql();
	
	public static void criarNovoJogo(Jogo j){
		HeroiDAO heroiDao = fabrica.criarHeroiDAO();
		heroiDao.inserir(j.getH());
		j.getH().setId(heroiDao.ultimoHeroi());
		JogoDAO jogoDao = fabrica.criarJogoDAO();
		jogoDao.inserirJogo(j);
		MonstroDAO monstroDao = fabrica.criarMonstroDAO();
		int num  = jogoDao.ultimoJogo();
		for (int i = 0; i < j.getM().size(); i++) {
			monstroDao.inserir(j.getM().get(i));
			jogoDao.addMonstrosJogo(num, monstroDao.ultimoMonstro());
		}
	}
	
	public static void salvarJogo(Jogo j){
		JogoDAO jogoDao = fabrica.criarJogoDAO();
		HeroiDAO heroiDao = fabrica.criarHeroiDAO();
		MonstroDAO monstroDao = fabrica.criarMonstroDAO();
		
		jogoDao.alterarJogo(j);
		jogoDao.deletarJogo(j);
		
		monstroDao.deletarMonstro();
		
		heroiDao.atualizar(j.getH());
	}
	
	public static List<Heroi> listarJogos(){
		HeroiDAO heroiDao = fabrica.criarHeroiDAO();
		return heroiDao.listar();
	}
	
	public static void carregarJogo(int id){
		JogoDAO jogoDao = fabrica.criarJogoDAO();
		new MundoShinobi(jogoDao.buscarJogo(id));
	}

}
