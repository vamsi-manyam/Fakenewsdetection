package com.example.geofencingpa1;

import android.content.Context;
import android.content.ContextWrapper;
import android.media.AudioManager;

public class Phonestate extends ContextWrapper {
    public Phonestate(Context base) {
        super(base);
    }
    public void changeringer(){
        AudioManager audioManager=
                (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }
    public void changenormal(){
        AudioManager audioManager=
                (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    }
}
