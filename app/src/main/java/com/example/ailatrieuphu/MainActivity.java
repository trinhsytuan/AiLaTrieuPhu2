package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView btnPlayer;
    SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onInit();
    }

    public void onInit() {
        btnPlayer = findViewById(R.id.btnPlayer);
        soundPlayer = new SoundPlayer(this);
        soundPlayer.startAltp();
        btnPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPlayer.stopMediaPlayer();
                Intent switchToMain = new Intent(view.getContext(), gioi_thieu_luat_choi.class);
                startActivity(switchToMain);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPlayer.release();
    }
}
