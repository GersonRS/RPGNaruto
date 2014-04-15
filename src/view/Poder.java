package view;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Poder extends JLabel{

	private int power;
	private String nomePower;
	
	public Poder() {
		super();
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String getNomePower() {
		return nomePower;
	}

	public void setNomePower(String nomePower) {
		this.nomePower = nomePower;
	}

}
