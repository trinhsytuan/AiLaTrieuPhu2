package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class gioi_thieu_luat_choi extends AppCompatActivity {
    SoundPlayer player = new SoundPlayer(this);
    Button startPlay;
    TextView txtShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi_thieu_luat_choi);
        onInit();
    }
    public void onInit() {
        player.GioiThieuLuatChoi();
        startPlay = findViewById(R.id.btnReady);
        txtShow = findViewById(R.id.textView5);
        startPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlayer();
            }
        });
    }
    public void startPlayer() {
        startPlay.setVisibility(View.GONE);
        txtShow.setVisibility(View.VISIBLE);
        player.stopMediaPlayer();
        MediaPlayer mediaPlayer;
        mediaPlayer = MediaPlayer.create(this, R.raw.gofind);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayers) {
                Intent intent = new Intent(gioi_thieu_luat_choi.this, ai_la_trieu_phu_player.class);
                startActivity(intent);
            }
        });
    }
}