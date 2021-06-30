package edu.hogwarts.siesta.solitaire;



import android.content.Context;
//import android.content.SharedPreferences;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
// import android.graphics.Rect;

//import android.media.AudioAttributes;
//import android.media.AudioManager;
//import android.media.MediaPlayer;
//import android.media.SoundPool;

import android.view.View;
import android.view.MotionEvent;

// import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Random;



public class SolitaireView extends View {

	static final int SCREEN_WIDTH=768;
	static final int SCREEN_HEIGHT=1024;

	static Random random = new Random();

	boolean gameover;

	static Context context;

	GameState gameState;

	TouchState touchState=null;
	double touchX=0,touchY=0;

	int offsetX=0,offsetY=0;

	Bitmap font;

	Bitmap[] cards;



	public SolitaireView(Context context) {

		super(context);

		SolitaireView.context=context;

		setBackgroundColor(Color.BLACK);

		font=BitmapFactory.decodeResource(context.getResources(),R.drawable.font);

		cards=new Bitmap[52];

		cards[0]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_clubs_a);
		cards[1]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_clubs_02);
		cards[2]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_clubs_03);
		cards[3]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_clubs_04);
		cards[4]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_clubs_05);
		cards[5]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_clubs_06);
		cards[6]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_clubs_07);
		cards[7]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_clubs_08);
		cards[8]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_clubs_09);
		cards[9]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_clubs_10);
		cards[10]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_clubs_j);
		cards[11]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_clubs_q);
		cards[12]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_clubs_k);

		cards[13]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_spades_a);
		cards[14]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_spades_02);
		cards[15]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_spades_03);
		cards[16]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_spades_04);
		cards[17]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_spades_05);
		cards[18]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_spades_06);
		cards[19]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_spades_07);
		cards[20]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_spades_08);
		cards[21]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_spades_09);
		cards[22]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_spades_10);
		cards[23]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_spades_j);
		cards[24]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_spades_q);
		cards[25]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_spades_k);

		cards[26]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_hearts_a);
		cards[27]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_hearts_02);
		cards[28]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_hearts_03);
		cards[29]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_hearts_04);
		cards[30]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_hearts_05);
		cards[31]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_hearts_06);
		cards[32]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_hearts_07);
		cards[33]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_hearts_08);
		cards[34]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_hearts_09);
		cards[35]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_hearts_10);
		cards[36]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_hearts_j);
		cards[37]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_hearts_q);
		cards[38]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_hearts_k);

		cards[39]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_diamonds_a);
		cards[40]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_diamonds_02);
		cards[41]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_diamonds_03);
		cards[42]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_diamonds_04);
		cards[43]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_diamonds_05);
		cards[44]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_diamonds_06);
		cards[45]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_diamonds_07);
		cards[46]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_diamonds_08);
		cards[47]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_diamonds_09);
		cards[48]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_diamonds_10);
		cards[49]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_diamonds_j);
		cards[50]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_diamonds_q);
		cards[51]=BitmapFactory.decodeResource(context.getResources(),R.drawable.card_diamonds_k);



		gameState=GameState.GAME_INIT;
	}



	@Override
	public void onDraw(Canvas canvas) {

		super.onDraw(canvas);



		if(gameState==GameState.GAME_INIT) {

			offsetX=(canvas.getWidth()-SCREEN_WIDTH)/2;
			offsetY=(canvas.getHeight()-SCREEN_HEIGHT)/2;

			gameover=false;

			gameState=GameState.GAME_PLAY;

		}



		Paint paint = new Paint();

		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.parseColor("#00AAFF"));
		paint.setFilterBitmap(false);

		canvas.drawRect(offsetX,offsetY,offsetX+SCREEN_WIDTH,offsetY+SCREEN_HEIGHT,paint);



		if(gameState==GameState.GAME_PLAY) {

			//Graphics.drawText(canvas,font,8,8,"Hello World",offsetX,offsetY,4);

			for(int i=0;i<52;i++) {
				int x=(i%13)*16*3+offsetX;
				int y=(i/13)*64*3+offsetY;
				Graphics.draw(canvas,cards[i],x,y,3);
			}

		}

		invalidate();

	}



	@Override
	public boolean onTouchEvent(MotionEvent event) {
		touchX=event.getX();
		touchY=event.getY();
		switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN: touchState=TouchState.TOUCH_DOWN; break;
			case MotionEvent.ACTION_MOVE: touchState=TouchState.TOUCH_MOVE; break;
			case MotionEvent.ACTION_UP:   touchState=TouchState.TOUCH_UP;   break;
		}
		return true;
	}



}



