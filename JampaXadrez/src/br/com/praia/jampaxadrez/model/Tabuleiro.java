package br.com.praia.jampaxadrez.model;
/*
 * Created on 08/04/2005
 */

/**
 * @author Diego
 * @author Bianca
 * @author Andreza
 */
public class Tabuleiro {
	/** casas indica as 64 posições do tabuleiro cada casa tem sua coordenada associada*/
	private static Peca[] casas;

	/**colunas coordenada vertical do tabuleiro. Varia de 1 até 8*/
	private static int colunas[] = { 1, 2, 3, 4, 5, 6, 7, 8 };

	/**colunas coordenada horizontal do tabuleiro. Varia de a até h*/
	private static char linhas[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };

	/**pecasFora1 guarda as peças que estão fora do jogador 1*/
	private static Peca[] pecasFora1;

	/**pecasFora2 guarda as peças que estão fora do jogador 2*/
	private static Peca[] pecasFora2;

	/**qtdFora1 indica quantas peças do jogador 1 estão fora do jogo*/
	private static int qtdFora1 = 0;

	/**qtdFora2 indica quantas peças do jogador 2 estão fora do jogo*/
	private static int qtdFora2 = 0;

	/**
	 * Construtor da Classe Tabuleiro
	 * Responsável por instanciar todas as peças em seus devidos lugares
	 */
	public Tabuleiro() {
		casas = new Peca[64];
		pecasFora1 = new Peca[16];
		pecasFora2 = new Peca[16];

		Tabuleiro.casas[0] = new Torre("a1");
		Tabuleiro.casas[56] = new Torre("h1");
		Tabuleiro.casas[7] = new Torre("a8");
		Tabuleiro.casas[63] = new Torre("h8");
		Tabuleiro.casas[1] = new Cavalo("a2");
		Tabuleiro.casas[57] = new Cavalo("h2");
		Tabuleiro.casas[6] = new Cavalo("a7");
		Tabuleiro.casas[62] = new Cavalo("h7");
		Tabuleiro.casas[2] = new Bispo("a3");
		Tabuleiro.casas[58] = new Bispo("h3");
		Tabuleiro.casas[5] = new Bispo("a6");
		Tabuleiro.casas[61] = new Bispo("h6");
		Tabuleiro.casas[4] = new Rei("a5");
		Tabuleiro.casas[60] = new Rei("h5");
		Tabuleiro.casas[3] = new Rainha("a4");
		Tabuleiro.casas[59] = new Rainha("h4");
		Tabuleiro.casas[8] = new Peao("b1");
		Tabuleiro.casas[9] = new Peao("b2");
		Tabuleiro.casas[10] = new Peao("b3");
		Tabuleiro.casas[11] = new Peao("b4");
		Tabuleiro.casas[12] = new Peao("b5");
		Tabuleiro.casas[13] = new Peao("b6");
		Tabuleiro.casas[14] = new Peao("b7");
		Tabuleiro.casas[15] = new Peao("b8");
		Tabuleiro.casas[48] = new Peao("g1");
		Tabuleiro.casas[49] = new Peao("g2");
		Tabuleiro.casas[50] = new Peao("g3");
		Tabuleiro.casas[51] = new Peao("g4");
		Tabuleiro.casas[52] = new Peao("g5");
		Tabuleiro.casas[53] = new Peao("g6");
		Tabuleiro.casas[54] = new Peao("g7");
		Tabuleiro.casas[55] = new Peao("g8");
	}

/**
 * 
 * @param jogada - coordenada de destino de uma jogada 
 * @return retorna true se coordenada estiver contida no tabuleiro
 */
	public static boolean coordenadaValida(String jogada) {
		char linha;
		String temp;
		int coluna;

		linha = jogada.charAt(0);
		temp = jogada.charAt(1) + "";
		coluna = Integer.parseInt(temp);
		for (int i = 0; i < 8; i++)
			if (linha == Tabuleiro.linhas[i] && coluna == Tabuleiro.colunas[i])
				return true;
		return false;
	}
/**
 * @param coordenada - coordenada qualquer, pode ser de destino ou de origem
 * @return Retorna o primeiro caracter da coordenada correspondente a uma linha do tabuleiro 
 */
	public static char separaCoordenadaY(String coordenada) {
		char linha;

		linha = coordenada.charAt(0);
		return linha;
	}

	/**
	 * @param coordenada - coordenada qualquer, pode ser de destino ou de origem
	 * @return retorna um inteiro correspondente a coluna do tabuleiro 
	 */
	public static int separaCoordenadaX(String coordenada) {
		String temp;
		int coluna;

		temp = coordenada.charAt(1) + "";
		coluna = Integer.parseInt(temp);
		return coluna;
	}

/**
 * @param coordenada qualquer dentro do tabuleiro
 * @return Retorna um inteiro correspondente da coordenada a uma posição
 * do tabuleiro
 */
	public static int posicaoTabuleiro(String coordenada) {
		int posicaoTabuleiro = -1;
		int posX = Tabuleiro.separaCoordenadaX(coordenada);
		char posY = Tabuleiro.separaCoordenadaY(coordenada);

		switch (posY) {
			case 'a' :
				posicaoTabuleiro = (--posX);
				break;
			case 'b' :
				posicaoTabuleiro = (--posX) + 8;
				break;
			case 'c' :
				posicaoTabuleiro = (--posX) + 16;
				break;
			case 'd' :
				posicaoTabuleiro = (--posX) + 24;
				break;
			case 'e' :
				posicaoTabuleiro = (--posX) + 32;
				break;
			case 'f' :
				posicaoTabuleiro = (--posX) + 40;
				break;
			case 'g' :
				posicaoTabuleiro = (--posX) + 48;
				break;
			case 'h' :
				posicaoTabuleiro = (--posX) + 56;
		}
		return posicaoTabuleiro;
	}

/**
 * @param p - peça a qual será inserida no array de peçaFora
 */
	public static void setPecaFora(Peca p) {
		int pos = Tabuleiro.posicaoTabuleiro(p.getPosicao());

		if (!Tabuleiro.ehVazia(pos))
			if (p.getJogador() == 1) {
				pecasFora1[qtdFora1] = p;
				++qtdFora1;
			} else if (p.getJogador() == 2) {
				pecasFora2[qtdFora2] = p;
				++qtdFora2;
			}
	}
/**
 * @param coordi - Coordenada inicial de um movimento
 * @param coordf - Coordenada final de um movimento
 * @return
 */
	public static int analisaMovimento(String coordi, String coordf) {
		Peca pecaDaVez = Tabuleiro.getCasa(coordi);
		String coordPecaDaVez = pecaDaVez.getPosicao();
		String coordReiAdversario;

		if (pecaDaVez.getJogador() == 1)
			coordReiAdversario = Rei.getPosicaoRei(2);
		else
			coordReiAdversario = Rei.getPosicaoRei(1);
//		int jogDaVez = pecaDaVez.getJogador();

		if (verificaJogadaSuicida(coordi, coordf) == 0) //jogada pode ser feita
			return verificaXeque(coordPecaDaVez, coordReiAdversario);
		return verificaJogadaSuicida(coordi, coordf);
	}

	private static void desfazerMovimento(String coordi, Peca pEngolida) {
		/*
		 * mesmo depois de ser armazenada no arrays de pecas fora do jogo, a
		 * peca engolida continua com o campo posicao setado
		 */
		String coordf = pEngolida.getPosicao();
		int casai = Tabuleiro.posicaoTabuleiro(coordi);
		int casaf = Tabuleiro.posicaoTabuleiro(coordf);
		Peca pJogadora = Tabuleiro.getCasa(coordf);

		Tabuleiro.casas[casai] = pJogadora;
		Tabuleiro.casas[casaf] = pEngolida;
		pJogadora.setPosicao(coordi);
		if (pEngolida.getJogador() == 1)
			qtdFora1--;
		else
			qtdFora2--;
	}

	//-1 tem jogada suicida,0 nao tem, 1 nao pode ser feita a jogada
	public static int verificaJogadaSuicida(String coordi, String coordf) {
		Peca pecaDaVez = Tabuleiro.getCasa(coordi);
		Peca pecaAdversaria = Tabuleiro.getCasa(coordf); /* pode ser null */
//		String posReiAdversario;

		if (pecaDaVez.mova_se(coordf)) {
			/*
			 * verifica se a jogada é valida e troca temporariamente as posicoes
			 * das peças
			 */

			String posReiDaVez = Rei.getPosicaoRei(pecaDaVez.getJogador());
//			if (pecaDaVez.getJogador() == 1)
//				posReiAdversario = Rei.getPosicaoRei(2);
//			else
//				posReiAdversario = Rei.getPosicaoRei(1);
			int jogDaVez = pecaDaVez.getJogador();
//			int jogAdversario =
//				Tabuleiro.getCasa(posReiAdversario).getJogador();

			for (int i = 0; i < 63; i++) {
				if (Tabuleiro.casas[i] != null
					&& (Tabuleiro.casas[i]).getJogador() != jogDaVez) {
					int posRX = Tabuleiro.separaCoordenadaX(posReiDaVez);
					char posRY = Tabuleiro.separaCoordenadaY(posReiDaVez);
					if ((Tabuleiro.casas[i]).jogadaValida(posRX, posRY)) {
						if (pecaAdversaria == null)
							Tabuleiro.desfazerMovimento(pecaDaVez, coordi);
						else
							Tabuleiro.desfazerMovimento(coordi, pecaAdversaria);
						return -1;
						/*
						 * a jogada nao pode ser executada,pois o rei do jogador
						 * fica em xeque
						 */
					}
				}
			}
			return 0;
			/*
								  * o rei nao é ameaçado, portanto a jogada pode ser feita
								  */

		}
		return 1;
		/* o movimento nao pode ser feito!Jogada invalida! */
	}

	private static void desfazerMovimento(Peca pJogadora, String coordi) {
		int casai = Tabuleiro.posicaoTabuleiro(coordi);
		int casaf = Tabuleiro.posicaoTabuleiro(pJogadora.getPosicao());

		Tabuleiro.casas[casai] = pJogadora;
		Tabuleiro.casas[casaf] = null;
		pJogadora.setPosicao(coordi);
	}

	/**
	 * verifica se o rei adversario está em xeque depois da jogada realizada
	 * 
	 * @return @param
	 *         coordRei = rei adversario
	 */
	public static int verificaXeque(String coordPecaXeque, String coordRei) {
		int jogadorRei = Tabuleiro.getCasa(coordRei).getJogador();

		for (int i = 0; i < 63; i++) {
			if (Tabuleiro.casas[i] != null)
				if ((Tabuleiro.casas[i]).getJogador() != jogadorRei) {
					int posrX = Tabuleiro.separaCoordenadaX(coordRei);
					char posrY = Tabuleiro.separaCoordenadaY(coordRei);
					if ((Tabuleiro.casas[i]).jogadaValida(posrX, posrY)) {
						if (Tabuleiro
							.xequeMate(
								(Tabuleiro.casas[i]).getPosicao(),
								coordRei)) {
							return 2; //jogo acabou!
						}
						return 3; //rei adversario em xeque!
					}
					/*
					 * O jogador adversario tem seu rei em Xeque
					 */

				}
		}
		return 4; //jogada normal.
	}

	public static boolean xequeMate(String coordPecaXeque, String coordRei) {
		//peça que dá o xeque
		Peca pecaXeque = Tabuleiro.getCasa(coordPecaXeque);
		//peca que realiza o
		// xeque

		/* verifica se alguma peca pode comer a que aponta para o rei */
		for (int i = 0; i < 63; i++) {
			if (Tabuleiro.casas[i] != null) {

				String coordPecaSalvadora = Tabuleiro.casas[i].getPosicao();
				if ((Tabuleiro.casas[i]).getJogador() != pecaXeque.getJogador()
					&& Tabuleiro.verificaJogadaSuicida(
						Tabuleiro.casas[i].getPosicao(),
						coordPecaXeque)
						== 0) {
					Tabuleiro.desfazerMovimento(coordPecaSalvadora, pecaXeque);
					//rei fica exposto depois da jogada
					return false;
				}
				if (!(pecaXeque instanceof Cavalo || pecaXeque instanceof Rei))
					if (!pecaIntermXeque(pecaXeque))
						return false;
			}
		}
		return true;
	}

	/*
	 * Funcao que retorna false se houver peca Intermediaria e true se nao
	 * houver
	 */
	private static boolean pecaIntermXeque(Peca pecaXeque) {
		int jogadorRei, j;
		/*
		 * Informa qual o jogador do rei
		 */
		if (pecaXeque.getJogador() == 1)
			jogadorRei = 2;
		else
			jogadorRei = 1;
		String coordXeque = pecaXeque.getPosicao();
		String coordRei = Rei.getPosicaoRei(jogadorRei);
		char posRY = Tabuleiro.separaCoordenadaY(coordRei);
		int posRX = Tabuleiro.separaCoordenadaX(coordRei);
		char posPecaXequeY = Tabuleiro.separaCoordenadaY(coordXeque);
		int posPecaXequeX = Tabuleiro.separaCoordenadaX(coordXeque);
		for (int i = 0; i < 63; i++) {
			if (Tabuleiro.casas[i] != null) {
//				Peca pecaCasa = Tabuleiro.casas[i];
				if ((Tabuleiro.casas[i]).getJogador() != pecaXeque.getJogador()
					&& Tabuleiro.casas[i].jogadaValida(
						posPecaXequeX,
						posPecaXequeY)) {
					if (posRY > posPecaXequeY) {
						if (posRX == posPecaXequeX)
							for (i = posPecaXequeY + 1; i < posRY; i++)
								if (pecaXeque.jogadaValida(posRX, (char) i))
									return false;
						if (posRX < posPecaXequeX)
							for (i = posPecaXequeY + 1, j = (posPecaXequeX - 1);
								i < posRY;
								i++, j--)
								if (pecaXeque.jogadaValida(j, (char) i))
									return false;
						if (posRX > posPecaXequeX)
							for (i = posPecaXequeY + 1, j = posPecaXequeX + 1;
								i < posRY;
								i++, j++)
								if (pecaXeque.jogadaValida(j, (char) i))
									return false;
					}
					if (posRY < posPecaXequeY) {
						if (posPecaXequeX == posRX)
							for (i = posPecaXequeY - 1; i > posRY; i--)
								if (pecaXeque.jogadaValida(posRX, (char) i))
									return false;
						if (posRX > posPecaXequeX)
							for (i = posPecaXequeY - 1, j = posPecaXequeX + 1;
								i > posRY;
								i--, j++)
								if (pecaXeque.jogadaValida(j, (char) i))
									return false;

						if (posRX < posPecaXequeX)
							for (i = posPecaXequeY - 1, j = posPecaXequeX - 1;
								i > posRY;
								i--, j--)
								if (pecaXeque.jogadaValida(j, (char) i))
									return false;
					}
					if (posRY == posPecaXequeY) {
						if (posRX > posPecaXequeX)
							for (i = posPecaXequeX + 1; i < posRX; i++)
								if (pecaXeque.jogadaValida(i, (char) posRY))
									return false;
						if (posRX < posPecaXequeX)
							for (i = posPecaXequeX - 1; i > posRX; i--)
								if (pecaXeque.jogadaValida(i, (char) posRY))
									return false;
					}
				}

			}
		}
		return true;
	}

	public void setQtdPecaFora1(int x) {
		Tabuleiro.qtdFora1 = x;
	}

	public void setQtdPecaFora2(int x) {
		Tabuleiro.qtdFora2 = x;
	}

	public String toString(String jogador1, String jogador2) {
		char c = 'b';
		String tabuleiro = "   1 2 3 4 5 6 7 8        " + jogador1 + "\na| ";
		Peca p;
		for (int i = 0; i < 8; i++) {
			p = Tabuleiro.casas[i];
			if (p == null)
				tabuleiro += "  ";
			else
				tabuleiro += p.toString() + " ";
		}
		tabuleiro += "|a";
		if (qtdFora1 != 0) {
			tabuleiro += "     Peça(s) fora: ";
			for (int i = 0; i < qtdFora1; i++)
				tabuleiro += pecasFora1[i].toString() + ",";
			tabuleiro += "\nb| ";
		} else
			tabuleiro += "\nb| ";
		for (int i = 8; i < 64; i++) {
			p = Tabuleiro.casas[i];
			if ((i % 8) == 0 && i != 8)
				tabuleiro += "|" + c + "\n" + ++c + "| ";
			if (p == null)
				tabuleiro += "  ";
			else
				tabuleiro += p.toString() + " ";
		}
		if (qtdFora2 != 0) {
			tabuleiro += "|h     Peça(s) fora: ";
			for (int i = 0; i < qtdFora2; i++)
				tabuleiro += pecasFora2[i].toString() + ",";
		} else
			tabuleiro += "|h";
		tabuleiro += "\n   1 2 3 4 5 6 7 8        " + jogador2;
		return tabuleiro;
	}
	
	@Override
	public String toString() {
		return toString("Jogador1", "Jogador2");
	}

	public static void setCasa(Peca p, String coordenada) {
		Tabuleiro.casas[Tabuleiro.posicaoTabuleiro(coordenada)] = p;
		if (p != null)
			p.setPosicao(coordenada);
		if (p instanceof Rei)
			if (p.getJogador() == 1)
				Rei.setPosicaoRei(p);
			else
				Rei.setPosicaoRei(p);
	}

	public static boolean ehVazia(int x) {
		return Tabuleiro.casas[x] == null;
	}

	public static Peca getCasa(String coordenada) {
		int pos;
		pos = posicaoTabuleiro(coordenada);
		return Tabuleiro.casas[pos];
	}

}