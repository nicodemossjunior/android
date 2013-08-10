package br.com.praia.jampaxadrez.model;

import br.com.praia.jampaxadrez.R;

/**
 * @author Bianca
 */
public class Torre extends Peca {

	/**
		 * Construtor da classe Torre
		 * 
		 * @param coordenada
		 *            representa a coordenada que informa onde o peao est� no
		 *            momento, para comparar com as coordenadas das posicoes do
		 *            tabuleiro e assim dizer se o peao � do jogador 1 ou 2
		 */
	public Torre(String coordenada) {

		this.setPosicao(coordenada);
		if (coordenada.equals("a1") || coordenada.equals("a8"))
			this.setCor(Tabuleiro.JOGADOR_BRANCO);
		else if (coordenada.equals("h1") || coordenada.equals("h8"))
			this.setCor(Tabuleiro.JOGADOR_PRETO);
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
	public boolean pecaIntermediaria(int posfX, char posfY) {
		String coordInic = this.getPosicao();
		int posiX = Tabuleiro.separaCoordenadaX(coordInic);
		char posiY = Tabuleiro.separaCoordenadaY(coordInic);

		if (posfY > posiY)
			for (int i = posiY + 1; i < posfY; i++) {
				String coordenada = "" + (char) i + posiX;
				int x = Tabuleiro.posicaoTabuleiro(coordenada);
				if (!Tabuleiro.ehVazia(x))
					return true;
			}
		if (posfY < posiY)
			for (int i = posfY + 1; i < posiY; i++) {
				String coordenada = "" + (char) i + posiX;
				int x = Tabuleiro.posicaoTabuleiro(coordenada);
				if (!Tabuleiro.ehVazia(x))
					return true;
			}
		if (posfX > posiX)
			for (int i = posiX + 1; i < posfX; i++) {
				String coordenada = "" + (char) posiY + i;
				int x = Tabuleiro.posicaoTabuleiro(coordenada);
				if (!Tabuleiro.ehVazia(x))
					return true;
			}
		if (posfX < posiX)
			for (int i = posfX + 1; i < posiX; i++) {
				String coordenada = "" + posiY + i;
				int x = Tabuleiro.posicaoTabuleiro(coordenada);
				if (!Tabuleiro.ehVazia(x))
					return true;
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
	public boolean jogadaValida(int posfX, char posfY) {

		String coordInic = this.getPosicao();
		char posiY = Tabuleiro.separaCoordenadaY(coordInic);
		int posiX = Tabuleiro.separaCoordenadaX(coordInic);
//		String coordenada = "" + posfY + posfX;
//		Peca p = Tabuleiro.getCasa(coordenada);

		if ((posiY != posfY && posiX == posfX)
				|| (posiX != posfX && posiY == posfY))
			if (!this.pecaIntermediaria(posfX, posfY))
				return true;
		return false;
	}

	/**
		 * @return um string com a formata��o de uma torre, ou seja, a representa��o
		 *         gr�fica de uma torre
		 */
	public String toString() {
		String torre;

		if (this.getCor() == 1)
			torre = "T";
		else
			torre = "t";
		return torre;
	}

	@Override
	public int getImagem() {
		int torre;

		if (this.getCor() == 1)
			torre = R.drawable.tb;
		else
			torre = R.drawable.tp;
		return torre;
	}

}