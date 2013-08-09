package br.com.praia.jampaxadrez.model;

import br.com.praia.jampaxadrez.R;

/*
 * Created on 14/04/2005
 */
/**
 * @author Andreza
 */

public class Peao extends Peca {
	/**
	 * Guarda a primeira posicao do peao no tabuleiro para depois verificar no
	 * método jogadaValida() se aquela é a primeira jogada de um determinado
	 * peao (para que ele possa andar duas casa se quiser só na primeira jogada)
	 */
	private final String posInicTabul;

	/**
	 * Construtor da classe Peao
	 * 
	 * @param coordenada
	 *            representa a coordenada que informa onde o peao está no
	 *            momento, para comparar com as coordenadas das posicoes do
	 *            tabuleiro e assim dizer se o peao é do jogador 1 ou 2
	 */
	public Peao(String coordenada) {
		this.setPosicao(coordenada);
		if (coordenada.equals("b1") || coordenada.equals("b2")
				|| coordenada.equals("b3") || coordenada.equals("b4")
				|| coordenada.equals("b5") || coordenada.equals("b6")
				|| coordenada.equals("b7") || coordenada.equals("b8"))
			this.setJogador(1);
		else if (coordenada.equals("g1") || coordenada.equals("g2")
				|| coordenada.equals("g3") || coordenada.equals("g4")
				|| coordenada.equals("g5") || coordenada.equals("g6")
				|| coordenada.equals("g7") || coordenada.equals("g8"))
			this.setJogador(2);
		this.setStatus(true);
		posInicTabul = this.getPosicao();
	}

	/**
	 * Método que verifica se existe alguma peça entre a coordenada inicial e a
	 * final do peao
	 * 
	 * @param posfX
	 *            é a coordenada do eixo x do tabuleiro para onde o peao se
	 *            moverá
	 * @param posfY
	 *            é a coordenada do eixo y do tabuleiro para onde o peao se
	 *            moverá
	 * 
	 * @return retorna true se existir alguma peca entre a coordenada inicial
	 *         que o jogador deu e a coordenada de destino que é passada por
	 *         parâmetro, senao retorna false
	 */
	public boolean pecaIntermediaria(int posfX, char posfY) {
		String coordenada = this.getPosicao();
		int posiX = Tabuleiro.separaCoordenadaX(coordenada);
		char posiY = Tabuleiro.separaCoordenadaY(coordenada);

		if (this.getJogador() == 1) {
			if (posfY - 2 == posiY && posfX == posiX) {
				char pecaIntermY = (char) (posfY - 1);
				int pecaIntermX = posiX;
				String coordInterm = "" + pecaIntermY + pecaIntermX;
				if (Tabuleiro.getCasa(coordInterm) != null) {
					return false;

				}
			}
		} else if (this.getJogador() == 2) {
			if (posfY + 2 == posiY && posfX == posiX) {
				char pecaIntermY = (char) (posfY + 1);
				int pecaIntermX = posiX;
				String coordInterm = "" + pecaIntermY + pecaIntermX;
				if (Tabuleiro.getCasa(coordInterm) != null)
					return false;
			}
		}
		return true;
	}

	/**
	 * Método que verifica se uma determinada jodada é válida ou não
	 * 
	 * @param posfX
	 *            é a coordenada do eixo x do tabuleiro para onde o peao se
	 *            moverá
	 * @param posfY
	 *            é a coordenada do eixo y do tabuleiro para onde o peao se
	 *            moverá
	 * @return true se for efetivada uma jogada válida e retorna false se a
	 *         jogada for inválida
	 */
	public boolean jogadaValida(int posfX, char posfY) {
		String coordenada = this.getPosicao();
		int posiX = Tabuleiro.separaCoordenadaX(coordenada);
		char posiY = Tabuleiro.separaCoordenadaY(coordenada);
		String coordFinal = "" + posfY + posfX;
		int pos = Tabuleiro.posicaoTabuleiro(coordFinal);

		if (this.getJogador() == 2) {
			if (posInicTabul == coordenada
					&& (posfY + 2 == posiY && posfX == posiX)
					&& pecaIntermediaria(posfX, posfY)
					&& Tabuleiro.ehVazia(pos))
				return true;
			if ((posfX == posiX + 1 && posfY == posiY - 1 || posfX == posiX - 1
					&& posfY == posiY - 1)
					&& (Tabuleiro.getCasa(coordFinal)).getJogador() != this
							.getJogador()) {
				return true;
			}
			if (Tabuleiro.ehVazia(pos)
					&& (posfY + 1 == posiY && posfX == posiX)) {
				return true;
			}
		}

		else if (this.getJogador() == 1) {
			if (posInicTabul == coordenada
					&& (posfY - 2 == posiY && posfX == posiX)
					&& pecaIntermediaria(posfX, posfY)
					&& Tabuleiro.ehVazia(pos))
				return true;
			if ((posfX == posiX + 1 && posfY == posiY + 1 || posfX == posiX - 1
					&& posfY == posiY + 1)
					&& (Tabuleiro.getCasa(coordFinal)).getJogador() != this
							.getJogador()) {
				return true;
			}
			if (Tabuleiro.ehVazia(pos)
					&& (posfY - 1 == posiY && posfX == posiX)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return um string com a formatação de um peao, ou seja, a representação
	 *         gráfica de um peao
	 */
	public String toString() {
		String peao;

		if (this.getJogador() == 1)
			peao = "P";
		else
			peao = "p";
		return peao;
	}

	@Override
	public int getImagem() {
		int peao;

		if (this.getJogador() == 1)
			peao = R.drawable.pb;
		else
			peao = R.drawable.pp;
		return peao;
	}

}