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



	public SolitaireView(Context context) {

		super(context);

		SolitaireView.context=context;

		setBackgroundColor(Color.BLACK);

		font=BitmapFactory.decodeResource(context.getResources(),R.drawable.font);

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



