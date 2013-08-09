package br.com.praia.jampaxadrez.model;

import br.com.praia.jampaxadrez.R;

/*
 * Created on 08/04/2005
 */

/**
 * @author Andreza
 */
public class Cavalo extends Peca {

	/**
	 * Construtor da classe Cavalo
	 * 
	 * @param coordenada
	 *            representa a coordenada que informa onde o cavalo est� no
	 *            momento, para comparar com as coordenadas das posicoes do
	 *            tabuleiro e assim dizer se o cavalo � do jogador 1 ou 2
	 */
	public Cavalo(String coordenada) {
		this.setPosicao(coordenada);
		if (coordenada.equals("a2") || coordenada.equals("a7"))
			this.setJogador(1);
		else if (coordenada.equals("h2") || coordenada.equals("h7"))
			this.setJogador(2);
		this.setStatus(true);
	}

	/**
	 * M�todo que verifica se uma determinada jodada � v�lida ou n�o
	 * 
	 * @param posfX
	 *            � a coordenada do eixo x do tabuleiro para onde o cavalo se
	 *            mover�
	 * @param posfY
	 *            � a coordenada do eixo y do tabuleiro para onde o cavalo se
	 *            mover�
	 * @return true se for efetivada uma jogada v�lida e retorna false se a
	 *         jogada for inv�lida
	 */
	public boolean jogadaValida(int posfX, char posfY) {
		String coordenada = this.getPosicao();
		int posiX = Tabuleiro.separaCoordenadaX(coordenada);
		char posiY = Tabuleiro.separaCoordenadaY(coordenada);

		if (posiY + 2 == posfY && posiX + 1 == posfX || posiY + 2 == posfY
				&& posiX - 1 == posfX || posiX - 2 == posfX
				&& posiY - 1 == posfY || posiX - 2 == posfX
				&& posiY + 1 == posfY || posiX + 2 == posfX
				&& posiY - 1 == posfY || posiX + 2 == posfX
				&& posiY + 1 == posfY || posiY - 2 == posfY
				&& posiX - 1 == posfX || posiY - 2 == posfY
				&& posiX + 1 == posfX)
			return true;
		return false;
	}

	/**
	 * M�todo que verifica se existe alguma pe�a entre a coordenada inicial e a
	 * final do cavalo. 
	 * 
	 * @param x
	 *            � a coordenada do eixo x do tabuleiro para onde o cavalo se
	 *            mover�
	 * @param y
	 *            � a coordenada do eixo y do tabuleiro para onde o cavalo se
	 *            mover�
	 * 
	 * @return retorna sempre true pois o cavalo pode saltar sobre as pe�as
	 * n�o fazendo assim diferen�a se existir n�o uma pe�a no caminho
	 */
	public boolean pecaIntermediaria(int x, char y) {
		return true;
	}

	/**
	 * M�todo que verifica se a posi��o de destino � v�lida ou n�o
	 * 
	 * @param coordenada
	 *            representa a coordenada para onde o cavalo se mover�
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
	 * @return um string com a formata��o de um cavalo, ou seja, a representa��o
	 *         gr�fica de um cavalo
	 */
	public String toString() {
		String cavalo;

		if (this.getJogador() == 1)
			cavalo = "C";
		else
			cavalo = "c";
		return cavalo;
	}

	@Override
	public int getImagem() {
		int cavalo;

		if (this.getJogador() == 1)
			cavalo = R.drawable.cb;
		else
			cavalo = R.drawable.cp;
		return cavalo;
	}

}