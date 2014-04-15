package modelo.dao;


public class FabricaDeDAOsMySql extends FabricaDeDAOs {

	
	@Override
	public HeroiDAO criarHeroiDAO() {
		return new HeroiDAOMySql();
	}

	@Override
	public MonstroDAO criarMonstroDAO() {
		return new MonstroDAOMySql();
	}

	@Override
	public ItemDAO criarItemDAO() {
		return new ItemDAOMySql();
	}

	@Override
	public ControleDAO criarControleDAO() {
		return new ControleDAOMySql();
	}

	@Override
	public JogoDAO criarJogoDAO() {
		return new JogoDAOMySql();
	}
}
