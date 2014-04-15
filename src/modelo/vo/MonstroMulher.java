package modelo.vo;

public class MonstroMulher extends Monstro{

	
	
	public MonstroMulher() {
		super();
		carregarImagens("MonstroMulher.png");
		this.tipo = 2;
	}

	public MonstroMulher(String img, int posicaoX, int posicaoY, int tamanhoX,
			int tamanhoY, int velocidade, int numeroDeQuadros) {
		super(img, posicaoX, posicaoY, tamanhoX, tamanhoY, velocidade, numeroDeQuadros);
		this.tipo = 2;
	}

	@Override
	public void atacar() {
		atira();
	}
	
	public void atira() {
		this.shuriken.add(new Shuriken("Shuriken.png", posicaoX, posicaoY,
				direcao, velocidade + 25));
	}
}
