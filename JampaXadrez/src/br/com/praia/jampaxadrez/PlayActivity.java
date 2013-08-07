package br.com.praia.jampaxadrez;

import br.com.praia.jampaxadrez.model.Jogador;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class PlayActivity extends Activity implements OnClickListener {

	private static final String LOG_NAME = "jampaXadrez";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		
		findViewById(R.id.bt_jogador1).setOnClickListener(this);
		findViewById(R.id.bt_jogador2).setOnClickListener(this);
		
		populate();
	}

	private void populate() {
		EditText edtJogador1 = (EditText) findViewById(R.id.ed_tx_jogador1);
		EditText edtJogador2 = (EditText) findViewById(R.id.ed_tx_jogador2);
		
		edtJogador1.setText("Jogador 1");
		edtJogador2.setText("Jogador 2");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play, menu);
		return true;
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_jogador1:
			cadastrarJogador((EditText)findViewById(R.id.ed_tx_jogador1));
			break;

		case R.id.bt_jogador2:
			cadastrarJogador((EditText)findViewById(R.id.ed_tx_jogador2));
			break;
		}
		
	}

	/**
	 * @param findViewById
	 */
	private void cadastrarJogador(EditText nomeJogador) {
		Jogador jogador = new Jogador();
		jogador.setName(nomeJogador.getText().toString());
		
		ObjectContainer db = MenuActivity.db;
		if (db != null && !db.ext().isClosed()) {
			ObjectSet<Jogador> lista = db.queryByExample(jogador);
			if (!lista.isEmpty()) {
				Log.i(LOG_NAME, "Jogador " + jogador.getName() + " encontrado");
			} else {
				db.store(jogador);
				db.commit();
				Toast.makeText(this,
						"Jogador " + jogador.getName() + " cadastrado.",
						Toast.LENGTH_SHORT).show();
			}
		}
		
		
	}

}
