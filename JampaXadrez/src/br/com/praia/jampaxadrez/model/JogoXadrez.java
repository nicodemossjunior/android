package br.com.praia.jampaxadrez.model;
/*
 * Created on 10/04/2005
 */

/**
 * @author Diego
 * @author Bianca
 * @author Andreza
 */
public class JogoXadrez {

	public static void main(String arg[]) {
		int opcao = 4;
		String hJogadas = "";

		System.out.println("Bem Vindos ao Bozez Xadrez!\n");
		while (true) {
			System.out.println("1 - Novo Jovo");
			System.out.println("2 - Exibir Historico de Jogadas!");
			System.out.println("3 - Sair do Jogo");
			System.out.println("opção:");

			opcao = EasyIn.getInt();
			if (opcao != 1 && opcao != 2 && opcao != 3)
				opcao = 4;

			switch (opcao) {
			case 1:
				hJogadas = novoJogo();
				break;
			case 2:
				exibeHistoricoJogadas(hJogadas);
				break;
			case 3:
				System.exit(0);
			case 4:
				System.out.println("\nopcao inválida! Tente novamente!");
			}
		}
	}

	/**
	 * Cria um novo jogo, e faz a entrada de dados para o jogo
	 * 
	 * @return Um String com todas as jogadas efetuadas durante um jogo!
	 */
	private static String novoJogo() {
		String jogadaI, jogadaF = "inicio";
		String jogador1, jogador2;
		int jogadorDaVez = 1;
		Peca p;
		boolean jogoAcabado = false;
		String hJogadas = "";

		Tabuleiro t = new Tabuleiro();

		System.out.println("Jogador 1 - Nome:");
		jogador1 = EasyIn.getString().toUpperCase();
		System.out.println("Jogador 2 - Nome:");
		jogador2 = EasyIn.getString().toLowerCase();
		while (true) {
			System.out.println("\n" + t.toString(jogador1, jogador2));
			if (jogadorDaVez == 1)
				System.out.println(jogador1 + " - jogada:");
			else
				System.out.println(jogador2 + " - jogada:");
			System.out.println("Escolha uma peça:");
			jogadaI = EasyIn.getString().toLowerCase();
			if (jogadaI.equals("desistir") || jogadaI.equals("fim"))
				break;
			if (validaComando(jogadaI)) {
				p = Tabuleiro.getCasa(jogadaI);
				if (p != null) {
					System.out.println("Destino:");
					jogadaF = EasyIn.getString().toLowerCase();
					if (jogadaF.equals("desistir") || jogadaF.equals("fim"))
						break;
					if (validaComando(jogadaF))
						if (p.getJogador() == jogadorDaVez) {
							switch (Tabuleiro
									.analisaMovimento(jogadaI, jogadaF)) {
							case -1:
								System.out
										.println("Jogada Invalida! Rei em xeque!");
								break;
							case 2:
								System.out.println("Xeque-Mate!\n\n");
								jogoAcabado = true;
								break;
							case 3:
								System.out.println("Xeque!");
								if (hJogadas.length() == 30)
									hJogadas = "\n";
								hJogadas += jogadaI + "->" + jogadaF + ", ";
								if (jogadorDaVez == 1)
									jogadorDaVez = 2;
								else
									jogadorDaVez = 1;
								break;
							case 1:
								System.out
										.println("Movimento Inválido! Tente Novamente!");
								break;
							case 4:
								if (hJogadas.length() == 30)
									hJogadas = "\n";
								hJogadas += jogadaI + "->" + jogadaF + ", ";
								if (jogadorDaVez == 1) //Jogada normal!
									jogadorDaVez = 2;
								else
									jogadorDaVez = 1;
							}
							if (jogoAcabado)
								break;
						} else
							System.out.println("Escolha uma peça");
					else
						System.out.println("Coordenada inválida!");
				} else
					System.out.println("Escolha uma peça!");
			} else
				System.out.println("Comando inválido!");
		}
		t.setQtdPecaFora1(0);
		t.setQtdPecaFora2(0);
		return hJogadas;
	}

	/**
	 * @param Recebe
	 *            um String comando Faz algumas checagens com este comando e
	 *            valida
	 * @return se o comando for valido a função retorna true se não retorna
	 *         falso
	 */
	private static boolean validaComando(String comando) {
		char posX, posY;

		if (comando.length() == 2) {
			posX = comando.charAt(1);
			posY = comando.charAt(0);
			if (posX > '0' && posX < '9' && posY >= 'a' && posY <= 'h')
				return true;
		}
		return false;
	}

	/**
	 * @param Recebe
	 *            um String com as Jogadas realizadas Exibi o Historico das
	 *            Jogadas realizadas
	 * @param hJogadas
	 */
	private static void exibeHistoricoJogadas(String hJogadas) {
		System.out.println("\n\nHistorico das Jogadas do Jogo Anterior:");
		if (hJogadas.length() == 0)
			hJogadas = "Vazio";
		System.out.println(hJogadas + "\n\n");
	}
}

