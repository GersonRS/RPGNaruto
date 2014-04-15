package modelo;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.dao.Mapa;
import modelo.vo.BolaDeFogo;
import modelo.vo.Controle;
import modelo.vo.Heroi;
import modelo.vo.Item;
import modelo.vo.Jogo;
import modelo.vo.Monstro;
import modelo.vo.MonstroChefe;
import modelo.vo.Shuriken;
import view.PainelEsquerdo;
import view.Renderizacao;

public class Logica implements Runnable {

	private static long temp = 1;

	public final static int LARGURA = 800;
	public final static int ALTURA = 640;
	public final static int LARGURA_PAINEL = 1600;
	public final static int ALTURA_PAINEL = 1952;
	private static int deslocX = 0;

	private static int deslocY = 0;

	private int chef = 0;

	private Renderizacao gamePanel;
	private PainelEsquerdo painel;
	private static Heroi heroi;

	private static List<Monstro> monstros = new ArrayList<Monstro>();
	static List<Shuriken> shuriken;
	static List<BolaDeFogo> fogo;
	private static List<Item> itens = new ArrayList<Item>();
	
	private Controle controle1;

	private boolean running = true;
	
	private static Jogo jogo; 

	Random ran = new Random();

	public enum Direcao {
		CIMA, DIREITA, BAIXO, ESQUERDA
	}
	
	HeroiCareTaker carataker;

	public Logica(Renderizacao panel, PainelEsquerdo painelEsc,Jogo j) {
		this.gamePanel = panel;
		this.painel = painelEsc;
		heroi = j.getH();
		
		deslocX = j.getDeslocX();
		deslocY = j.getDeslocY();
		
		carataker = new HeroiCareTaker();

		controle1 = j.getH().getControle();
		getHeroi().setControle(controle1);
		shuriken = getHeroi().getShuriken();
		fogo = getHeroi().getJutsuBolaDeFogo();
		heroi.addObserver(painel);

		monstros = j.getM();
		jogo = j;
	}

	@Override
	public void run() {

		long tempAnterior = System.currentTimeMillis();
		Mapa mapa = new Mapa("Tile.png");
		mapa.montarMapa1Camada();
		mapa.montarMapa2Camada();
		mapa.montarMapa3Camada();
		heroi.setNome(heroi.getNome());

		
		while (running) {
			tratarJogo();
			gamePanel.renderizar();
			gamePanel.pintaTela();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}

			setTemp(System.currentTimeMillis() - tempAnterior);
			if (getHeroi().getLife() <= 0) {
				running = false;
				gamePanel.gameOver();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
			tempAnterior = System.currentTimeMillis();
		}
	}

	private void tratarJogo() {

		moverJogador();
		if(!controle1.isBotaoQuatro()){
			moverMonstros();
		mexerShuriken();
		mexerFogo();
		verificaColisaoFogo();
		verificaColisaoShuriken();
		tirarLife();
		tratamentoGeral();
			carataker.adicionarMemento(new HeroiMemento(getHeroi().clone()));
		}
	}

	private void moverJogador() {
		Controle controle = getHeroi().getControle();
		int posXAnterior = getHeroi().getPosicaoX();
		int posYAnterior = getHeroi().getPosicaoY();

		if (controle.isBotaoCima()) {
			getHeroi().andar(Direcao.CIMA.ordinal());
		}
		if (controle.isBotaoBaixo()) {
			getHeroi().andar(Direcao.BAIXO.ordinal());
		}
		if (controle.isBotaoDireita()) {
			getHeroi().andar(Direcao.DIREITA.ordinal());
		}
		if (controle.isBotaoEsquerda()) {
			getHeroi().andar(Direcao.ESQUERDA.ordinal());
		}
		if (controle.isBotaoSpaco()) {
			if (controle.isBotaoUm()) {
				getHeroi().atiraFogo();
			}
			if (controle.isBotaoDois()) {
				getHeroi().atira();
			}
			if(controle.isBotaoTres()){
				
			}
		}
		if(controle.isBotaoQuatro()){
			setHeroi(carataker.getUltimoEstadoSalvo().getHeroi().clone());
		}

		int x = getHeroi().getPosicaoX();
		int y = getHeroi().getPosicaoY();

		int tempdX = posXAnterior - x;
		int tempdY = posYAnterior - y;

		deslocX += tempdX;
		deslocY += tempdY;

		tratarSairTela(getHeroi(), posXAnterior, posYAnterior, tempdX, tempdY);
		tratarColisao(getHeroi(), posXAnterior, posYAnterior, tempdX, tempdY);

		gamePanel.setDeslocX(deslocX);
		gamePanel.setDeslocY(deslocY);
	}

	private void tratarSairTela(Heroi heroi, int posX, int posY, int a, int b) {

		if ((heroi.getPosicaoX() < 0)
				|| ((heroi.getPosicaoX() + heroi.tamanhoX) > LARGURA_PAINEL)) {
			heroi.setPosicaoX(posX);
			deslocX = -a;
			deslocY = -b;
		}
		if ((heroi.getPosicaoY() < 0)
				|| ((heroi.getPosicaoY() + heroi.tamanhoY) > ALTURA_PAINEL)) {
			heroi.setPosicaoY(posY);
			deslocX = -a;
			deslocY = -b;
		}
	}

	private void tratarColisao(Heroi boneco, int posXAnterior,
			int posYAnterior, int a, int b) {
		int[] areaColisao = boneco.areaColisaoPe();
		Rectangle areaHeroi = getHeroi().getAreaColisao();
		for (Monstro monstro : getMonstros()) {
			if (!boneco.equals(monstro)) {
				Rectangle areaMonstro = monstro.getAreaColisao();

				if (areaHeroi.intersects(areaMonstro)) {
					deslocX -= a;
					deslocY -= b;
					boneco.setPosicaoX(posXAnterior);
					boneco.setPosicaoY(posYAnterior);
				}
			}
		}
		if (Mapa.matriz[areaColisao[0] / 32][areaColisao[1] / 32] >= 1
				|| Mapa.matriz[areaColisao[2] / 32][areaColisao[3] / 32] >= 1) {
			deslocX -= a;
			deslocY -= b;
			boneco.setPosicaoX(posXAnterior);
			boneco.setPosicaoY(posYAnterior);
		}
	}

	private void mexerShuriken() {
		for (int i = 0; i < shuriken.size(); i++) {
			Shuriken m = (Shuriken) shuriken.get(i);
			if (m.isVisivel()) {
				m.mexer();
			} else {
				shuriken.remove(i);
				m = null;
			}
		}
	}

	private void mexerFogo() {
		for (int i = 0; i < fogo.size(); i++) {
			BolaDeFogo b = (BolaDeFogo) fogo.get(i);
			if (b.isVisivel()) {
				b.mexer();
			} else {
				fogo.remove(i);
				b = null;
			}
		}
	}

	private void verificaColisaoFogo() {

		for (BolaDeFogo fire : fogo) {

			Rectangle areaShuriken = fire.getAreaColisao();

			for (Monstro monstro : getMonstros()) {
				Rectangle areaMonstro = monstro.getAreaColisao();

				if (areaShuriken.intersects(areaMonstro)) {
					fire.setVisivel(false);
					monstro.setLife(monstro.getLife() - fire.getDANO()
							* getHeroi().getAtaque());
				}
			}
			if ((fire.getPosicaoX() < 0)
					|| ((fire.getPosicaoX() + fire.tamanhoX) > LARGURA_PAINEL)) {
				fire.setVisivel(false);
			}
			if ((fire.getPosicaoY() < 0)
					|| ((fire.getPosicaoY() + fire.tamanhoY) > ALTURA_PAINEL)) {
				fire.setVisivel(false);
			}
		}
	}

	private void verificaColisaoShuriken() {

		for (Shuriken shuri : shuriken) {

			Rectangle areaShuriken = shuri.getAreaColisao();

			for (Monstro monstro : getMonstros()) {
				Rectangle areaMonstro = monstro.getAreaColisao();

				if (areaShuriken.intersects(areaMonstro)) {
					shuri.setVisivel(false);
					monstro.setLife(monstro.getLife() - shuri.getDANO()
							* getHeroi().getAtaque());
				}
			}
			if ((shuri.getPosicaoX() < 0)
					|| ((shuri.getPosicaoX() + shuri.tamanhoX) > LARGURA_PAINEL)) {
				shuri.setVisivel(false);
			}
			if ((shuri.getPosicaoY() < 0)
					|| ((shuri.getPosicaoY() + shuri.tamanhoY) > ALTURA_PAINEL)) {
				shuri.setVisivel(false);
			}
		}
	}

	private void tratamentoGeral() {
		if (getHeroi().getExp() >= getHeroi().getExpMax()) {
			getHeroi().setNivel(getHeroi().getNivel() + 1);
			getHeroi().setExp(0);
			getHeroi().setLifeMax(getHeroi().getLifeMax() + 100);
			getHeroi().setExpMax(getHeroi().getExpMax() + 100);
			if (getHeroi().getStr() <= 5) {
				getHeroi().setStr(getHeroi().getStr() + 1);
			}

		}
		if (getItens().size() != 0) {
			Rectangle areaHeroi = getHeroi().getAreaColisao();
			for (int j = 0; j < getItens().size(); j++) {
				Item ite = getItens().get(j);
				Rectangle areaItem = ite.getAreaColisao();
				if (getHeroi().getInventario().size() < 19) {
					if (areaItem.intersects(areaHeroi)) {
						getHeroi().addItem(ite);
						getItens().remove(j);
					}
				}
			}
		}

		if (getMonstros().size() == 0 && chef == 0) {
			Monstro monstroChefe = new MonstroChefe("chefe.png", 950, 800, 96, 96,
					5, 4);
			monstroChefe.setLife(450);
			monstroChefe.setExp(300);
			monstroChefe.setSombra(null);
			getMonstros().add(monstroChefe);
			chef = 1;
		}
	}

	private void tirarLife() {
		int[] areaColisao = getHeroi().areaColisaoPe();
		if (Mapa.matriz[areaColisao[0] / 32][areaColisao[1] / 32] == 1
				|| Mapa.matriz[areaColisao[2] / 32][areaColisao[3] / 32] == 1) {
			getHeroi().setLife(getHeroi().getLife() - 5);
		}
	}

	private void moverMonstros() {

		for (int i = 0; i < getMonstros().size(); i++) {

			Monstro monstrO = getMonstros().get(i);

			if (monstrO.isVivo()) {

				int difX = getHeroi().getPosicaoX() - monstrO.getPosicaoX();
				int difY = getHeroi().getPosicaoY() - monstrO.getPosicaoY();

				int posXAnterior = monstrO.getPosicaoX();
				int posYAnterior = monstrO.getPosicaoY();

				double distancia = Math.sqrt((difX * difX) + (difY * difY));

				if (distancia < 150) {

					double ang = Math.atan2(difY, difX);
					double cosX = Math.cos(ang);
					double senY = Math.sin(ang);

					if (cosX > 0) {
						monstrO.setDirecao(Direcao.DIREITA.ordinal());
						monstrO.andar();
					}
					if (cosX < 0) {
						monstrO.setDirecao(Direcao.ESQUERDA.ordinal());
						monstrO.andar();
					}
					if (senY > 0) {
						monstrO.setDirecao(Direcao.BAIXO.ordinal());
						monstrO.andar();
					}
					if (senY < 0) {
						monstrO.setDirecao(Direcao.CIMA.ordinal());
						monstrO.andar();
					}
				} else
					monstrO.andar();
				tratarColisaoMonstro(monstrO, posXAnterior, posYAnterior);
				tratarSairTelaMonstro(monstrO, posXAnterior, posXAnterior);
				if (monstrO.getLife() <= 0) {
					monstrO.setVivo(false);
					monstrO.getTime().stop();
					monstrO = null;
				}
			} else {
				getMonstros().remove(i);
				int percent = ran.nextInt(100);
				if (percent < 50)
					getItens().add(monstrO.criaItem());
				getHeroi().setExp(getHeroi().getExp() + monstrO.getExp());
			}
		}
	}

	private void tratarSairTelaMonstro(Monstro monstro, int posX, int posY) {

		if ((monstro.getPosicaoX() < 0)
				|| ((monstro.getPosicaoX() + monstro.tamanhoX) > LARGURA_PAINEL)) {
			monstro.setPosicaoX(posX);
		}
		if ((monstro.getPosicaoY() < 0)
				|| ((monstro.getPosicaoY() + monstro.tamanhoY) > ALTURA_PAINEL)) {
			monstro.setPosicaoY(posY);
		}
	}

	private void tratarColisaoMonstro(Monstro monster, int posXAnterior,
			int posYAnterior) {

		int[] areaColisao = monster.areaColisaoPe();
		Rectangle areaMonster = monster.getAreaColisao();

		if (Mapa.matriz[areaColisao[0] / 32][areaColisao[1] / 32] >= 1
				|| Mapa.matriz[areaColisao[2] / 32][areaColisao[3] / 32] >= 1) {
			monster.setPosicaoX(posXAnterior);
			monster.setPosicaoY(posYAnterior);

		}

		Rectangle areaHeroi = getHeroi().getAreaColisao();

		if (areaMonster.intersects(areaHeroi)) {
			monster.setPosicaoX(posXAnterior);
			monster.setPosicaoY(posYAnterior);
			getHeroi().setLife(getHeroi().getLife() - 5);
		}

		for (Monstro monstro : getMonstros()) {
			if (!monster.equals(monstro)) {
				Rectangle areaMonstro = monstro.getAreaColisao();

				if (areaMonster.intersects(areaMonstro)) {
					monster.setPosicaoX(posXAnterior);
					monster.setPosicaoY(posYAnterior);
				}
			}
		}
	}

	public static List<Monstro> getMonstros() {
		return monstros;
	}

	public static void setMonstros(List<Monstro> monstros) {
		Logica.monstros = monstros;
	}

	public static Heroi getHeroi() {
		return heroi;
	}

	public static void setHeroi(Heroi heroia) {
//		heroi.setId(heroia.getId());
		heroi.setTempoDeJogo(heroia.getTempoDeJogo());
		heroi.setNome(heroia.getNome());
		heroi.setLife(heroia.getLife());
		heroi.setLifeMax(heroia.getLifeMax());
		heroi.setChaka(heroia.getChaka());
		heroi.setChakaMax(heroia.getChakaMax());
		heroi.setExp(heroia.getExp());
		heroi.setExpMax(heroia.getExpMax());
		heroi.setPosicaoX(heroia.getPosicaoX());
		heroi.setPosicaoY(heroia.getPosicaoY());
		heroi.setTamanhoX(heroia.getTamanhoX());
		heroi.setTamanhoY(heroia.getTamanhoY());
		heroi.setVelocidade(heroia.getVelocidade());
		heroi.setStr(heroia.getStr());
		heroi.setAgi(heroia.getAgi());
		heroi.setDef(heroia.getDef());
		heroi.setJut(heroia.getJut());
		heroi.setHp(heroia.getHp());
		heroi.setCh(heroia.getCh());
		heroi.setNivel(heroia.getNivel());
		heroi.anim=heroia.anim;
		heroi.setStrMax(heroia.getStrMax());
		heroi.setAgiMax(heroia.getAgiMax());
		heroi.setDefMax(heroia.getDefMax());
		heroi.setJutMax(heroia.getJutMax());
		heroi.setHpMax(heroia.getHpMax());
		heroi.setChMax(heroia.getChMax());
		heroi.setControle(heroia.getControle());
		heroi.setShuriken(heroia.getShuriken());
		heroi.setJutsuBolaDeFogo(heroia.getJutsuBolaDeFogo());
		heroi.setInventario(heroia.getInventario());
//		heroi.setEquip(heroia.getEquip());
//		heroi.setPower(heroia.getPower());
//		heroi.setSlot(heroia.getSlot());
		heroi.setImagem(heroia.getImagem());
		heroi.setSombra(heroia.getSombra());
	}

	public static List<Item> getItens() {
		return itens;
	}

	public static void setItens(List<Item> itens) {
		Logica.itens = itens;
	}

	public static long getTemp() {
		return temp;
	}

	public static void setTemp(long temp) {
		Logica.temp = temp;
	}

	public static Jogo getJogo() {
		jogo.setH(heroi);
		jogo.setM(monstros);
		jogo.setDeslocX(deslocX);
		jogo.setDeslocY(deslocY);
		return jogo;
	}

}
