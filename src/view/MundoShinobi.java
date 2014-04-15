package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import modelo.Logica;
import modelo.vo.Jogo;
import controle.TratarTeclas;


@SuppressWarnings("serial")
public class MundoShinobi extends JFrame{

	public MundoShinobi(Jogo j) {
		super("RPG - Mundo Shinobi");
		Renderizacao gp = new Renderizacao();
		PainelEsquerdo painelEsc = new PainelEsquerdo();
		
		Logica logicas = new Logica(gp,painelEsc,j);
		new Thread(logicas).start();

		gp.addKeyListener(new TratarTeclas());

		setIconImage(new ImageIcon(getClass().getClassLoader().getResource(
		"naruto_icon.gif")).getImage());
		
		Dimension dimTela = Toolkit.getDefaultToolkit().getScreenSize();

		getContentPane().add(gp, BorderLayout.CENTER);
		getContentPane().add(painelEsc, BorderLayout.WEST);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setUndecorated(true); //tira as bordas da janela
		setResizable(false);
		setLocation(dimTela.width / 2 - 800 / 2,
				dimTela.height / 2 - 600 / 2);
		setVisible(true);
	}
}
