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
	 *            representa a coordenada que informa onde o cavalo está no
	 *            momento, para comparar com as coordenadas das posicoes do
	 *            tabuleiro e assim dizer se o cavalo é do jogador 1 ou 2
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
	 * Método que verifica se uma determinada jodada é válida ou não
	 * 
	 * @param posfX
	 *            é a coordenada do eixo x do tabuleiro para onde o cavalo se
	 *            moverá
	 * @param posfY
	 *            é a coordenada do eixo y do tabuleiro para onde o cavalo se
	 *            moverá
	 * @return true se for efetivada uma jogada válida e retorna false se a
	 *         jogada for inválida
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
	 * Método que verifica se existe alguma peça entre a coordenada inicial e a
	 * final do cavalo. 
	 * 
	 * @param x
	 *            é a coordenada do eixo x do tabuleiro para onde o cavalo se
	 *            moverá
	 * @param y
	 *            é a coordenada do eixo y do tabuleiro para onde o cavalo se
	 *            moverá
	 * 
	 * @return retorna sempre true pois o cavalo pode saltar sobre as peças
	 * não fazendo assim diferença se existir não uma peça no caminho
	 */
	public boolean pecaIntermediaria(int x, char y) {
		return true;
	}

	/**
	 * Método que verifica se a posição de destino é válida ou não
	 * 
	 * @param coordenada
	 *            representa a coordenada para onde o cavalo se moverá
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
	 * @return um string com a formatação de um cavalo, ou seja, a representação
	 *         gráfica de um cavalo
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