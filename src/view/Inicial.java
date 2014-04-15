package view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import controle.TratarMouseTelaInicial;


@SuppressWarnings("serial")
public class Inicial extends JFrame{

	Dimension dimTela = Toolkit.getDefaultToolkit().getScreenSize();

	public int TAMANHO_TELA_X = 800;
	public int TAMANHO_TELA_Y = 600;

	public JLabel fundo1, fundo2, nuvem1, nuvem2, nuvemGrande1, nuvemGrande2,
			flashPequeno1, flashPequeno2, flashPequeno3, flashGrande;
	public Random random;
	public Timer timer;
	public ImageIcon imageTemp[] = new ImageIcon[10];
	public ImageIcon imageNome[] = new ImageIcon[3];
	public JLabel novoJogo, sair, nome, opcoes;
	public Image img;
	public int a;

	public Inicial() {
		super("RPG - Mundo Shinobi");
		setSize(new Dimension(TAMANHO_TELA_X, TAMANHO_TELA_Y));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setResizable(false);
		setLocation(dimTela.width / 2 - TAMANHO_TELA_X / 2, dimTela.height / 2
				- TAMANHO_TELA_Y / 2);
		setIconImage(new ImageIcon(getClass().getClassLoader().getResource(
				"naruto_icon.gif")).getImage());

		random = new Random();

		for (int i = 0; i < imageNome.length; i++) {
			imageNome[i] = new ImageIcon(getClass().getClassLoader()
					.getResource("Nome" + i + ".png"));

		}
		nome = new JLabel(imageNome[0]);
		nome.setBounds(TAMANHO_TELA_X / 2 - 313, 150, 626, 124);

		imageTemp[0] = new ImageIcon(getClass().getClassLoader().getResource(
				"fundo1.png"));
		Image redimensionadora0 = imageTemp[0].getImage().getScaledInstance(
				TAMANHO_TELA_X, TAMANHO_TELA_Y, Image.SCALE_DEFAULT);
		imageTemp[0] = new ImageIcon(redimensionadora0);

		imageTemp[1] = new ImageIcon(getClass().getClassLoader().getResource(
				"fundo5.png"));
		Image redimensionadora1 = imageTemp[1].getImage().getScaledInstance(
				TAMANHO_TELA_X, TAMANHO_TELA_Y, Image.SCALE_DEFAULT);
		imageTemp[1] = new ImageIcon(redimensionadora1);

		imageTemp[2] = new ImageIcon(getClass().getClassLoader().getResource(
				"nuvem1.png"));
		imageTemp[3] = new ImageIcon(getClass().getClassLoader().getResource(
				"nuvem2.png"));
		imageTemp[4] = new ImageIcon(getClass().getClassLoader().getResource(
				"nuvemGrande1.png"));
		imageTemp[5] = new ImageIcon(getClass().getClassLoader().getResource(
				"nuvemGrande2.png"));
		imageTemp[6] = new ImageIcon(getClass().getClassLoader().getResource(
				"flashPequeno1.png"));
		imageTemp[7] = new ImageIcon(getClass().getClassLoader().getResource(
				"flashPequeno2.png"));
		imageTemp[8] = new ImageIcon(getClass().getClassLoader().getResource(
				"flashPequeno3.png"));
		imageTemp[9] = new ImageIcon(getClass().getClassLoader().getResource(
				"flashGrande.png"));

		fundo1 = new JLabel(imageTemp[0]);
		fundo1.setBounds(0, 0, TAMANHO_TELA_X, TAMANHO_TELA_Y);
		fundo2 = new JLabel(imageTemp[1]);
		fundo2.setBounds(0, 0, TAMANHO_TELA_X, TAMANHO_TELA_Y);
		nuvem1 = new JLabel(imageTemp[2]);
		nuvem2 = new JLabel(imageTemp[3]);
		nuvemGrande1 = new JLabel(imageTemp[4]);
		nuvemGrande2 = new JLabel(imageTemp[5]);
		flashPequeno1 = new JLabel(imageTemp[6]);
		flashPequeno2 = new JLabel(imageTemp[7]);
		flashPequeno3 = new JLabel(imageTemp[8]);
		flashGrande = new JLabel(imageTemp[9]);

		nuvem1.setBounds(-116, random.nextInt(TAMANHO_TELA_Y) - 32, 116, 64);
		nuvem2.setBounds(-116, random.nextInt(TAMANHO_TELA_Y) - 32, 116, 64);
		nuvemGrande1.setBounds(TAMANHO_TELA_X,
				random.nextInt(TAMANHO_TELA_Y) - 58, 211, 116);
		nuvemGrande2.setBounds(TAMANHO_TELA_X,
				random.nextInt(TAMANHO_TELA_Y) - 65, 232, 130);
		flashPequeno1.setBounds(-128, random.nextInt(TAMANHO_TELA_Y) - 30, 128,
				61);
		flashPequeno2.setBounds(-176, random.nextInt(TAMANHO_TELA_Y) - 32, 176,
				64);
		flashPequeno3.setBounds(TAMANHO_TELA_X,
				random.nextInt(TAMANHO_TELA_Y) - 32, 176, 64);
		flashGrande.setBounds(TAMANHO_TELA_X,
				random.nextInt(TAMANHO_TELA_Y) - 106, 432, 212);

		novoJogo = new JLabel(new ImageIcon(getClass().getClassLoader()
				.getResource("Novo Jogo(a).png")));
		novoJogo.setBounds(TAMANHO_TELA_X / 2 - 92, 280, 184, 44);
		opcoes = new JLabel(new ImageIcon(getClass().getClassLoader()
				.getResource("Opcoes(a).png")));
		opcoes.setBounds(TAMANHO_TELA_X / 2 - 92, 327, 184, 44);
		sair = new JLabel(new ImageIcon(getClass().getClassLoader()
				.getResource("Sair(a).png")));
		sair.setBounds(TAMANHO_TELA_X / 2 - 92, 374, 184, 44);

		novoJogo.addMouseListener(new TratarMouseTelaInicial(this));
		sair.addMouseListener(new TratarMouseTelaInicial(this));
		opcoes.addMouseListener(new TratarMouseTelaInicial(this));

		add(fundo1);
		fundo1.add(fundo2);
		fundo2.add(nome);
		fundo2.add(novoJogo);
		fundo2.add(sair);
		fundo2.add(opcoes);
		fundo1.add(nuvem1);
		fundo1.add(nuvem2);
		fundo1.add(nuvemGrande1);
		fundo1.add(nuvemGrande2);
		fundo1.add(flashPequeno1);
		fundo1.add(flashPequeno2);
		fundo1.add(flashPequeno3);
		fundo1.add(flashGrande);

		timer = new Timer(30, new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Random r = new Random();
				if (nuvem1.getX() >= TAMANHO_TELA_X) {
					nuvem1.setLocation(-116, r.nextInt(TAMANHO_TELA_Y) - 32);
				}
				nuvem1.setLocation(nuvem1.getX() + 5, nuvem1.getY());

				if (nuvem2.getX() >= TAMANHO_TELA_X) {
					nuvem2.setLocation(-116, r.nextInt(TAMANHO_TELA_Y) - 32);
				}
				nuvem2.setLocation(nuvem2.getX() + 6, nuvem2.getY());

				if (nuvemGrande1.getX() <= -211) {
					nuvemGrande1.setLocation(TAMANHO_TELA_X,
							r.nextInt(TAMANHO_TELA_Y) - 58);
				}
				nuvemGrande1.setLocation(nuvemGrande1.getX() - 7,
						nuvemGrande1.getY());

				if (nuvemGrande2.getX() <= -232) {
					nuvemGrande2.setLocation(TAMANHO_TELA_X,
							r.nextInt(TAMANHO_TELA_Y) - 65);
				}
				nuvemGrande2.setLocation(nuvemGrande2.getX() - 9,
						nuvemGrande2.getY());

				if (flashPequeno1.getX() >= TAMANHO_TELA_X) {
					flashPequeno1.setLocation(-128,
							random.nextInt(TAMANHO_TELA_Y) - 30);
				}
				flashPequeno1.setLocation(flashPequeno1.getX() + 76,
						flashPequeno1.getY());

				if (flashPequeno2.getX() >= TAMANHO_TELA_X) {
					flashPequeno2.setLocation(-176,
							random.nextInt(TAMANHO_TELA_Y) - 32);
				}
				flashPequeno2.setLocation(flashPequeno2.getX() + 66,
						flashPequeno2.getY());

				if (flashPequeno3.getX() <= -176) {
					flashPequeno3.setLocation(TAMANHO_TELA_X,
							random.nextInt(TAMANHO_TELA_Y) - 32);
				}
				flashPequeno3.setLocation(flashPequeno3.getX() - 56,
						flashPequeno3.getY());

				if (flashGrande.getX() <= -432) {
					flashGrande.setLocation(TAMANHO_TELA_X,
							random.nextInt(TAMANHO_TELA_Y) - 106);
				}
				flashGrande.setLocation(flashGrande.getX() - 46,
						flashGrande.getY());

				if (a == 0) {
					nome.setIcon(imageNome[1]);
					a++;
				} else {
					if (a == 1) {
						nome.setIcon(imageNome[2]);
						a++;
					} else {
						nome.setIcon(imageNome[0]);
						a = 0;
					}
				}

			}
		});
		timer.start();
		setVisible(true);
		
	}
	
	public Image getImg() {
		return img;
	}

	public static void main(String[] args) {
		new Inicial();
	}

}
