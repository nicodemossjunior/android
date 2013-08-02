package br.com.praia.jampaxadrez.model;
/*
 * Created on 09/04/2005
 */

/**
 * @author Diego
 */
public class Bispo extends Peca {
	/**
		 * Construtor da classe Bispo
		 * 
		 * @param coordenada
		 *            representa a coordenada que informa onde o peao está no
		 *            momento, para comparar com as coordenadas das posicoes do
		 *            tabuleiro e assim dizer se o peao é do jogador 1 ou 2
		 */
	public Bispo(String coordenada) {
		this.setPosicao(coordenada);
		if (coordenada.equals("a3") || coordenada.equals("a6"))
			this.setJogador(1);
		else if (coordenada.equals("h3") || coordenada.equals("h6"))
			this.setJogador(2);
		setStatus(true);
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
	public boolean pecaIntermediaria(int x, char y) {
		int posX, i;
		char posY, j;
		String coordenada;

		posX = Tabuleiro.separaCoordenadaX(this.getPosicao());
		posY = Tabuleiro.separaCoordenadaY(this.getPosicao());

		if (posX > x && posY > y) { // p/ esquerda para cima
			for (i = --posX, j = --posY; i > x; --i, --j) {
				coordenada = "" + j + i;
				if (Tabuleiro.getCasa(coordenada) != null)
					return true;
			}
		}
		if (posX < x && posY > y) { // p/ direita para cima
			for (i = ++posX, j = --posY; i < x; ++i, --j) {
				coordenada = "" + j + i;
				if (Tabuleiro.getCasa(coordenada) != null)
					return true;
			}
		}
		if (posX > x && posY < y) { // p/ esquerda para baixo
			for (i = --posX, j = ++posY; i > x; --i, ++j) {
				coordenada = "" + j + i;
				Peca p = Tabuleiro.getCasa(coordenada);
				System.out.println(p);
				if (p != null) {
					return true;
				}
			}
		}
		if (posX < x && posY < y) { // p/ direita para baixo
			for (i = ++posX, j = ++posY; j < y; ++i, ++j) {
				coordenada = "" + j + i;
				if (Tabuleiro.getCasa(coordenada) != null)
					return true;
			}
		}
		return false;

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
	public boolean jogadaValida(int x, char y) {
		int posX;
		char posY;
				
		posX = Tabuleiro.separaCoordenadaX(this.getPosicao());
		posY = Tabuleiro.separaCoordenadaY(this.getPosicao());
		if (Math.abs(x - posX) == Math.abs(y - posY))
			if (!this.pecaIntermediaria(x, y))
				return true;
		return false;
	}

	/**
		 * @return um string com a formatação de um Bispo, ou seja, a representação
		 *         gráfica de um Bispo
		 */
	public String toString() {
		String bispo;

		if (this.getJogador() == 1)
			bispo = "B";
		else
			bispo = "b";
		return bispo;
	}

}