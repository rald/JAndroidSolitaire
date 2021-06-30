package edu.hogwarts.siesta.solitaire;

import android.app.Activity;
import android.os.Bundle;

import android.media.AudioManager;

public class Solitaire extends Activity {

  SolitaireView solitaireView;

  @Override
  public void onCreate(Bundle bundle) {

    super.onCreate(bundle);

	setVolumeControlStream(AudioManager.STREAM_MUSIC);

    solitaireView = new SolitaireView(this);

    setContentView(solitaireView);

  }

}
