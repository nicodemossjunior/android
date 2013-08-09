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
	 *            representa a coordenada que informa onde a rainha está no
	 *            momento, para comparar com as coordenadas das posicoes do
	 *            tabuleiro e assim dizer se a rainha é do jogador 1 ou 2
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
	 * Método que verifica se uma determinada jodada é válida ou não
	 * 
	 * @param posfX
	 *            é a coordenada do eixo x do tabuleiro para onde a rainha se
	 *            moverá
	 * @param posfY
	 *            é a coordenada do eixo y do tabuleiro para onde a rainha se
	 *            moverá
	 * @return true se for efetivada uma jogada válida e retorna false se a
	 *         jogada for inválida
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
	 * Método que verifica se existe alguma peça entre a coordenada inicial e a
	 * final da rainha
	 * 
	 * @param posfX
	 *            é a coordenada do eixo x do tabuleiro para onde a rainha se
	 *            moverá
	 * @param posfY
	 *            é a coordenada do eixo y do tabuleiro para onde a rainha se
	 *            moverá
	 * 
	 * @return retorna true se existir alguma peça entre a coordenada inicial
	 *         que o jogador deu e a coordenada de destino que é passada por
	 *         parâmetro, senao retorna false
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
	 * @return um string com a formatação de uma rainha, ou seja, a
	 *         representação gráfica de uma rainha
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