package view;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Inventario extends JLabel {

	private int str, agi, def, jut, hp, ch;
	private boolean equipado;
	
	public Inventario() {
		super();
		this.equipado = false;
	}

	public int getStr() {
		return str;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public int getAgi() {
		return agi;
	}

	public void setAgi(int agi) {
		this.agi = agi;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getJut() {
		return jut;
	}

	public void setJut(int jut) {
		this.jut = jut;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getCh() {
		return ch;
	}

	public void setCh(int ch) {
		this.ch = ch;
	}

	public boolean isEquipado() {
		return equipado;
	}

	public void setEquipado(boolean equipado) {
		this.equipado = equipado;
	}
	
}
