package br.com.praia.jampaxadrez.model;

import br.com.praia.jampaxadrez.R;

/**
 * @author Bianca
 */
public class Rainha extends Peca {

	/**
	 * Construtor da classe Rainha
	 * 
	 * @param coordenada
	 *            representa a coordenada que informa onde a rainha est� no
	 *            momento, para comparar com as coordenadas das posicoes do
	 *            tabuleiro e assim dizer se a rainha � do jogador 1 ou 2
	 */
	public Rainha(String coordenada) {

		this.setPosicao(coordenada);
		if (coordenada.equals("a4"))
			this.setJogador(1);
		else if (coordenada.equals("h4"))
			this.setJogador(2);
		setStatus(true);
	}

	/**
	 * M�todo que verifica se uma determinada jodada � v�lida ou n�o
	 * 
	 * @param posfX
	 *            � a coordenada do eixo x do tabuleiro para onde a rainha se
	 *            mover�
	 * @param posfY
	 *            � a coordenada do eixo y do tabuleiro para onde a rainha se
	 *            mover�
	 * @return true se for efetivada uma jogada v�lida e retorna false se a
	 *         jogada for inv�lida
	 */
	public boolean jogadaValida(int posfX, char posfY) {

		String coordInic = this.getPosicao();
		char posiY = Tabuleiro.separaCoordenadaY(coordInic);
		int posiX = Tabuleiro.separaCoordenadaX(coordInic);

		if ((posiY == posfY && posiX != posfX)
				|| (posiY != posfY && posiX == posfX))
			if (!this.pecaIntermediaria(posfX, posfY))
				return true;
		if (Math.abs(posiX - posfX) == Math.abs(posiY - posfY))
			if (!this.pecaIntermediaria(posfX, posfY))
				return true;
		return false;
	}

	/**
	 * M�todo que verifica se existe alguma pe�a entre a coordenada inicial e a
	 * final da rainha
	 * 
	 * @param posfX
	 *            � a coordenada do eixo x do tabuleiro para onde a rainha se
	 *            mover�
	 * @param posfY
	 *            � a coordenada do eixo y do tabuleiro para onde a rainha se
	 *            mover�
	 * 
	 * @return retorna true se existir alguma pe�a entre a coordenada inicial
	 *         que o jogador deu e a coordenada de destino que � passada por
	 *         par�metro, senao retorna false
	 */
	public boolean pecaIntermediaria(int posfX, char posfY) {
		String coordenada, coord = this.getPosicao();
		int i, j, posiX = Tabuleiro.separaCoordenadaX(coord);
		char posiY = Tabuleiro.separaCoordenadaY(coord);

		if (posfY > posiY) {
			if (posfX == posiX)
				for (i = posiY + 1; i < posfY; i++) {
					coordenada = "" + (char) i + posiX;
					int x = Tabuleiro.posicaoTabuleiro(coordenada);
					if (!Tabuleiro.ehVazia(x))
						return true;
				}
			if (posfX < posiX)
				for (i = posiY + 1, j = posiX - 1; i < posfY; i++, j--) {
					coordenada = "" + (char) i + j;
					int x = Tabuleiro.posicaoTabuleiro(coordenada);
					if (!Tabuleiro.ehVazia(x))
						return true;
				}

			if (posfX > posiX)
				for (i = posiY + 1, j = posiX + 1; i < posfY; i++, j++) {
					coordenada = "" + (char) i + j;
					int x = Tabuleiro.posicaoTabuleiro(coordenada);
					if (!Tabuleiro.ehVazia(x))
						return true;

				}
		}
		if (posfY < posiY) {
			if (posiX == posfX)
				for (i = posiY - 1; i > posfY; i--) {
					coordenada = "" + (char) i + posiX;
					int x = Tabuleiro.posicaoTabuleiro(coordenada);
					if (!Tabuleiro.ehVazia(x))
						return true;
				}

			if (posfX > posiX)
				for (i = posiY - 1, j = posiX + 1; i > posfY; i--, j++) {//ki
					coordenada = "" + (char) i + j;
					int x = Tabuleiro.posicaoTabuleiro(coordenada);
					if (!Tabuleiro.ehVazia(x))
						return true;
				}
			if (posfX < posiX)
				for (i = posiY - 1, j = posiX - 1; i > posfY; i--, j--) {
					coordenada = "" + (char) i + j;
					int x = Tabuleiro.posicaoTabuleiro(coordenada);
					if (!Tabuleiro.ehVazia(x))
						return true;
				}
		}
		if (posfY == posiY) {
			if (posfX > posiX)
				for (i = posiX + 1; i < posfX; i++) {
					coordenada = "" + (char) posiY + i;
					int x = Tabuleiro.posicaoTabuleiro(coordenada);
					if (!Tabuleiro.ehVazia(x))
						return true;
				}
			if (posfX < posiX)
				for (i = posiX - 1; i > posfX; i--) {
					coordenada = "" + (char) posiY + i;
					int x = Tabuleiro.posicaoTabuleiro(coordenada);
					if (!Tabuleiro.ehVazia(x))
						return true;
				}
		}
		return false;
	}

	/**
	 * @return um string com a formata��o de uma rainha, ou seja, a
	 *         representa��o gr�fica de uma rainha
	 */
	public String toString() {
		String rainha;

		if (this.getJogador() == 1)
			rainha = "Q";
		else
			rainha = "q";
		return rainha;
	}

	@Override
	public int getImagem() {
		int rainha;

		if (this.getJogador() == 1)
			rainha = R.drawable.qb;
		else
			rainha = R.drawable.qp;
		return rainha;
	}
}