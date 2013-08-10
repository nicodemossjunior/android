package br.com.praia.jampaxadrez;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import br.com.praia.jampaxadrez.model.Jogador;
import br.com.praia.jampaxadrez.model.Peca;
import br.com.praia.jampaxadrez.model.Tabuleiro;
import br.com.praia.jampaxadrez.util.DbHelper;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PlayActivity extends Activity implements OnClickListener {

	ImageView[] casas;
	Tabuleiro tabuleiro;

	private Jogador jogador1;
	private Jogador jogador2;
	private Jogador jogadorDaVez;

	private int statusPartida = Tabuleiro.MOVIMENTO_VALIDO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);

		findViewById(R.id.im_bt_jogador1).setOnClickListener(this);
		findViewById(R.id.im_bt_jogador2).setOnClickListener(this);
		findViewById(R.id.bt_add_vitoria1).setOnClickListener(this);
		findViewById(R.id.bt_add_vitoria2).setOnClickListener(this);

		posicionarPecas();
		populate();
	}

	private void posicionarPecas() {
		casas = new ImageView[64];
		tabuleiro = new Tabuleiro();

		casas[0] = (ImageView) findViewById(R.id.im_1);
		casas[1] = (ImageView) findViewById(R.id.im_2);
		casas[2] = (ImageView) findViewById(R.id.im_3);
		casas[3] = (ImageView) findViewById(R.id.im_4);
		casas[4] = (ImageView) findViewById(R.id.im_5);
		casas[5] = (ImageView) findViewById(R.id.im_6);
		casas[6] = (ImageView) findViewById(R.id.im_7);
		casas[7] = (ImageView) findViewById(R.id.im_8);

		casas[8] = (ImageView) findViewById(R.id.im_9);
		casas[9] = (ImageView) findViewById(R.id.im_10);
		casas[10] = (ImageView) findViewById(R.id.im_11);
		casas[11] = (ImageView) findViewById(R.id.im_12);
		casas[12] = (ImageView) findViewById(R.id.im_13);
		casas[13] = (ImageView) findViewById(R.id.im_14);
		casas[14] = (ImageView) findViewById(R.id.im_15);
		casas[15] = (ImageView) findViewById(R.id.im_16);

		casas[16] = (ImageView) findViewById(R.id.im_17);
		casas[17] = (ImageView) findViewById(R.id.im_18);
		casas[18] = (ImageView) findViewById(R.id.im_19);
		casas[19] = (ImageView) findViewById(R.id.im_20);
		casas[20] = (ImageView) findViewById(R.id.im_21);
		casas[21] = (ImageView) findViewById(R.id.im_22);
		casas[22] = (ImageView) findViewById(R.id.im_23);
		casas[23] = (ImageView) findViewById(R.id.im_24);

		casas[24] = (ImageView) findViewById(R.id.im_25);
		casas[25] = (ImageView) findViewById(R.id.im_26);
		casas[26] = (ImageView) findViewById(R.id.im_27);
		casas[27] = (ImageView) findViewById(R.id.im_28);
		casas[28] = (ImageView) findViewById(R.id.im_29);
		casas[29] = (ImageView) findViewById(R.id.im_30);
		casas[30] = (ImageView) findViewById(R.id.im_31);
		casas[31] = (ImageView) findViewById(R.id.im_32);

		casas[32] = (ImageView) findViewById(R.id.im_33);
		casas[33] = (ImageView) findViewById(R.id.im_34);
		casas[34] = (ImageView) findViewById(R.id.im_35);
		casas[35] = (ImageView) findViewById(R.id.im_36);
		casas[36] = (ImageView) findViewById(R.id.im_37);
		casas[37] = (ImageView) findViewById(R.id.im_38);
		casas[38] = (ImageView) findViewById(R.id.im_39);
		casas[39] = (ImageView) findViewById(R.id.im_40);

		casas[40] = (ImageView) findViewById(R.id.im_41);
		casas[41] = (ImageView) findViewById(R.id.im_42);
		casas[42] = (ImageView) findViewById(R.id.im_43);
		casas[43] = (ImageView) findViewById(R.id.im_44);
		casas[44] = (ImageView) findViewById(R.id.im_45);
		casas[45] = (ImageView) findViewById(R.id.im_46);
		casas[46] = (ImageView) findViewById(R.id.im_47);
		casas[47] = (ImageView) findViewById(R.id.im_48);

		casas[48] = (ImageView) findViewById(R.id.im_49);
		casas[49] = (ImageView) findViewById(R.id.im_50);
		casas[50] = (ImageView) findViewById(R.id.im_51);
		casas[51] = (ImageView) findViewById(R.id.im_52);
		casas[52] = (ImageView) findViewById(R.id.im_53);
		casas[53] = (ImageView) findViewById(R.id.im_54);
		casas[54] = (ImageView) findViewById(R.id.im_55);
		casas[55] = (ImageView) findViewById(R.id.im_56);

		casas[56] = (ImageView) findViewById(R.id.im_57);
		casas[57] = (ImageView) findViewById(R.id.im_58);
		casas[58] = (ImageView) findViewById(R.id.im_59);
		casas[59] = (ImageView) findViewById(R.id.im_60);
		casas[60] = (ImageView) findViewById(R.id.im_61);
		casas[61] = (ImageView) findViewById(R.id.im_62);
		casas[62] = (ImageView) findViewById(R.id.im_63);
		casas[63] = (ImageView) findViewById(R.id.im_64);

		for (int i = 0; i < casas.length; i++) {
			casas[i].setOnTouchListener(new MyTouchListener(this));
			casas[i].setOnDragListener(new MyDragListener(this));

			casas[i].setTag(i);

			Peca peca = tabuleiro.getCasa(i);
			if (peca != null) {
				casas[i].setImageResource(peca.getImagem());
			} else {
				casas[i].setImageDrawable(null);
			}
		}
	}

	// This defines your touch listener
	private final class MyTouchListener implements OnTouchListener {

		private Context activity;
		
		MyTouchListener(Context activity) {
			this.activity = activity;
		}
		
		public boolean onTouch(View view, MotionEvent motionEvent) {

			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
				Integer i = (Integer)view.getTag();
				Peca p = tabuleiro.getCasa(i);
				if (p != null) {
					int corJogador = p.getCor();
					if (statusPartida != Tabuleiro.MOVIMENTO_XEQUE_MATE ){
						if (jogadorDaVez.getCor() == corJogador) {
	
							ClipData data = ClipData.newPlainText("", "");
							DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
							
							view.startDrag(data, shadowBuilder, view, 0);
							view.setVisibility(View.INVISIBLE);
						} else {
							Toast.makeText(activity, R.string.jogador_errado, Toast.LENGTH_SHORT).show();
						}
						return true;
					}
				}
			} 
			
			return false;
			
		}
	}

	private final class MyDragListener implements OnDragListener {
		private Context activity;

		// Drawable enterShape = getResources().getDrawable(R.drawable.tp);
		// Drawable normalShape = getResources().getDrawable(R.drawable.tp);
		
		MyDragListener(Context activity) {
			this.activity = activity;
		}

		@Override
		public synchronized boolean onDrag(View v, DragEvent event) {
			
			int action = event.getAction();
			ImageView origem = (ImageView) event.getLocalState();
			ImageView destino = (ImageView) v;
			
			boolean jogadaValida = false;
			
			switch (action) {
			case DragEvent.ACTION_DRAG_ENDED:
				origem.setVisibility(View.VISIBLE);
				break;
				
			case DragEvent.ACTION_DROP:
				// Dropped, reassign View to ViewGroup

				if (origem.getId() != destino.getId()) {
					
					String coordenadaOrigem = Tabuleiro.posicaoTabuleiro((Integer) origem.getTag());
					String coordenadaDestino = Tabuleiro.posicaoTabuleiro((Integer) destino.getTag());
					
					switch (Tabuleiro.analisaMovimento(coordenadaOrigem,coordenadaDestino)) {
					case Tabuleiro.MOVIMENTO_INVALIDO:
						Toast.makeText(activity, R.string.mov_invalido, Toast.LENGTH_SHORT).show();
						jogadaValida = false;
						break;
						
					case Tabuleiro.MOVIMENTO_INVALIDO_XEQUE:
						Toast.makeText(activity, R.string.mov_invalido_cheque, Toast.LENGTH_SHORT).show();
						jogadaValida = false;
						break;
						
					case Tabuleiro.MOVIMENTO_XEQUE:
						Toast.makeText(activity, R.string.mov_cheque, Toast.LENGTH_SHORT).show();
						jogadaValida = true;
						break;
						
					case Tabuleiro.MOVIMENTO_XEQUE_MATE:
						Toast.makeText(activity, R.string.mov_cheque_mate, Toast.LENGTH_SHORT).show();
						jogadaValida = true;
						statusPartida = Tabuleiro.MOVIMENTO_XEQUE_MATE;
						DbHelper.atribuirVitoria(jogadorDaVez);
						
						break;
						
					case Tabuleiro.MOVIMENTO_VALIDO:
						jogadaValida = true;
						break;
						
					default:
						break;
					}
					
					if (jogadaValida) {
						
						atualizarTabuleiro(origem, destino);
						
						if (jogadorDaVez.equals(jogador1)) {
							jogadorDaVez = jogador2;
						} else {
							jogadorDaVez = jogador1;
						}
					}

				}
				break;
			// case DragEvent.ACTION_DRAG_ENDED:
			// v.setBackgroundDrawable(normalShape);
			default:
				break;
			}
			return true;
		}

		private void atualizarTabuleiro(ImageView origem, ImageView destino) {
			destino.setImageDrawable(origem.getDrawable());
			origem.setImageDrawable(null);
		}
	}

	private void populate() {
		EditText edtJogador1 = (EditText) findViewById(R.id.ed_tx_jogador1);
		EditText edtJogador2 = (EditText) findViewById(R.id.ed_tx_jogador2);

		edtJogador1.setText(R.string.jogador_1);
		edtJogador2.setText(R.string.jogador_2);
		
		jogador1 = new Jogador(edtJogador1.getText().toString());
		jogador1.setCor(Tabuleiro.JOGADOR_BRANCO);
		
		jogador2 = new Jogador(edtJogador2.getText().toString());
		jogador2.setCor(Tabuleiro.JOGADOR_PRETO);
		
		jogadorDaVez = jogador1;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.im_bt_jogador1:
			jogador1 = DbHelper
					.cadastrarJogador(((EditText) findViewById(R.id.ed_tx_jogador1))
							.getText().toString());
			break;

		case R.id.im_bt_jogador2:
			jogador2 = DbHelper
					.cadastrarJogador(((EditText) findViewById(R.id.ed_tx_jogador2))
							.getText().toString());
			break;

		case R.id.bt_add_vitoria1:
			DbHelper.atribuirVitoria(jogador1);
			break;

		case R.id.bt_add_vitoria2:
			DbHelper.atribuirVitoria(jogador2);
			break;
		}

	}

}
