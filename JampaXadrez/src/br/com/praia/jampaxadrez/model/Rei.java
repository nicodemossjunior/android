package br.com.praia.jampaxadrez.model;

import br.com.praia.jampaxadrez.R;

/*
 * Created on 10/04/2005
 */

/**
 * @author Diego
 */
public class Rei extends Peca {
	/**
	 * posicaoRei informa a posicao atual dos Reis em qualquer momento
	 * inicialmente este array recebe a posi��o onde os reis s�o criado 
	 */
	private static String posicaoRei[] = {"a5","h5"};
		
	/**
		 * Construtor da classe Rei
		 * 
		 * @param coordenada
		 *            representa a coordenada que informa onde o peao est� no
		 *            momento, para comparar com as coordenadas das posicoes do
		 *            tabuleiro e assim dizer se o peao � do jogador 1 ou 2
		 */		
	public Rei(String coordenada) {
		this.setPosicao(coordenada);
		if (coordenada == "a5") 
			this.setJogador(1);
		else 
			this.setJogador(2);
		setStatus(true);
	}

	/**
	 * M�todo que verifica se existe alguma pe�a entre a coordenada inicial e a
	 * final do rei. 
	 * 
	 * @param x
	 *            � a coordenada do eixo x do tabuleiro para onde o cavalo se
	 *            mover�
	 * @param y
	 *            � a coordenada do eixo y do tabuleiro para onde o cavalo se
	 *            mover�
	 * 
	 * @return retorna sempre true pois o rei pode somente ander uma unica
	 * casa n�o havendo assim uma pe�a no caminho
	 */
	public boolean pecaIntermediaria(int x, char y) {
		return true;
	}

	/**
		 * M�todo que verifica se uma determinada jodada � v�lida ou n�o
		 * 
		 * @param posfX
		 *            � a coordenada do eixo x do tabuleiro para onde o peao se
		 *            mover�
		 * @param posfY
		 *            � a coordenada do eixo y do tabuleiro para onde o peao se
		 *            mover�
		 * @return true se for efetivada uma jogada v�lida e retorna false se a
		 *         jogada for inv�lida
		 */
	public boolean jogadaValida(int x, char y) {
		int posiX = Tabuleiro.separaCoordenadaX(this.getPosicao());
		char posiY = Tabuleiro.separaCoordenadaY(this.getPosicao());

		if ((Math.abs(posiX - x) == 1)
			&& (Math.abs(posiY - y) == 1)
			|| (posiX == x && Math.abs(posiY - y) == 1)
			|| (posiY == y && Math.abs(posiX - x) == 1))
			return true;
		else
			return false;
	}

	/**
	 * @param jogador cooresponde ao numero do jogador se jogador 1 ou jogador 2
	 * @return Returns a posicaoRei do jogador em quest�o.
	 */
	public static String getPosicaoRei(int jogador) {
		return Rei.posicaoRei[--jogador];
	}

	/**
	 * @param peca a qual ser� setada a posi��o do rei.
	 */
	public static void setPosicaoRei(Peca peca) {
		String posicaoRei = peca.getPosicao();
		if (peca.getJogador() == 1)
			Rei.posicaoRei[0] = posicaoRei;
		else
			Rei.posicaoRei[1] = posicaoRei;
	}

	/**
		 * @return um string com a formata��o de um Rei, ou seja, a representa��o
		 *         gr�fica de um Rei
		 */
	public String toString() {
		String rei;

		if (this.getJogador() == 1)
			rei = "R";
		else
			rei = "r";
		return rei;
	}

	@Override
	public int getImagem() {
		int rei;

		if (this.getJogador() == 1)
			rei = R.drawable.kb;
		else
			rei = R.drawable.kb;
		return rei;
	}
}