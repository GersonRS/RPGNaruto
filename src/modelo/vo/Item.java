package modelo.vo;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Item {

	protected int id;
	protected int posicaoX, posicaoY;
	protected String nome;
	protected BufferedImage imagem;
	protected ImageIcon img;
	protected boolean visivel;
	protected final int tamanhoX = 32, tamanhoY = 32;
	protected int str, agi, def, jut, hp, ch;

	public Item() {
		carregarImagem("Kunai.png");
	}

	public Item(int posicaoX, int posicaoY,String nome) {
		this.visivel = true;
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
		this.nome = nome;
		carregarImagem(nome);
	}
	public void carregarImagem(String s){
		try {
			imagem = ImageIO
					.read(getClass().getClassLoader().getResource(s));
			img = new ImageIcon(getClass().getClassLoader().getResource(s));
		} catch (IOException e) {
			System.out
					.println("Erro ao buscar imagem do Item.\nEncerrando aplicação");
			System.exit(0);
		}
	}
	public Rectangle getAreaColisao() {
		return new Rectangle(posicaoX, posicaoY, tamanhoX, tamanhoY);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BufferedImage getImagem() {
		return imagem;
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

	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}

	public int getStr() {
		return str;
	}

	public int getAgi() {
		return agi;
	}

	public int getDef() {
		return def;
	}

	public int getJut() {
		return jut;
	}

	public int getHp() {
		return hp;
	}

	public int getCh() {
		return ch;
	}

	public int getTamanhoX() {
		return tamanhoX;
	}

	public int getTamanhoY() {
		return tamanhoY;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public void setAgi(int agi) {
		this.agi = agi;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public void setJut(int jut) {
		this.jut = jut;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setCh(int ch) {
		this.ch = ch;
	}

	public ImageIcon getImg() {
		return img;
	}

}
