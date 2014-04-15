package modelo.vo;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.imageio.ImageIO;

import modelo.Fachada;
import modelo.Logica;
import modelo.dao.ControleDAO;

public class Heroi extends Observable {

	private int id=0;
	private Time tempoDeJogo;
	private String nome;
	private int exp, expMax = 150;
	private int posicaoX, posicaoY;
	private BufferedImage imagem,sombra;
	private int direcao = 2;
	private int velocidade;
	private int nivel = 1;
//	private boolean carregado=false;
//	private ArrayList<Item> equip = new ArrayList<Item>(9);
//	private ArrayList<Item> power = new ArrayList<Item>(8);
//	private ArrayList<Object> slot = new ArrayList<Object>(4);

	private int str, strMax = 5, agi, agiMax = 5, def, defMax = 5, jut,
			jutMax = 5, hp, hpMax = 5, ch, chMax = 5;

	private Controle controle = null;
	private int idControle=1;

	private int life = 100;
	private int lifeMax = 100;
	private int chaka = 100;
	private int chakaMax = 100;

	public int tamanhoX, tamanhoY;
	public long anim = 1;

	private List<BolaDeFogo> jutsuBolaDeFogo = new ArrayList<BolaDeFogo>();
	private List<Shuriken> shuriken = new ArrayList<Shuriken>();
	private List<Item> inventario;

	public Heroi() {
		this.inventario = new ArrayList<Item>(20);
		carregarImagens("Narutoo.png");
	}

	public Heroi(String nome,String img, int posicaoX, int posicaoY, int tamanhoX,
			int tamanhoY, int velocidade) {
		this.inventario = new ArrayList<Item>(20);
		this.nome = nome;
		this.exp = 0;
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
		this.tamanhoX = tamanhoX;
		this.tamanhoY = tamanhoY;
		this.velocidade = velocidade;
		this.str = 1;
		this.agi = 1;
		this.def = 1;
		this.jut = 1;
		this.hp = 1;
		this.ch = 1;
		carregarImagens(img);
	}
	
	public void carregarImagens(String img){
		try {
			imagem = ImageIO.read(getClass().getClassLoader().getResource(img));
			sombra = ImageIO.read(getClass().getClassLoader().getResource(
					"Sombra.png"));
		} catch (IOException e) {
			System.out
			.println("Erro ao buscar imagem do Heroi.\nEncerrando aplicação");
			System.exit(0);
		}
		
	}

	public void andar(int novaDirecao) {
		direcao = novaDirecao;
		anim++;
		switch (novaDirecao) {
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

	public void atira() {
		this.shuriken.add(new Shuriken("shuriken.png", posicaoX, posicaoY + 10,
				direcao, velocidade + 25));
	}

	public void atiraFogo() {
		if (chaka > 0) {
			this.jutsuBolaDeFogo.add(new BolaDeFogo("bola de fogo.png",
					posicaoX - 27, posicaoY, direcao, velocidade + 25));
			chaka -= 20;
			setChanged();
			notifyObservers();
		}
	}

	public int getAtaque() {
		return str + jut + ch;
	}

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

	@Override
	public Heroi clone() {
		Heroi heroi = new Heroi();
		heroi.id = this.id;
		heroi.tempoDeJogo = this.tempoDeJogo;
		heroi.nome = this.nome;
		heroi.life = this.life;
		heroi.lifeMax = this.lifeMax;
		heroi.chaka = this.chaka;
		heroi.chakaMax = this.chakaMax;
		heroi.exp = this.exp;
		heroi.expMax = this.expMax;
		heroi.posicaoX = this.posicaoX;
		heroi.posicaoY = this.posicaoY;
		heroi.tamanhoX = this.tamanhoX;
		heroi.tamanhoY = this.tamanhoY;
		heroi.velocidade = this.velocidade;
		heroi.str = this.str;
		heroi.agi = this.agi;
		heroi.def = this.def;
		heroi.jut = this.jut;
		heroi.hp = this.hp;
		heroi.ch = this.ch;
		heroi.nivel = this.nivel;
		heroi.anim = this.anim;
		heroi.strMax = this.strMax;
		heroi.agiMax = this.agiMax;
		heroi.defMax = this.defMax;
		heroi.jutMax = this.jutMax;
		heroi.hpMax = this.hpMax;
		heroi.chMax = this.chMax;
		heroi.controle = this.controle;
		heroi.shuriken = this.shuriken;
		heroi.jutsuBolaDeFogo = this.jutsuBolaDeFogo;
		heroi.inventario = this.inventario;
//		heroi.equip = this.equip;
//		heroi.power = this.power;
//		heroi.slot = this.slot;
		heroi.imagem = this.imagem;
		heroi.sombra = this.sombra;
		return heroi;
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
		setChanged();
		notifyObservers();
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
		setChanged();
		notifyObservers();
	}

	public Controle getControle() {
		if (controle==null)
        {
            ControleDAO controleDao = Fachada.fabrica.criarControleDAO();
            controle=controleDao.getControlePorChave(idControle);
        }
		return controle;
	}

	public void setControle(Controle controle) {
		this.controle = controle;
	}

	public List<Shuriken> getShuriken() {
		return shuriken;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
		setChanged();
		notifyObservers();
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
		setChanged();
		notifyObservers();
	}

	public void addItem(Item item) {
		this.inventario.add(item);
		setChanged();
		notifyObservers();
	}
	
	public List<Item> getInventario() {
//		if(!carregado){
//			ItemDAO itemDao = Fachada.fabrica.criarItemDAO();
//			inventario = itemDao.buscarInventario(id);
//			carregado=true;
//		}
		return inventario;
	}

	public void setInventario(List<Item> inventario) {
		this.inventario = inventario;
		setChanged();
		notifyObservers();
	}

	public int getLifeMax() {
		return lifeMax;
	}

	public void setLifeMax(int lifeMax) {
		this.lifeMax = lifeMax;
		setChanged();
		notifyObservers();
	}

	public int getChaka() {
		return chaka;
	}

	public void setChaka(int chaka) {
		this.chaka = chaka;
		setChanged();
		notifyObservers();
	}

	public int getChakaMax() {
		return chakaMax;
	}

	public void setChakaMax(int chakaMax) {
		this.chakaMax = chakaMax;
		setChanged();
		notifyObservers();
	}

	public int getExpMax() {
		return expMax;
	}

	public void setExpMax(int expMax) {
		this.expMax = expMax;
		setChanged();
		notifyObservers();
	}

	public int getStr() {
		return str;
	}

	public void setStr(int str) {
		this.str = str;
		setChanged();
		notifyObservers();
	}

	public int getAgi() {
		return agi;
	}

	public void setAgi(int agi) {
		this.agi = agi;
		setChanged();
		notifyObservers();
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
		setChanged();
		notifyObservers();
	}

	public int getJut() {
		return jut;
	}

	public void setJut(int jut) {
		this.jut = jut;
		setChanged();
		notifyObservers();
	}

	public int getStrMax() {
		return strMax;
	}

	public void setStrMax(int strMax) {
		this.strMax = strMax;
		setChanged();
		notifyObservers();
	}

	public int getAgiMax() {
		return agiMax;
	}

	public void setAgiMax(int agiMax) {
		this.agiMax = agiMax;
		setChanged();
		notifyObservers();
	}

	public int getDefMax() {
		return defMax;
	}

	public void setDefMax(int defMax) {
		this.defMax = defMax;
		setChanged();
		notifyObservers();
	}

	public int getJutMax() {
		return jutMax;
	}

	public void setJutMax(int jutMax) {
		this.jutMax = jutMax;
		setChanged();
		notifyObservers();
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
		setChanged();
		notifyObservers();
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
		setChanged();
		notifyObservers();
	}

	public int getCh() {
		return ch;
	}

	public void setCh(int ch) {
		this.ch = ch;
		setChanged();
		notifyObservers();
	}

	public int getChMax() {
		return chMax;
	}

	public void setChMax(int chMax) {
		this.chMax = chMax;
		setChanged();
		notifyObservers();
	}

//	public ArrayList<Item> getEquip() {
//		return equip;
//	}
//
//	public void setEquip(ArrayList<Item> equip) {
//		this.equip = equip;
//		setChanged();
//		notifyObservers();
//	}
//
//	public ArrayList<Item> getPower() {
//		return power;
//	}
//
//	public void setPower(ArrayList<Item> power) {
//		this.power = power;
//		setChanged();
//		notifyObservers();
//	}
//
//	public ArrayList<Object> getSlot() {
//		return slot;
//	}
//
//	public void setSlot(ArrayList<Object> slot) {
//		this.slot = slot;
//		setChanged();
//		notifyObservers();
//	}

	public List<BolaDeFogo> getJutsuBolaDeFogo() {
		return jutsuBolaDeFogo;
	}
	
	public BufferedImage getSombra() {
		return sombra;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setImagem(BufferedImage imagem) {
		this.imagem = imagem;
	}

	public void setSombra(BufferedImage sombra) {
		this.sombra = sombra;
	}

	public void setJutsuBolaDeFogo(List<BolaDeFogo> jutsuBolaDeFogo) {
		this.jutsuBolaDeFogo = jutsuBolaDeFogo;
	}

	public void setShuriken(List<Shuriken> shuriken) {
		this.shuriken = shuriken;
	}

	public Time getTempoDeJogo() {
		return tempoDeJogo;
	}

	public void setTempoDeJogo(Time tempoDeJogo) {
		this.tempoDeJogo = tempoDeJogo;
	}

	public int getIdControle() {
		return idControle;
	}

	public void setIdControle(int idControle) {
		this.idControle = idControle;
	}
	
}
