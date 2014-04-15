package controle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import modelo.Fachada;
import modelo.Logica;
import modelo.vo.Controle;
import modelo.vo.Jogo;


public class TratarTeclas implements KeyListener {

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		Controle controle = Logica.getHeroi().getControle();
		if (keyCode == controle.getEsquerda()) {
			controle.setBotaoEsquerda(true);
		}
		if (keyCode == controle.getDireita()) {
			controle.setBotaoDireita(true);
		}
		if (keyCode == controle.getCima()) {
			controle.setBotaoCima(true);
		}
		if (keyCode == controle.getBaixo()) {
			controle.setBotaoBaixo(true);
		}
		if (keyCode == controle.getSpaco()){
			controle.setBotaoSpaco(true);
		}
		if (keyCode == controle.getUm()){
			controle.setBotaoUm(true);
			controle.setBotaoDois(false);
			controle.setBotaoTres(false);
			controle.setBotaoQuatro(false);
		}
		if (keyCode == controle.getDois()){
			controle.setBotaoUm(false);
			controle.setBotaoDois(true);
			controle.setBotaoTres(false);
			controle.setBotaoQuatro(false);
		}
		if (keyCode == controle.getTres()){
			controle.setBotaoUm(false);
			controle.setBotaoDois(false);
			controle.setBotaoTres(true);
			controle.setBotaoQuatro(false);
		}
		if (keyCode == controle.getQuatro()){
//			controle.setBotaoUm(false);
//			controle.setBotaoDois(false);
//			controle.setBotaoTres(false);
			controle.setBotaoQuatro(true);
		}
		if (keyCode == 27){
			Jogo j = Logica.getJogo();
			if(j.getQtdSalvo()==0){
				j.setQtdSalvo(j.getQtdSalvo()+1);
				Fachada.criarNovoJogo(j);
			}else if (j.getQtdSalvo()>0){
				j.setQtdSalvo(j.getQtdSalvo()+1);
				Fachada.salvarJogo(j);
			}
		}
		
	}

	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		Controle controle = Logica.getHeroi().getControle();
		if (keyCode == controle.getEsquerda()) {
			controle.setBotaoEsquerda(false);
		}
		if (keyCode == controle.getDireita()) {
			controle.setBotaoDireita(false);
		}
		if (keyCode == controle.getCima()) {
			controle.setBotaoCima(false);
		}
		if (keyCode == controle.getBaixo()) {
			controle.setBotaoBaixo(false);
		}
		if (keyCode == controle.getSpaco()){
			controle.setBotaoSpaco(false);
		}
		if (keyCode == controle.getQuatro()){
			controle.setBotaoQuatro(false);
		}
	}

	public void keyTyped(KeyEvent e) {
	}
}