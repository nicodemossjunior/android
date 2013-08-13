package br.com.praia;

import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ContentProviderClient;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private static String AUTHORITY = "br.com.praia.jampaxadrez.provider";
	private static String BASE_PATH = "jogador";
	public static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ContentProviderClient cp = getContentResolver().acquireContentProviderClient(CONTENT_URI);
		try {
			Cursor cursor = cp.query(CONTENT_URI, null, null, null, null);
			
			if (cursor.moveToFirst()) {
				do {
					Log.i("teste", cursor.getColumnName(0) + ": " + cursor.getString(0) +
							" , " + cursor.getColumnName(1) + ": " + cursor.getString(1));
				} while (cursor.moveToNext());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
