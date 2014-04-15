package modelo;

import modelo.vo.Heroi;

public class HeroiMemento {
	
	protected Heroi heroi;

	public HeroiMemento(Heroi heroi) {
		super();
		this.heroi = heroi;
	}

	public Heroi getHeroi() {
		heroi.notifyObservers();
		return heroi;
	}

}
