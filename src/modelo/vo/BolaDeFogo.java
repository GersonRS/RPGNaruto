package modelo.vo;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import modelo.Logica;


public class BolaDeFogo {


		private int DANO = 10;
		private int posicaoX, posicaoY;
		private BufferedImage imagem;
		private int direcao;
		private int velocidade;
		private boolean isVisivel;

		public final int tamanhoX = 75;
		public final int tamanhoY = 75;
		public long anim = 1;

		public BolaDeFogo(String img, int posicaoX, int posicaoY, int direcao,
				int velocidade) {
			this.posicaoX = posicaoX;
			this.posicaoY = posicaoY;
			this.direcao = direcao;
			this.velocidade = velocidade;
			this.isVisivel = true;
			try {
				imagem = ImageIO.read(getClass().getClassLoader().getResource(
						img));
			} catch (IOException e) {
				System.out
						.println("Erro ao buscar imagem do personagem.\nEncerrando aplicação");
				System.exit(0);
			}
		}

		public void mexer() {
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
		
		public int[] areaColisaoPe() {
			int[] posicoes = new int[4];
			posicoes[0] = posicaoX;
			posicoes[1] = posicaoY;
			posicoes[2] = posicaoX + tamanhoX;
			posicoes[3] = posicaoY + tamanhoY;
			return posicoes;
		}
		
		public Rectangle getAreaColisao(){
			int posX,posY,tamX,tamY;
			posX = getPosicaoX()+35;
			posY = getPosicaoY()+35;
			tamX = this.tamanhoX - 35;
			tamY = this.tamanhoY - 35;
			return new Rectangle(posX,posY,tamX,tamY);
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

		public boolean isVisivel() {
			return isVisivel;
		}

		public void setVisivel(boolean isVisivel) {
			this.isVisivel = isVisivel;
		}

		public int getVelocidade() {
			return velocidade;
		}

		public int getDANO() {
			return this.DANO;
		}
}
