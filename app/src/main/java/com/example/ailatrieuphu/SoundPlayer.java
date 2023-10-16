package com.example.ailatrieuphu;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class SoundPlayer {
    private MediaPlayer mediaPlayer;
    private Context context;
    private MediaPlayer playNen, cauhoi;

    public SoundPlayer(Context context) {
        this.context = context;
    }

    public void startAltp() {
        mediaPlayer = MediaPlayer.create(context, R.raw.start_altp);
        mediaPlayer.setLooping(true);
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
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    public void release() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void playNhac1den5() {

        playNen = MediaPlayer.create(context, R.raw.background_music);
        playNen.setLooping(true);
        if (playNen.isPlaying()) {
            playNen.pause();
        } else {
            playNen.start();
        }
    }

    public void playNhac6den9() {
        playNen = MediaPlayer.create(context, R.raw.background_music_b);
        playNen.setLooping(true);
        if (playNen.isPlaying()) {
            playNen.pause();
        } else {
            playNen.start();
        }
    }

    public void playNhac10den15() {
        playNen = MediaPlayer.create(context, R.raw.background_music_c);
        playNen.setLooping(true);
        if (playNen.isPlaying()) {
            playNen.pause();
        } else {
            playNen.start();
        }
    }

    public void nhaccauhoi(int id) {
        String resourceName = "ques" + id;
        int resID = context.getResources().getIdentifier(resourceName, "raw", context.getPackageName());
        cauhoi = MediaPlayer.create(context, resID);

        if (cauhoi.isPlaying()) {
            cauhoi.pause();
        } else {
            cauhoi.start();
        }
    }

    public void stopNen() {
        if (playNen != null) {
            playNen.pause();
        }
    }

    public void startNen() {
        if (playNen != null) {
            playNen.start();
        }
    }
    public void bestPlayer() {
        MediaPlayer best = MediaPlayer.create(context, R.raw.best_player);
        best.start();
    }
    public void thuacuoc() {
        MediaPlayer thuacuocs = MediaPlayer.create(context, R.raw.lose);
        thuacuocs.start();
    }

    public void destoryAll() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (cauhoi != null) {
            cauhoi.release();
            cauhoi = null;
        }
        if (playNen != null) {
            playNen.pause();
        }

    }

}
