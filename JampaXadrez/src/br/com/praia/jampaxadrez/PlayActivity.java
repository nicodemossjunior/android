package br.com.praia.jampaxadrez;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import br.com.praia.jampaxadrez.model.Jogador;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PlayActivity extends Activity implements OnClickListener {

	private static final String LOG_NAME = "jampaXadrez";
	
	ImageView[] casas;
	private FrameLayout tab;
	private final static int leftMargin[] = { 48, 110, 176, 244 };
	private final static int topMargin[] = { 54, 115, 180, 250 };

	private Jogador jogador1;
	private Jogador jogador2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);

		tab = (FrameLayout) findViewById(R.id.flayout_tab);

		posicionarPecas();
		
		findViewById(R.id.bt_jogador1).setOnClickListener(this);
		findViewById(R.id.bt_jogador2).setOnClickListener(this);
		findViewById(R.id.bt_add_vitoria1).setOnClickListener(this);
		findViewById(R.id.bt_add_vitoria2).setOnClickListener(this);
		
		populate();
	}

	private void posicionarPecas() {
		casas = new ImageView[64];

		LayoutParams params;

		casas[0] = new ImageView(this);
		casas[0].setImageResource(R.drawable.tp);
		casas[0].setAdjustViewBounds(true);
		params = new LayoutParams(40, 40);
		params.topMargin = topMargin[0];
		params.leftMargin = leftMargin[0];
		casas[0].setLayoutParams(params);
		casas[0].setOnTouchListener(new MyTouchListener());
		casas[0].setOnDragListener(new MyDragListener());
		tab.addView(casas[0]);

		casas[1] = new ImageView(this);
		casas[1].setImageResource(R.drawable.cp);
		casas[1].setAdjustViewBounds(true);
		params = new LayoutParams(40, 40);
		params.topMargin = topMargin[0];
		params.leftMargin = leftMargin[1];
		casas[1].setLayoutParams(params);
		tab.addView(casas[1]);

		casas[2] = new ImageView(this);
		casas[2].setImageResource(R.drawable.bp);
		casas[2].setAdjustViewBounds(true);
		params = new LayoutParams(40, 40);
		params.topMargin = topMargin[0];
		params.leftMargin = leftMargin[2];
		casas[2].setLayoutParams(params);
		tab.addView(casas[2]);

		casas[3] = new ImageView(this);
		casas[3].setImageResource(R.drawable.kp);
		casas[3].setAdjustViewBounds(true);
		params = new LayoutParams(40, 40);
		params.topMargin = topMargin[0];
		params.leftMargin = leftMargin[3];
		casas[3].setLayoutParams(params);
		tab.addView(casas[3]);

		casas[9] = new ImageView(this);
		casas[9].setImageResource(R.drawable.pp);
		casas[9].setAdjustViewBounds(true);
		params = new LayoutParams(40, 40);
		params.topMargin = topMargin[1];
		params.leftMargin = leftMargin[0];
		casas[9].setLayoutParams(params);
		tab.addView(casas[9]);
	}

	// This defines your touch listener
	private final class MyTouchListener implements OnTouchListener {
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
				ClipData data = ClipData.newPlainText("", "");
				DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
						view);
				view.startDrag(data, shadowBuilder, view, 0);
				view.setVisibility(View.INVISIBLE);
				return true;
			} else {
				return false;
			}
		}
	}

	class MyDragListener implements OnDragListener {
		  Drawable enterShape = getResources().getDrawable(R.drawable.tp);
		  Drawable normalShape = getResources().getDrawable(R.drawable.tp);
		  
		  @Override
		  public boolean onDrag(View v, DragEvent event) {
		    int action = event.getAction();
		    switch (event.getAction()) {
		    case DragEvent.ACTION_DRAG_STARTED:
		    // Do nothing
		      break;
		    case DragEvent.ACTION_DRAG_ENTERED:
		      v.setBackgroundDrawable(enterShape);
		      break;
		    case DragEvent.ACTION_DRAG_EXITED:        
		      v.setBackgroundDrawable(normalShape);
		      break;
		    case DragEvent.ACTION_DROP:
		      // Dropped, reassign View to ViewGroup
		      View view = (View) event.getLocalState();
		      ViewGroup owner = (ViewGroup) view.getParent();
		      owner.removeView(view);
		      LinearLayout container = (LinearLayout) v;
		      container.addView(view);
		      view.setVisibility(View.VISIBLE);
		      break;
		    case DragEvent.ACTION_DRAG_ENDED:
		      v.setBackgroundDrawable(normalShape);
		      default:
		      break;
		    }
		    return true;
		  }
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
			jogador1 = cadastrarJogador((EditText)findViewById(R.id.ed_tx_jogador1));
			break;

		case R.id.bt_jogador2:
			jogador2 = cadastrarJogador((EditText)findViewById(R.id.ed_tx_jogador2));
			break;
		
		case R.id.bt_add_vitoria1:
			atribuirVitoria(jogador1);
			break;
		
		case R.id.bt_add_vitoria2:
			atribuirVitoria(jogador2);
			break;
		}
		
	}

	/**
	 * @param jogador
	 */
	private void atribuirVitoria(Jogador jogador) {
		ObjectContainer db = MenuActivity.db;
		if (db != null && !db.ext().isClosed()) {
			ObjectSet<Jogador> jogadores = db.queryByExample(jogador);
			if (jogadores != null && !jogadores.isEmpty()) {
				Jogador obtido = jogadores.next();
				Log.i(LOG_NAME, obtido.toString());
				obtido.setVitorias(obtido.getVitorias() + 1);
				db.store(obtido);
				db.commit();
				Toast.makeText(this, "Vitórias: "+obtido.getVitorias(), Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "Jogador não encontrado", Toast.LENGTH_SHORT).show();
			}
		}
	}

	/**
	 * Nome do jogador
	 * @param nomeJogador
	 * @return jogador cadastrado
	 */
	private Jogador cadastrarJogador(EditText nomeJogador) {
		Jogador jogador = new Jogador();
		jogador.setName(nomeJogador.getText().toString());
		
		ObjectContainer db = MenuActivity.db;
		if (db != null && !db.ext().isClosed()) {
			ObjectSet<Jogador> lista = db.queryByExample(jogador);
			if (lista != null && !lista.isEmpty()) {
				Log.i(LOG_NAME, "Jogador " + jogador.getName() + " encontrado");
			} else {
				db.store(jogador);
				db.commit();
				Toast.makeText(this,
						"Jogador " + jogador.getName() + " cadastrado.",
						Toast.LENGTH_SHORT).show();
			}
		}
		
		return jogador;
	}

}
