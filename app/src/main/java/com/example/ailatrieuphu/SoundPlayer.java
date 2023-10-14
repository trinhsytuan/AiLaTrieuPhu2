package com.example.ailatrieuphu;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundPlayer {
    private MediaPlayer mediaPlayer;
    private Context context;

    public SoundPlayer(Context context) {
        this.context = context;
    }

    public void startAltp() {
        mediaPlayer = MediaPlayer.create(context, R.raw.start_altp);
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
    }
    public void GioiThieuLuatChoi() {
        mediaPlayer = MediaPlayer.create(context, R.raw.luatchoi_b);
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer = MediaPlayer.create(context, R.raw.ready);
                mediaPlayer.start();
            }
        });
    }


    public void stopMediaPlayer() {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    public void release() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
