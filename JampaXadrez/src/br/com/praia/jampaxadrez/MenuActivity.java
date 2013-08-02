package br.com.praia.jampaxadrez;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class MenuActivity extends Activity implements OnClickListener{

	private ObjectContainer db;
	private static final String LOG_NAME = "jampaXadrez";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		((ImageButton)findViewById(R.id.imPlay)).setOnClickListener(this);
		((ImageButton)findViewById(R.id.imRank)).setOnClickListener(this);
		((ImageButton)findViewById(R.id.imAbout)).setOnClickListener(this);
		
		String DB_NAME = "jampaXadrez.db4o";
		String DB_PATH = getDir("", MODE_PRIVATE).getPath();
		
		try {
			db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DB_PATH + "/" + DB_NAME);
		} catch (Exception e) {
			Log.e(LOG_NAME, "Nao foi possivel abrir o arquivo : " + DB_PATH + "/" + DB_NAME 
					+"\n Mensagem original: " + e.getMessage());
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public void onClick(View menuLink) {
		
		switch (menuLink.getId()) {
		case R.id.imPlay:

			Intent playActivity = new Intent(this, PlayActivity.class);
			startActivity(playActivity);
			
			break;

		case R.id.imRank:

			break;

		case R.id.imAbout:

			break;

		default:
			
			break;
		}
		
		
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if (db != null) {
			db.close();
		}
	}

}
