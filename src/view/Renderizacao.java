package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelo.Logica;
import modelo.dao.Mapa;
import modelo.vo.BolaDeFogo;
import modelo.vo.Heroi;
import modelo.vo.Item;
import modelo.vo.Monstro;
import modelo.vo.Shuriken;

@SuppressWarnings("serial")
public class Renderizacao extends JPanel {

	private int deslocX;

	private int deslocY;

	private BufferedImage tela = new BufferedImage(Logica.LARGURA_PAINEL,
			Logica.ALTURA_PAINEL, BufferedImage.TYPE_4BYTE_ABGR);
	
	private BufferedImage gameOver;

	private Graphics2D dbg = (Graphics2D) tela.getGraphics();

	public Renderizacao() {

		setPreferredSize(new Dimension(800, 600));
		setDoubleBuffered(true);
		setFocusable(true);
		requestFocus();
		setIgnoreRepaint(true);
		
		try {
			gameOver = ImageIO.read(getClass().getClassLoader().getResource("GameOver.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "erro ao caregar GemeOver.png");
			e.printStackTrace();
		}

	}

	public void renderizar() {

		dbg.drawImage(Mapa.mapa, 0, 0, null);
		dbg.drawImage(Mapa.mapa2, 0, 0, null);

		Heroi heroi = Logica.getHeroi();
		List<Shuriken> shuriken = heroi.getShuriken();
		List<BolaDeFogo> jutDeFogos = heroi.getJutsuBolaDeFogo();
		List<Item> itens = Logica.getItens();

		for (int i = 0; i < itens.size(); i++) {
			Item it = itens.get(i);
			dbg.drawImage(it.getImagem(), it.getPosicaoX(), it.getPosicaoY(),
					null);
		}

		for (int i = 0; i < shuriken.size(); i++) {
			Shuriken shu = (Shuriken) shuriken.get(i);
			dbg.drawImage(shu.getImagem(), shu.getPosicaoX(),
					shu.getPosicaoY(), shu.getPosicaoX() + shu.tamanhoX,
					shu.getPosicaoY() + shu.tamanhoY, (int) (shu.anim % 4)
							* shu.tamanhoX, shu.getDirecao() * shu.tamanhoY,
					(int) (shu.anim % 4 * shu.tamanhoX) + shu.tamanhoX,
					(shu.getDirecao() * shu.tamanhoY) + shu.tamanhoY, null);
		}
		for (int i = 0; i < jutDeFogos.size(); i++) {
			BolaDeFogo fogo = (BolaDeFogo) jutDeFogos.get(i);
			dbg.drawImage(fogo.getImagem(), fogo.getPosicaoX(),
					fogo.getPosicaoY(), fogo.getPosicaoX() + fogo.tamanhoX,
					fogo.getPosicaoY() + fogo.tamanhoY, (int) (fogo.anim % 4)
					* fogo.tamanhoX, fogo.getDirecao() * fogo.tamanhoY,
					(int) (fogo.anim % 4 * fogo.tamanhoX) + fogo.tamanhoX,
					(fogo.getDirecao() * fogo.tamanhoY) + fogo.tamanhoY, null);
		}

		dbg.drawImage(heroi.getSombra(), heroi.getPosicaoX(),
				heroi.getPosicaoY() + 35, null);
		dbg.drawImage(heroi.getImagem(), heroi.getPosicaoX(),
				heroi.getPosicaoY(), heroi.getPosicaoX() + heroi.tamanhoX,
				heroi.getPosicaoY() + heroi.tamanhoY, (int) (heroi.anim % 6)
						* heroi.tamanhoX, heroi.getDirecao() * heroi.tamanhoY,
				(int) (heroi.anim % 6 * heroi.tamanhoX) + heroi.tamanhoX,
				(heroi.getDirecao() * heroi.tamanhoY) + heroi.tamanhoY, null);

		for (Monstro monstro : Logica.getMonstros()) {

			dbg.setColor(Color.RED);
			dbg.fillRect(monstro.getPosicaoX() + 2, monstro.getPosicaoY() - 15,
					monstro.getLife() >> 3, 5);

			dbg.drawImage(monstro.getSombra(), monstro.getPosicaoX() + 3,
					monstro.getPosicaoY() + 35, null);
			dbg.drawImage(
					monstro.getImagem(),
					monstro.getPosicaoX(),
					monstro.getPosicaoY() - 5,
					monstro.getPosicaoX() + monstro.tamanhoX,
					monstro.getPosicaoY() + monstro.tamanhoY,
					(int) (monstro.anim % monstro.getNumQuador())
							* monstro.tamanhoX,
					monstro.getDirecao() * monstro.tamanhoY,
					(int) (monstro.anim % monstro.getNumQuador() * monstro.tamanhoX)
							+ monstro.tamanhoX,
					(monstro.getDirecao() * monstro.tamanhoY)
							+ monstro.tamanhoY, null);
		}
		dbg.drawImage(Mapa.mapa3, 0, 0, null);
	}

	public void pintaTela() {

		Graphics2D g;

		g = (Graphics2D) this.getGraphics();

		if ((g != null) && (tela != null)) {
			if (getDeslocX() >= 0)
				setDeslocX(0);
			if (getDeslocX() <= -tela.getWidth() + getWidth())
				setDeslocX(-tela.getWidth() + getWidth());
			if (getDeslocY() >= 0)
				setDeslocY(0);
			if (getDeslocY() <= -tela.getHeight() + getHeight())
				setDeslocY(-tela.getHeight() + getHeight());
			g.drawImage(tela, getDeslocX(), getDeslocY(), this);
		}
		g.dispose();
	}

	public void gameOver() {
		Graphics2D g;

		g = (Graphics2D) this.getGraphics();
		
		g.drawImage(gameOver, 0, 0, this);
	}

	public int getDeslocX() {
		return deslocX;
	}

	public void setDeslocX(int deslocX) {
		this.deslocX = deslocX;
	}

	public int getDeslocY() {
		return deslocY;
	}

	public void setDeslocY(int deslocY) {
		this.deslocY = deslocY;
	}
}
