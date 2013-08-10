package br.com.praia.jampaxadrez;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.praia.jampaxadrez.model.Jogador;
import br.com.praia.jampaxadrez.util.DbHelper;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class RankingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranking);
		
		populate();
	}

	private void populate() {
		List<Jogador> arr = consultarJogadores();
		
		ArrayAdapter<Jogador> adapter = new ArrayAdapter<Jogador>(this, android.R.layout.simple_list_item_1, arr);
		ListView listView = (ListView) findViewById(R.id.lv_ranking);
		listView.setAdapter(adapter);
	}

	private List<Jogador> consultarJogadores() {
		List<Jogador> arr = new ArrayList<Jogador>();
		ObjectContainer db = DbHelper.db;
		if (db != null && !db.ext().isClosed()) {
			Query q = db.query();
			q.constrain(Jogador.class);
			ObjectSet<Jogador> jogadores = q.execute();
			
			if (jogadores != null && !jogadores.isEmpty()) {
				do {
					arr.add(jogadores.next());
				} while (jogadores.hasNext());
				
				Collections.sort(arr, new Comparator<Jogador>() {
					@Override
					public int compare(Jogador j1, Jogador j2) {
						return j1.getVitorias() < j2.getVitorias() ? 1 : j1.getVitorias() > j2.getVitorias() ? -1 : 0;
					}
				});
				
			}
		}
		return arr;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ranking, menu);
		return true;
	}

}
