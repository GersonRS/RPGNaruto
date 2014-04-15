package modelo.vo;

public class Controle {

	private  int cima;
	private  int direita;
	private  int baixo;
	private  int esquerda;
	private  int spaco;
	private  int um, dois, tres, quatro;

	private boolean botaoCima = false;
	private boolean botaoDireita = false;
	private boolean botaoBaixo = false;
	private boolean botaoEsquerda = false;
	private boolean botaoSpaco = false;
	private boolean botaoUm = false;
	private boolean botaoDois = false;
	private boolean botaoTres = false;
	private boolean botaoQuatro = false;

	public Controle() {
	}

	public Controle(int cima, int direita, int baixo, int esquerda, int spaco,
			int um, int dois, int tres, int quatro) {
		this.cima = cima;
		this.direita = direita;
		this.baixo = baixo;
		this.esquerda = esquerda;
		this.spaco = spaco;
		this.um = um;
		this.dois = dois;
		this.tres = tres;
		this.quatro = quatro;
	}

	public int getDireita() {
		return direita;
	}

	public int getCima() {
		return cima;
	}

	public int getBaixo() {
		return baixo;
	}

	public int getEsquerda() {
		return esquerda;
	}

	public int getSpaco() {
		return spaco;
	}

	public int getUm() {
		return um;
	}

	public int getDois() {
		return dois;
	}

	public int getTres() {
		return tres;
	}

	public int getQuatro() {
		return quatro;
	}

	public void setCima(int cima) {
		this.cima = cima;
	}

	public void setDireita(int direita) {
		this.direita = direita;
	}

	public void setBaixo(int baixo) {
		this.baixo = baixo;
	}

	public void setEsquerda(int esquerda) {
		this.esquerda = esquerda;
	}

	public void setSpaco(int spaco) {
		this.spaco = spaco;
	}

	public void setUm(int um) {
		this.um = um;
	}

	public void setDois(int dois) {
		this.dois = dois;
	}

	public void setTres(int tres) {
		this.tres = tres;
	}

	public void setQuatro(int quatro) {
		this.quatro = quatro;
	}

	public boolean isBotaoCima() {
		return botaoCima;
	}

	public void setBotaoCima(boolean botaoCima) {
		this.botaoCima = botaoCima;
	}

	public boolean isBotaoBaixo() {
		return botaoBaixo;
	}

	public void setBotaoBaixo(boolean botaoBaixo) {
		this.botaoBaixo = botaoBaixo;
	}

	public boolean isBotaoDireita() {
		return botaoDireita;
	}

	public void setBotaoDireita(boolean botaoDireita) {
		this.botaoDireita = botaoDireita;
	}

	public boolean isBotaoEsquerda() {
		return botaoEsquerda;
	}

	public void setBotaoEsquerda(boolean botaoEsquerda) {
		this.botaoEsquerda = botaoEsquerda;
	}

	public boolean isBotaoSpaco() {
		return botaoSpaco;
	}

	public void setBotaoSpaco(boolean botaoSpaco) {
		this.botaoSpaco = botaoSpaco;
	}

	public boolean isBotaoUm() {
		return botaoUm;
	}

	public void setBotaoUm(boolean botaoUm) {
		this.botaoUm = botaoUm;
	}

	public boolean isBotaoDois() {
		return botaoDois;
	}

	public void setBotaoDois(boolean botaoDois) {
		this.botaoDois = botaoDois;
	}

	public boolean isBotaoTres() {
		return botaoTres;
	}

	public void setBotaoTres(boolean botaoTres) {
		this.botaoTres = botaoTres;
	}

	public boolean isBotaoQuatro() {
		return botaoQuatro;
	}

	public void setBotaoQuatro(boolean botaoQuatro) {
		this.botaoQuatro = botaoQuatro;
	}

}
