package modelo;

import java.util.ArrayList;

import modelo.vo.Heroi;

public class HeroiCareTaker {
	
	protected ArrayList<HeroiMemento> estados;
	private Heroi heroi = Logica.getHeroi();
	
	public HeroiCareTaker() {
		estados = new ArrayList<HeroiMemento>();
	}

	public void adicionarMemento(HeroiMemento memento) {
        if(estados.size()>30){
        	heroi = estados.get(0).getHeroi();
        	estados.remove(0);
        }
		estados.add(memento);
    }
	
	public HeroiMemento getUltimoEstadoSalvo() {
        if (estados.size() <= 0) {
            return new HeroiMemento(heroi);
        }
        HeroiMemento heroiSalvo = estados.get(estados.size() - 1);
        estados.remove(estados.size() - 1);
        return heroiSalvo;
    }
}
