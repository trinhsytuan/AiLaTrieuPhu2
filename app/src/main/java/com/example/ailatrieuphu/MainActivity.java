package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ailatrieuphu.Model.DbHelper;
import com.example.ailatrieuphu.Model.QuestionDao;
import com.example.ailatrieuphu.Model.QuestionModel;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView btnPlayer;
    SoundPlayer soundPlayer;
    DbHelper helper;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        helper = new DbHelper(context);


        try {
            helper.createDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        soundPlayer.destoryAll();
    }

    @Override
    protected void onPause() {
        super.onPause();
        soundPlayer.destoryAll();
    }


}
