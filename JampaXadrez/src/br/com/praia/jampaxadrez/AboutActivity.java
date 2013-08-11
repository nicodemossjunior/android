package br.com.praia.jampaxadrez;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Scroller;

public class AboutActivity extends Activity {
	
	EditText about;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		about = (EditText) findViewById(R.id.editText1);
		about.setScroller(new Scroller(this)); 
		about.setMaxLines(1); 
		about.setVerticalScrollBarEnabled(true); 
		about.setMovementMethod(new ScrollingMovementMethod());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about, menu);
		return true;
	}

}
