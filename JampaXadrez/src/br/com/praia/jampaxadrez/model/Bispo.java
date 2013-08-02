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
		 *            representa a coordenada que informa onde o peao est� no
		 *            momento, para comparar com as coordenadas das posicoes do
		 *            tabuleiro e assim dizer se o peao � do jogador 1 ou 2
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
		 * M�todo que verifica se existe alguma pe�a entre a coordenada inicial e a
		 * final do peao
		 * 
		 * @param posfX
		 *            � a coordenada do eixo x do tabuleiro para onde o peao se
		 *            mover�
		 * @param posfY
		 *            � a coordenada do eixo y do tabuleiro para onde o peao se
		 *            mover�
		 * 
		 * @return retorna true se existir alguma peca entre a coordenada inicial
		 *         que o jogador deu e a coordenada de destino que � passada por
		 *         par�metro, senao retorna false
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
		 * @return um string com a formata��o de um Bispo, ou seja, a representa��o
		 *         gr�fica de um Bispo
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