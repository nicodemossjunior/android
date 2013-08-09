package br.com.praia.jampaxadrez.model;
/*
 * Created on 08/04/2005
 */

/**
 * @author Diego
 * @author Bianca
 * @author Andreza
 */

public abstract class Peca {

	/** Jogador representa o time ao qual as peças pertencem */
	private int jogador;

	/** Status informa se a peça está no jogo */
	private boolean status;

	/** Posicao representa onde a peça se encontra no tabuleiro */
	private String posicao;

	/**
	 * Método abstrato que será implementado em toda classe que herdar da classe
	 * Peca
	 * 
	 * @param x
	 *            é a coordenada do eixo x do tabuleiro para onde a peça se
	 *            moverá
	 * @param y
	 *            é a coordenada do eixo y do tabuleiro para onde a peça se
	 *            moverá
	 * @return retorna true se tiver peça intermediaria e false se não tiver
	 */
	public abstract boolean pecaIntermediaria(int x, char y);

	/**
	 * Método abstrato que será implementado em toda classe que herdar da classe
	 * Peca
	 * 
	 * @param x
	 *            é a coordenada do eixo x do tabuleiro para onde a peça se
	 *            moverá
	 * @param y
	 *            é a coordenada do eixo y do tabuleiro para onde a peça se
	 *            moverá
	 * @return true se for efetivada uma jogada válida e retorna false se a
	 *         jogada for inválida
	 */
	public abstract boolean jogadaValida(int x, char y);

	/**
	 * Método abstrato que será implementado em toda classe que herdar da classe
	 * Peca
	 * 
	 * @return um string com a formatação de uma peça, ou seja, a representação
	 *         gráfica da peça
	 */
	public abstract String toString();

	/**Método que faz o movimento da peça
	 * @param coordenada
	 *            representa a coordenada para onde a peça se moverá
	 * @return true se a peça for movida com sucesso e false se a peça não for
	 *         movida
	 */
	public boolean mova_se(String coordenada) {
		Peca pecaDestino = Tabuleiro.getCasa(coordenada);
		String posAntiga = this.getPosicao();

		int posfX = Tabuleiro.separaCoordenadaX(coordenada);
		char posfY = Tabuleiro.separaCoordenadaY(coordenada);
		if (posDestinoValida(coordenada)) /*
										   * verifica se a posição existe no
										   * Tabuleiro e se tem alguma peça do
										   * seu time
										   */
			if (this.jogadaValida(posfX, posfY)) {
				/*
				 * verifica se a peca faz este movimento
				 */
				if (pecaDestino != null) /*
										  * verifica se a casa do destino está
										  * vazia
										  */
					Tabuleiro.setPecaFora(this.comerPeca(pecaDestino));
				Tabuleiro.setCasa(Tabuleiro.getCasa(posAntiga), coordenada);
				Tabuleiro.setCasa(null, posAntiga);
				return true;
			}
		return false;
	}

	/**
	 * Método que verifica se a posição de destino é válida ou não
	 * 
	 * @param coordenada
	 *            representa a coordenada para onde a peça se moverá
	 * @return true se a posição de destino que foi passada pelo parâmetro
	 *         coordenada for válida, retorna false se for inválida
	 */
	public boolean posDestinoValida(String coordenada) {
		Peca p = Tabuleiro.getCasa(coordenada);

		if (p != null)
			if (this.getJogador() == p.getJogador())
				return false;
		return true;
	}

	/**
	 * 
	 * @param p
	 *            representa a peça que terá seu status marcado como falso, pois
	 *            ela será englida
	 * @return a peça que foi engolida
	 */
	protected Peca comerPeca(Peca p) {
		p.setStatus(false);
		return p;
	}

	/**
	 * 
	 * @return o status da peça, que pode ser true se a peça tiver no tabuleiro
	 *         e false se a peça tiver sido engolida
	 */
	public boolean getStatus() {
		return this.status;
	}

	/**
	 * Método que marca o status da peça para true ou false dependendo de sua
	 * presença no tabuleiro
	 * 
	 * @param status
	 *            representa o status da peça que pode ser true se ela tiver no
	 *            tabuleiro ou false se não tiver
	 */
	protected void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return um string que representa a posição atual da peça no tabuleiro
	 */
	public String getPosicao() {
		return this.posicao;
	}

	/**
	 * Método que define a posição de uma peça no tabuleiro
	 * 
	 * @param posicao
	 *            um string que representa a posição atual da peça no tabuleiro
	 */
	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	/**
	 * Método que informa qual é o jogador no momento
	 * 
	 * @return o jogador, que é representado por um número, jogador 1 ou jogador
	 *         2
	 */
	public int getJogador() {
		return this.jogador;
	}

	/**
	 * Método que define o jogador
	 * @param jogador representa o jogador no momento, que pode ser o jogador 1 ou o 2
	 */
	public void setJogador(int jogador) {
		this.jogador = jogador;
	}

	public abstract int getImagem();

}