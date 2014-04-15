package modelo.dao;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import modelo.Logica;

public class Mapa {

	private BufferedImage tileSet;
	private BufferedImage imagem;
	public static BufferedImage mapa,mapa2,mapa3;

	private final int tamanhoTileX = 32;
	private final int tamanhoTileY = 32;

	public static int matriz[][] = new int[50][61];
	static int matriz_X[][] = new int[50][61];
	static int matriz_Y[][] = new int[50][61];

	public int[][] carregaMatriz(int[][] matz, String diretorio) {

		ArrayList<String> arqText = new ArrayList<String>();
    	InputStream is = getClass().getResourceAsStream(diretorio);
    	InputStreamReader isr = new InputStreamReader(is);
    	BufferedReader in = new BufferedReader(isr);

		try {

			while (in.ready())
				arqText.add(in.readLine());

			for (int i = 0; i < arqText.size(); i++) {
				String aux = arqText.get(i).replace(",", "");
				arqText.set(i, aux);
			}

			for (int i = 0; i < arqText.size(); i++) {
				int começo = 0;
				int fim = 1;
				int k = 0;
				for (int j = 0; j < arqText.get(i).length(); j += 1) {
					String tile = arqText.get(i).substring(começo, fim);
					matz[i][k] = Integer.parseInt(tile);
					começo += 1;
					fim += 1;
					k++;
				}

			}
		} catch (Exception e) {
			System.out.println("nao carregou");
			System.exit(0);
		}
		return matz;
	}

	public Mapa(String img) {
		try {
			tileSet = ImageIO.read(this.getClass().getClassLoader()
					.getResource(img));
			imagem = ImageIO.read(this.getClass().getClassLoader()
					.getResource("TileSet.png"));
		} catch (IOException e) {
			System.out
					.println("Erro ao buscar imagem do mapa.\nEncerrando aplicação");
			System.exit(0);
		}
	}

	public void montarMapa1Camada() {

		matriz = carregaMatriz(matriz, "Matriz.txt");

		mapa = new BufferedImage(Logica.LARGURA_PAINEL, Logica.ALTURA_PAINEL,
				BufferedImage.TYPE_4BYTE_ABGR);

		int posX = 0;
		int posY = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				mapa.getGraphics().drawImage(tileSet, posX, posY,
						posX + tamanhoTileX, posY + tamanhoTileY,
						(matriz[i][j] % 4) * 32, 0,
						(matriz[i][j] % 4) * 32 + 32, 32, null);
				posY += 32;
			}
			posX += 32;
			posY = 0;
		}
	}

	public void montarMapa2Camada() {

		matriz_X = carregaMatriz(matriz_X, "Matriz_X.txt");
		matriz_Y = carregaMatriz(matriz_Y, "Matriz_Y.txt");

		mapa2 = new BufferedImage(Logica.LARGURA_PAINEL, Logica.ALTURA_PAINEL,
				BufferedImage.TYPE_4BYTE_ABGR);

		int posX = 0;
		int posY = 0;
		for (int i = 0; i < matriz_X.length; i++) {
			for (int j = 0; j < matriz_Y[0].length; j++) {
				mapa2.getGraphics().drawImage(imagem, posX, posY,
						posX + tamanhoTileX, posY + tamanhoTileY,
						(matriz_X[i][j] % 8) * 32, (matriz_Y[i][j] % 7) * 32,
						(matriz_X[i][j] % 8) * 32 + 32,
						(matriz_Y[i][j] % 7) * 32 + 32, null);
				posY += 32;
			}
			posX += 32;
			posY = 0;
		}

	}
	public void montarMapa3Camada() {
		
		matriz_X = carregaMatriz(matriz_X, "Matriz_X3.txt");
		matriz_Y = carregaMatriz(matriz_Y, "Matriz_Y3.txt");
		
		mapa3 = new BufferedImage(Logica.LARGURA_PAINEL, Logica.ALTURA_PAINEL,
				BufferedImage.TYPE_4BYTE_ABGR);
		
		int posX = 0;
		int posY = 0;
		for (int i = 0; i < matriz_X.length; i++) {
			for (int j = 0; j < matriz_Y[0].length; j++) {
				mapa3.getGraphics().drawImage(imagem, posX, posY,
						posX + tamanhoTileX, posY + tamanhoTileY,
						(matriz_X[i][j] % 8) * 32, (matriz_Y[i][j] % 7) * 32,
						(matriz_X[i][j] % 8) * 32 + 32,
						(matriz_Y[i][j] % 7) * 32 + 32, null);
				posY += 32;
			}
			posX += 32;
			posY = 0;
		}
		
	}

}
