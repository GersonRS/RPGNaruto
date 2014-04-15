package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import modelo.Fachada;
import modelo.vo.Heroi;
import controle.TratarMouseTelaOpcao;

@SuppressWarnings("serial")
public class Opcoes extends JFrame{

	private int x=800, y=600,a=1;
	private JLabel fundo;
	private List<Botao> botoes;
	private Image img;
	private JButton voltar;
	private ImageIcon imgIcon;

	public Opcoes() {
		super("Opções");
		botoes = new ArrayList<Botao>();
		
		setSize(x, y);
		setLayout(null);

		imgIcon = new ImageIcon(img = carrega("Opcoes.png"));
		img = imgIcon.getImage().getScaledInstance(x, y, Image.SCALE_DEFAULT);
		imgIcon = new ImageIcon(img);
		fundo = new JLabel(imgIcon);
		fundo.setBounds(0, 0, getWidth(), getHeight());

		voltar = new JButton("Voltar");
		voltar.setBounds(720,0,80,20);
		
		TratarMouseTelaOpcao tmto = new TratarMouseTelaOpcao(this);
		
		voltar.addActionListener(tmto);
		
		fundo.add(voltar);
		
		for (Heroi h : Fachada.listarJogos()) {
			Botao b = new Botao("Nome: "+h.getNome());
			b.setId(h.getId());
			b.setBounds(x/2-(x/4)/2, -50+y/2+40*a++, x/4, y/15);
			b.addActionListener(tmto);
			fundo.add(b);
			botoes.add(b);
		}
		
		add(fundo);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);
	}

	private BufferedImage carrega(String img) {
		try {
			BufferedImage imagem;
			imagem = ImageIO.read(getClass().getClassLoader().getResource(img));
			return imagem;
		} catch (Exception e) {
			System.out.println("erro ao carregar " + img);
		}
		return null;
	}

	public List<Botao> getBotoes() {
		return botoes;
	}

	public JButton getVoltar() {
		return voltar;
	}
	
}
