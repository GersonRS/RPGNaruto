package view;

import java.awt.Dimension;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controle.TratarMousePainelBaixo;

import modelo.vo.Heroi;
import modelo.vo.Item;

@SuppressWarnings("serial")
public class PainelEsquerdo extends JPanel implements Observer{

	PainelCima painelCima;
	PainelBaixo painelBaixo;
	
	public PainelEsquerdo() {
		
		setLayout(null);
		
		setPreferredSize(new Dimension(150, 600));
		setVisible(true);
		painelCima = new PainelCima(150, 150);
		painelBaixo = new PainelBaixo();
		
		painelCima.setBounds(0, 0, 150, 150);
		painelBaixo.setBounds(0, 150, 150, 450);
		add(painelCima);
		add(painelBaixo);
		painelBaixo.addMouseListener(new TratarMousePainelBaixo(painelBaixo));
		painelBaixo.addMouseMotionListener(new TratarMousePainelBaixo(painelBaixo));
		

	}

	public void atualiza(Heroi heroi) {
		painelCima.atualiza(heroi);
		painelBaixo.setInventario(heroi.getInventario());		
	}
	public void mostraTudo(){
		painelCima.mostraTudo();
	}
	public List<Item> retorna(){
		return painelBaixo.getInventario();
	}	

	@Override
	public void update(Observable heroi, Object arg1) {
		Heroi hero = (Heroi)heroi;
		painelCima.atualiza(hero);
		painelBaixo.setInventario(hero.getInventario());
		
		painelCima.mostraTudo();
		painelBaixo.addItem();
	}

}
