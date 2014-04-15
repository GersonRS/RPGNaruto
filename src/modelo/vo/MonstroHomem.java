package modelo.vo;

public class MonstroHomem extends Monstro {

	public MonstroHomem() {
		super();
		carregarImagens("Monstro.png");
		this.tipo = 1;
	}

	public MonstroHomem(String img, int posicaoX, int posicaoY, int tamanhoX,
			int tamanhoY, int velocidade, int numeroDeQuadros) {
		super(img, posicaoX, posicaoY, tamanhoX, tamanhoY, velocidade,
				numeroDeQuadros);
		this.tipo = 1;
	}

	@Override
	public void atacar() {
		System.out.println("dano em area do MonstroHomem");
	}
}
