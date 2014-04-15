package modelo.dao;

public abstract class FabricaDeDAOs {
	
	public abstract HeroiDAO criarHeroiDAO();
	public abstract MonstroDAO criarMonstroDAO();
	public abstract ItemDAO criarItemDAO();
	public abstract ControleDAO criarControleDAO();
	public abstract JogoDAO criarJogoDAO();

}
