package com.example.tictactoe;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class SoundPlayer {

    public static int soundFlag = 1;
    private AudioAttributes audioAttributes;
    final int MAX = 2;

    private static SoundPool soundPool;
    private static int oSound;
    private static int deuseSound;
    private static int xSound;
    private static int winSound;

    public SoundPlayer(Context context){

        // soundPool is deprecated in API level 21. (lollipop)

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {

            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(MAX)
                    .build();
        }else {
            // SoundPool(int maxStreams, int streamType, int srcQuality);
            soundPool = new SoundPool(MAX, AudioManager.STREAM_MUSIC,0);
        }



        oSound = soundPool.load(context,R.raw.o,1);
        xSound = soundPool.load(context,R.raw.x,1);
        deuseSound = soundPool.load(context,R.raw.deuse,1);
        winSound = soundPool.load(context,R.raw.win,1);

    }

    public void playOSound(){
        if (soundFlag==1){
            soundPool.play(oSound,1.0f,1.0f,1,0,1.0f);
        }

    }
    public void playXSound(){
        if (soundFlag==1){
            soundPool.play(xSound,1.0f,1.0f,1,0,1.0f);
        }

    }public void playDeuseSound(){
        if (soundFlag==1){
            soundPool.play(deuseSound,1.0f,1.0f,1,0,1.0f);
        }

    }public void playWinSound(){
        if (soundFlag==1){
            soundPool.play(winSound,1.0f,1.0f,1,0,1.0f);
        }

    }
}
