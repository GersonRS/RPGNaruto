package view;

import javax.swing.JButton;

public class Botao extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private int id;
	
	public Botao() {
		super();
	}
	
	public Botao(String text){
		super(text);
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
