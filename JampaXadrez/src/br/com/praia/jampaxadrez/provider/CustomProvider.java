/*
 * Created on 10/08/2013 
 */
package br.com.praia.jampaxadrez.provider;

import java.util.List;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import br.com.praia.jampaxadrez.model.Jogador;
import br.com.praia.jampaxadrez.util.DbHelper;

/**
 * @author Junior
 *
 */
public class CustomProvider extends ContentProvider {
	
	private static String AUTHORITY = "br.com.praia.jampaxadrez.provider";
	private static String BASE_PATH = "jogador";
	public static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);


	/* (non-Javadoc)
	 * @see android.content.ContentProvider#onCreate()
	 */
	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public Cursor query(Uri arg0, String[] arg1, String arg2, String[] arg3, String arg4) {
		
		String[] colunas = {"nome", "vitorias"};
		MatrixCursor cursor = new MatrixCursor(colunas);
		List<Jogador> jogadores = DbHelper.obterJogadores();
		for (Jogador jogador : jogadores) {
			cursor.addRow(new Object[]{jogador.getName(), jogador.getVitorias()});
		}
		return cursor;
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[])
	 */
	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#delete(android.net.Uri, java.lang.String, java.lang.String[])
	 */
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see android.content.ContentProvider#getType(android.net.Uri)
	 */
	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see android.content.ContentProvider#insert(android.net.Uri, android.content.ContentValues)
	 */
	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		// TODO Auto-generated method stub
		return null;
	}
}
