package controle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import modelo.vo.Jogo;
import view.Inicial;
import view.MundoShinobi;
import view.Opcoes;

public class TratarMouseTelaInicial extends MouseAdapter{

	private Inicial i;
	
	
	
	public TratarMouseTelaInicial(Inicial i) {
		this.i = i;
	}


	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == i.novoJogo) {
			Jogo j = new Jogo();
			j.getH().setNome(JOptionPane.showInputDialog(null,"Digite o nome do Personagem: "));
			i.dispose();
			
			new MundoShinobi(j);
		}
		if (e.getSource() == i.opcoes) {
			i.dispose();
			new Opcoes();
		}
		if (e.getSource() == i.sair) {
			System.exit(0);
		}
	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == i.novoJogo) {
			i.novoJogo.setIcon(new ImageIcon(getClass().getClassLoader()
					.getResource("Novo Jogo(b).png")));
		}
		if (e.getSource() == i.opcoes) {
			i.opcoes.setIcon(new ImageIcon(getClass().getClassLoader()
					.getResource("Opcoes(b).png")));
		}
		if (e.getSource() == i.sair) {
			i.sair.setIcon(new ImageIcon(getClass().getClassLoader().getResource(
					"Sair(b).png")));
		}
	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource() == i.novoJogo) {
			i.novoJogo.setIcon(new ImageIcon(getClass().getClassLoader()
					.getResource("Novo Jogo(a).png")));
		}
		if (e.getSource() == i.opcoes) {
			i.opcoes.setIcon(new ImageIcon(getClass().getClassLoader()
					.getResource("Opcoes(a).png")));
		}
		if (e.getSource() == i.sair) {
			i.sair.setIcon(new ImageIcon(getClass().getClassLoader().getResource(
					"Sair(a).png")));
		}

	}
	
}
