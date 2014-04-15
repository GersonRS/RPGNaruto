package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import modelo.vo.Heroi;

@SuppressWarnings("serial")
public class PainelCima extends JComponent {

	Heroi heroi;
	
	private BufferedImage hp, ch, exp, hpMax, chMax, str, def, agi, jut,
			imagem;
	private BufferedImage doubleImagem;

	public PainelCima(int x, int y) {

		setPreferredSize(new Dimension(x, y));
		setLayout(null);

		doubleImagem = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
		carregaTudo();

		setVisible(true);
	}

	private void carregaTudo() {

		try {
			imagem = ImageIO.read(getClass().getClassLoader().getResource(
					"tudoCima.png"));
			hp = ImageIO.read(getClass().getClassLoader().getResource(
					"HP-2.png"));
			hpMax = ImageIO.read(getClass().getClassLoader().getResource(
					"bara_hpMax.png"));
			ch = ImageIO.read(getClass().getClassLoader().getResource(
					"MP-2.png"));
			chMax = ImageIO.read(getClass().getClassLoader().getResource(
					"bara_chMax.png"));
			exp = ImageIO.read(getClass().getClassLoader().getResource(
					"Xp-2.png"));
			str = ImageIO.read(getClass().getClassLoader().getResource(
					"bara_str.png"));
			def = ImageIO.read(getClass().getClassLoader().getResource(
					"bara_def.png"));
			agi = ImageIO.read(getClass().getClassLoader().getResource(
					"bara_agi.png"));
			jut = ImageIO.read(getClass().getClassLoader().getResource(
					"bara_jut.png"));
		} catch (IOException e) {
			System.exit(0);
			JOptionPane.showMessageDialog(null, "erro");
			e.printStackTrace();
		}
	}

	public void atualiza(Heroi heroi) {
		this.heroi = heroi;
	}

	public void mostraTudo() {

		Graphics2D g = (Graphics2D) doubleImagem.getGraphics();
		Graphics2D g2d = (Graphics2D) this.getGraphics();

		g.drawImage(imagem, 0, 0, this);
		g.drawImage(hp, 26, 19, (heroi.getLife() * 127) / heroi.getLifeMax(), 11, this);
		g.drawImage(ch, 26, 38, (heroi.getChaka() * 127) / heroi.getChakaMax(), 11, this);
		g.drawImage(exp, 26, 57, (heroi.getExp() * 127) / heroi.getExpMax(), 11,
				this);
		g.drawImage(hpMax, 26, 96, heroi.getHp() * 48 / heroi.getHpMax(), 11, this);
		g.drawImage(chMax, 26, 115, heroi.getCh() * 48 / heroi.getChMax(), 11, this);
		g.drawImage(agi, 26, 135, heroi.getAgi() * 48 / heroi.getAgiMax(), 11, this);
		g.drawImage(str, 101, 96, heroi.getStr() * 48 / heroi.getStrMax(), 11, this);
		g.drawImage(def, 101, 115, heroi.getDef() * 48 / heroi.getDefMax(), 11, this);
		g.drawImage(jut, 101, 135, heroi.getJut() * 48 / heroi.getJutMax(), 11, this);
		g.setColor(Color.WHITE);
		g.drawString(heroi.getNome(), 40, 13);
		g.drawString("" + heroi.getNivel(), 125, 13);
		g.drawString("" + heroi.getLife() + " / " + heroi.getLifeMax(), 66, 30);
		g.drawString("" + heroi.getChaka() + " / " + heroi.getChakaMax(), 66, 49);
		g.drawString("" + heroi.getExp() + " / " + heroi.getExpMax(), 69, 66);

		g2d.drawImage(doubleImagem, 0, 0,null);
		
	}
}
