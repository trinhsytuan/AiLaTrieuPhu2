package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ai_la_trieu_phu_player extends AppCompatActivity implements View.OnClickListener {
    ImageView btnHelpCall, btnChangeQuestion, btn5050, btnAudience;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_la_trieu_phu_player);
        onInit();
    }
    public void onInit() {
        btnHelpCall = findViewById(R.id.helpCall);
        btnChangeQuestion = findViewById(R.id.helpDoiCauHoi);
        btn5050 = findViewById(R.id.help5050);
        btnAudience = findViewById(R.id.helpKhanGia);
        btnHelpCall.setOnClickListener(this);
        btnChangeQuestion.setOnClickListener(this);
        btn5050.setOnClickListener(this);
        btnAudience.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}