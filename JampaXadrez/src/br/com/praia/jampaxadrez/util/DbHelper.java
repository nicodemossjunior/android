package br.com.praia.jampaxadrez.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.util.Log;
import android.widget.CursorAdapter;
import br.com.praia.jampaxadrez.model.Jogador;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class DbHelper {

	public static ObjectContainer db;
	
	public static void configureDB(String dbPathAndName) {
		if (db == null || db.ext().isClosed()) {
			
			try {
				db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), dbPathAndName);
			} catch (Exception e) {
				Log.e(LogUtil.LOG_NAME,
						"Nao foi possivel abrir o arquivo : " +dbPathAndName + "\n Mensagem original: "
								+ e.getMessage());
			}
		}
	}
	
	/**
	 * @param jogador
	 */
	public static void atribuirVitoria(String nomeJogador) {
		Jogador jogador = new Jogador(nomeJogador);
		if (db != null && !db.ext().isClosed()) {
			ObjectSet<Jogador> jogadores = db.queryByExample(jogador);
			if (jogadores != null && !jogadores.isEmpty()) {
				Jogador obtido = jogadores.next();
				Log.i(LogUtil.LOG_NAME, obtido.toString());
				obtido.setVitorias(obtido.getVitorias() + 1);
				db.store(obtido);
				db.commit();
//				Toast.makeText(this, "Vitórias: "+obtido.getVitorias(), Toast.LENGTH_SHORT).show();
			} 
//			else {
//				Toast.makeText(this, "Jogador não encontrado", Toast.LENGTH_SHORT).show();
//			}
		}
	}

	/**
	 * Nome do jogador
	 * @param nomeJogador
	 * @return jogador cadastrado
	 */
	public static Jogador cadastrarJogador(String nomeJogador) {
		Jogador jogador = new Jogador(nomeJogador);
		
		if (db != null && !db.ext().isClosed()) {
			ObjectSet<Jogador> lista = db.queryByExample(jogador);
			if (lista != null && !lista.isEmpty()) {
				jogador = lista.next();
//				Toast.makeText(this,
//						"Jogador " + jogador.getName() + " encontrado.",
//						Toast.LENGTH_SHORT).show();
			} else {
				db.store(jogador);
				db.commit();
//				Toast.makeText(this,
//						"Jogador " + jogador.getName() + " cadastrado.",
//						Toast.LENGTH_SHORT).show();
			}
		}
		
		return jogador;
	}

	/**
	 * @return lista com todos os jogadores
	 */
	public static List<Jogador> obterJogadores() {
		List<Jogador> itens = new ArrayList<Jogador>();

		if (db != null && !db.ext().isClosed()) {
			Query q = db.query();
			q.constrain(Jogador.class);
			ObjectSet<Jogador> jogadores = q.execute();
			
			itens.addAll(jogadores);
			
			Collections.sort(itens, new Comparator<Jogador>() {
				@Override
				public int compare(Jogador j1, Jogador j2) {
					return j1.getVitorias() < j2.getVitorias() ? 1 : j1.getVitorias() > j2.getVitorias() ? -1 : 0;
				}
			});
		}
		return itens;
	}

	
	
}
