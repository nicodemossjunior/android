package br.com.praia.jampaxadrez;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

public class PlayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		
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

}
