package br.com.praia.jampaxadrez;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import br.com.praia.jampaxadrez.util.DbHelper;

public class MenuActivity extends Activity implements OnClickListener{

	private static final String DB_NAME = "jampaXadrez.db4o";;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		((ImageButton)findViewById(R.id.imPlay)).setOnClickListener(this);
		((ImageButton)findViewById(R.id.imRank)).setOnClickListener(this);
		((ImageButton)findViewById(R.id.imAbout)).setOnClickListener(this);

		DbHelper.configureDB(getDir("", MODE_PRIVATE).getPath() + "/" + DB_NAME);
		
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
			
			Intent rankingActivity = new Intent(this, RankingActivity.class);
			startActivity(rankingActivity);
			
			break;

		case R.id.imAbout:
			
			Intent aboutActivity = new Intent(this, AboutActivity.class);
			startActivity(aboutActivity);

			break;

		default:
			
			break;
		}
		
		
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if (DbHelper.db != null) {
			DbHelper.db.close();
		}
	}

}
