package modelo.vo;

public class MonstroChefe extends Monstro {
	public MonstroChefe() {
		super();
		carregarImagens("chefe.png");
		this.tipo = 3;
	}

	public MonstroChefe(String img, int posicaoX, int posicaoY, int tamanhoX,
			int tamanhoY, int velocidade, int numeroDeQuadros) {
		super(img, posicaoX, posicaoY, tamanhoX, tamanhoY, velocidade,
				numeroDeQuadros);
		this.tipo = 3;
	}

	@Override
	public void atacar() {
		System.out.println("Poder Especial do Chefe, HAHAHAHAHA");
	}
}
