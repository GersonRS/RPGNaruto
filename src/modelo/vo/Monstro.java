package modelo.vo;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import modelo.Fachada;
import modelo.Logica;
import modelo.dao.ItemDAO;

public abstract class Monstro implements ActionListener {

	protected int exp,id;
	protected int posicaoX, posicaoY;
	protected BufferedImage imagem, sombra;
	protected int direcao = 2;
	protected int velocidade;
	protected boolean isVivo = true;
	protected Item item;
	protected int Item_id = 1;
	protected int numQuador;
	protected int tipo;

	protected Timer timer;
	protected Random ran;

	protected int life = 150;

	public int tamanhoX, tamanhoY;
	public long anim = 1;

	protected List<Shuriken> shuriken;
	
	public Monstro(){
		ran = new Random();
		timer = new Timer(1000, this);
		timer.start();		
	}
	
	

	public Monstro(String img, int posicaoX, int posicaoY, int tamanhoX,
			int tamanhoY, int velocidade, int numeroDeQuadros) {
		ran = new Random();
		this.exp = ran.nextInt(51) + 50;
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
		this.tamanhoX = tamanhoX;
		this.tamanhoY = tamanhoY;
		this.velocidade = velocidade;
		this.numQuador = numeroDeQuadros;
		this.shuriken = new ArrayList<Shuriken>();
		carregarImagens(img);
		timer = new Timer(1000, this);
		timer.start();
	}
	
	public void carregarImagens(String img){
		try {
			imagem = ImageIO.read(getClass().getClassLoader().getResource(img));
			sombra = ImageIO.read(getClass().getClassLoader().getResource(
					"Sombra.png"));
		} catch (IOException e) {
			System.out
			.println("Erro ao buscar imagem do monstro.\nEncerrando aplicação");
			System.exit(0);
		}
		
	}
	
	public void acao(){
		andar();
		if(ran.nextInt(100)<10){
		atacar();
		}
	}

	public void andar() {
		anim++;
		switch (direcao) {
		case 0:
			posicaoY -= (velocidade * Logica.getTemp()) / 100;
			break;
		case 1:
			posicaoX += (velocidade * Logica.getTemp()) / 100;
			break;
		case 2:
			posicaoY += (velocidade * Logica.getTemp()) / 100;
			break;
		case 3:
			posicaoX -= (velocidade * Logica.getTemp()) / 100;
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		moverMonstro();
	}
	
	public Item getItem() {
		if(item==null){
			ItemDAO itemDao = Fachada.fabrica.criarItemDAO();
			item = itemDao.buscarItem(0);
		}
		return item;
	}

	public Item criaItem() {
		Random ran = new Random();
		int sort = ran.nextInt(9);
		sort = 0;
		switch (sort) {
		case 0: {
			
			break;
		}
		case 1: {
			break;
		}
		case 2: {
			break;
		}
		case 3: {
			break;
		}
		case 4: {
			break;
		}
		case 5: {
			break;
		}
		case 6: {
			break;
		}
		case 7: {
			break;
		}
		case 8: {
			break;
		}
		case 9: {
			break;
		}
		default: {
			break;
		}
		}

		return new Kunai(posicaoX-3,posicaoY+tamanhoY/5);
	}

	protected void moverMonstro() {
		int direcao = ran.nextInt(4);
		this.setDirecao(direcao);
	}
	
	public abstract void atacar();

	public int[] areaColisaoPe() {
		int[] posicoes = new int[4];
		posicoes[0] = posicaoX + 2;
		posicoes[1] = posicaoY + 20;
		posicoes[2] = posicaoX + tamanhoX - 2;
		posicoes[3] = posicaoY + tamanhoY;
		return posicoes;
	}

	public Rectangle getAreaColisao() {
		return new Rectangle(posicaoX + 2, posicaoY + 25, tamanhoX - 2,
				tamanhoY);
	}

	public int getPosicaoX() {
		return posicaoX;
	}

	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}

	public int getPosicaoY() {
		return posicaoY;
	}

	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}

	public BufferedImage getImagem() {
		return imagem;
	}

	public int getDirecao() {
		return direcao;
	}

	public void setDirecao(int direcao) {
		this.direcao = direcao;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public List<Shuriken> getShuriken() {
		return shuriken;
	}

	public boolean isVivo() {
		return isVivo;
	}

	public void setVivo(boolean isVivo) {
		this.isVivo = isVivo;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public Timer getTime() {
		return timer;
	}

	public BufferedImage getSombra() {
		return sombra;
	}

	public int getNumQuador() {
		return numQuador;
	}

	public void setNumQuador(int numQuador) {
		this.numQuador = numQuador;
	}

	public void setSombra(BufferedImage sombra) {
		this.sombra = sombra;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}

	public int getTamanhoX() {
		return tamanhoX;
	}

	public void setTamanhoX(int tamanhoX) {
		this.tamanhoX = tamanhoX;
	}

	public int getTamanhoY() {
		return tamanhoY;
	}

	public void setTamanhoY(int tamanhoY) {
		this.tamanhoY = tamanhoY;
	}

	public int getItem_id() {
		return Item_id;
	}

	public void setItem_id(int item_id) {
		Item_id = item_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
}
