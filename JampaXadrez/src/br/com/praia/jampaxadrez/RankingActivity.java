package br.com.praia.jampaxadrez;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.praia.jampaxadrez.model.Jogador;
import br.com.praia.jampaxadrez.util.DbHelper;

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
		return DbHelper.obterJogadores();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ranking, menu);
		return true;
	}

}
