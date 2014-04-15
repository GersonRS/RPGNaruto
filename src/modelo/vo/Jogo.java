package modelo.vo;

import java.util.ArrayList;
import java.util.List;

public class Jogo {

	private int id, qtdSalvo;
	private Heroi h;
	private List<Monstro> m;
	private int deslocX = 0,deslocY = 0;

	public Jogo() {
		this.h = new Heroi("", "Narutoo.png", 325, 300, 23, 55, 10);
		this.h.setControle(new Controle(38, 39, 40, 37, 32, 49, 50, 51, 52));
		this.m = new ArrayList<Monstro>();
		this.qtdSalvo = 0;
		Monstro monstro1 = new MonstroHomem("Monstro.png", 800, 500, 27, 57, 5, 4);
		Monstro monstro2 = new MonstroMulher("MonstroMulher.png", 1024, 768, 32, 61, 3, 4);
		Monstro monstro3 = new MonstroHomem("Monstro.png", 600, 800, 27, 57, 4, 4);
		Monstro monstro4 = new MonstroMulher("MonstroMulher.png", 687, 952, 32, 61, 3, 4);
		Monstro monstro5 = new MonstroHomem("Monstro.png", 905, 1400, 27, 57, 6, 4);
		Monstro monstro6 = new MonstroMulher("MonstroMulher.png", 1500, 800, 32, 61, 2, 4);
//		Monstro monstro5 = new Monstro("MonstroMulher.png", 905, 1400, 32, 61, 6, 6);
		m.add(monstro1);
		m.add(monstro2);
		m.add(monstro3);
		m.add(monstro4);
		m.add(monstro5);
		m.add(monstro6);
	}

	public Heroi getH() {
		return h;
	}

	public void setH(Heroi h) {
		this.h = h;
	}

	public List<Monstro> getM() {
		return m;
	}

	public void setM(List<Monstro> m) {
		this.m = m;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQtdSalvo() {
		return qtdSalvo;
	}

	public void setQtdSalvo(int qtdSalvo) {
		this.qtdSalvo = qtdSalvo;
	}

	public int getDeslocX() {
		return deslocX;
	}

	public void setDeslocX(int deslocX) {
		this.deslocX = deslocX;
	}

	public int getDeslocY() {
		return deslocY;
	}

	public void setDeslocY(int deslocY) {
		this.deslocY = deslocY;
	}
	
}