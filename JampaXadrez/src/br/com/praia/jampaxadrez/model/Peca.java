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

	/** Jogador representa o time ao qual as pe�as pertencem */
	private int jogador;

	/** Status informa se a pe�a est� no jogo */
	private boolean status;

	/** Posicao representa onde a pe�a se encontra no tabuleiro */
	private String posicao;

	/**
	 * M�todo abstrato que ser� implementado em toda classe que herdar da classe
	 * Peca
	 * 
	 * @param x
	 *            � a coordenada do eixo x do tabuleiro para onde a pe�a se
	 *            mover�
	 * @param y
	 *            � a coordenada do eixo y do tabuleiro para onde a pe�a se
	 *            mover�
	 * @return retorna true se tiver pe�a intermediaria e false se n�o tiver
	 */
	public abstract boolean pecaIntermediaria(int x, char y);

	/**
	 * M�todo abstrato que ser� implementado em toda classe que herdar da classe
	 * Peca
	 * 
	 * @param x
	 *            � a coordenada do eixo x do tabuleiro para onde a pe�a se
	 *            mover�
	 * @param y
	 *            � a coordenada do eixo y do tabuleiro para onde a pe�a se
	 *            mover�
	 * @return true se for efetivada uma jogada v�lida e retorna false se a
	 *         jogada for inv�lida
	 */
	public abstract boolean jogadaValida(int x, char y);

	/**
	 * M�todo abstrato que ser� implementado em toda classe que herdar da classe
	 * Peca
	 * 
	 * @return um string com a formata��o de uma pe�a, ou seja, a representa��o
	 *         gr�fica da pe�a
	 */
	public abstract String toString();

	/**M�todo que faz o movimento da pe�a
	 * @param coordenada
	 *            representa a coordenada para onde a pe�a se mover�
	 * @return true se a pe�a for movida com sucesso e false se a pe�a n�o for
	 *         movida
	 */
	public boolean mova_se(String coordenada) {
		Peca pecaDestino = Tabuleiro.getCasa(coordenada);
		String posAntiga = this.getPosicao();

		int posfX = Tabuleiro.separaCoordenadaX(coordenada);
		char posfY = Tabuleiro.separaCoordenadaY(coordenada);
		if (posDestinoValida(coordenada)) /*
										   * verifica se a posi��o existe no
										   * Tabuleiro e se tem alguma pe�a do
										   * seu time
										   */
			if (this.jogadaValida(posfX, posfY)) {
				/*
				 * verifica se a peca faz este movimento
				 */
				if (pecaDestino != null) /*
										  * verifica se a casa do destino est�
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
	 * M�todo que verifica se a posi��o de destino � v�lida ou n�o
	 * 
	 * @param coordenada
	 *            representa a coordenada para onde a pe�a se mover�
	 * @return true se a posi��o de destino que foi passada pelo par�metro
	 *         coordenada for v�lida, retorna false se for inv�lida
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
	 *            representa a pe�a que ter� seu status marcado como falso, pois
	 *            ela ser� englida
	 * @return a pe�a que foi engolida
	 */
	protected Peca comerPeca(Peca p) {
		p.setStatus(false);
		return p;
	}

	/**
	 * 
	 * @return o status da pe�a, que pode ser true se a pe�a tiver no tabuleiro
	 *         e false se a pe�a tiver sido engolida
	 */
	public boolean getStatus() {
		return this.status;
	}

	/**
	 * M�todo que marca o status da pe�a para true ou false dependendo de sua
	 * presen�a no tabuleiro
	 * 
	 * @param status
	 *            representa o status da pe�a que pode ser true se ela tiver no
	 *            tabuleiro ou false se n�o tiver
	 */
	protected void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return um string que representa a posi��o atual da pe�a no tabuleiro
	 */
	public String getPosicao() {
		return this.posicao;
	}

	/**
	 * M�todo que define a posi��o de uma pe�a no tabuleiro
	 * 
	 * @param posicao
	 *            um string que representa a posi��o atual da pe�a no tabuleiro
	 */
	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	/**
	 * M�todo que informa qual � o jogador no momento
	 * 
	 * @return o jogador, que � representado por um n�mero, jogador 1 ou jogador
	 *         2
	 */
	public int getJogador() {
		return this.jogador;
	}

	/**
	 * M�todo que define o jogador
	 * @param jogador representa o jogador no momento, que pode ser o jogador 1 ou o 2
	 */
	public void setJogador(int jogador) {
		this.jogador = jogador;
	}

	public abstract int getImagem();

}