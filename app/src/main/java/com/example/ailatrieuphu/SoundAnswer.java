package com.example.ailatrieuphu;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.util.Random;

public class SoundAnswer {
    private ai_la_trieu_phu_player player;
    private Context context;

    public SoundAnswer(ai_la_trieu_phu_player player, Context context) {
        this.player = player;
        this.context = context;
    }

    public void xuLyCauHoi(String dapannguoidung) {
        Random random = new Random();
        String ttRandom = "";
        int randomNumber = random.nextInt(2) + 1; // Số ngẫu nhiên từ 1 đến 3
        if (randomNumber == 2) ttRandom = "2";
        String resourceName = "ans_" + dapannguoidung + ttRandom;
        int resID = context.getResources().getIdentifier(resourceName, "raw", context.getPackageName());
        MediaPlayer cauhoi = MediaPlayer.create(context, resID);
        if (cauhoi.isPlaying()) {
            cauhoi.pause();
        } else {
            cauhoi.start();
        }
        cauhoi.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                xinduaracautraloi();
            }
        });
    }

    public void xinduaracautraloi() {
        Random random = new Random();
        int randomNumber = random.nextInt(3) + 1; // Số ngẫu nhiên từ 1 đến 3
        String resourceName = "ans_now" + randomNumber;
        int resID = context.getResources().getIdentifier(resourceName, "raw", context.getPackageName());
        MediaPlayer dapan = MediaPlayer.create(context, resID);
        dapan.start();
        dapan.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                player.xulydapan();
            }
        });
    }

    public void camonban() {
        MediaPlayer trove = MediaPlayer.create(context, R.raw.lose);
        trove.start();
    }

    public void wrongAnswer(int dapan) {
        String daString = "";
        String resourceName = "lose_" + dapan;
        int resID = context.getResources().getIdentifier(resourceName, "raw", context.getPackageName());
        MediaPlayer dapanSound = MediaPlayer.create(context, resID);
        dapanSound.start();
    }

    public void correctAnswer(String dapan, int cauhoi) {
        String resourceName = "true_" + dapan;
        int resID = context.getResources().getIdentifier(resourceName, "raw", context.getPackageName());
        MediaPlayer dapanSound = MediaPlayer.create(context, resID);
        dapanSound.start();
        dapanSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                player.chuyencauhoi();
            }
        });
    }
    public void cauhoiso5() {
        MediaPlayer playcauhoiso5 = MediaPlayer.create(context, R.raw.chuc_mung_vuot_moc_01_0);
        playcauhoiso5.start();
        playcauhoiso5.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                player.startCau6();
            }
        });
    }
}
