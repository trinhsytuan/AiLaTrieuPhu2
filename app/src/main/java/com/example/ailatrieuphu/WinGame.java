package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinGame extends AppCompatActivity {
    Button choilai;
    TextView tien;
    SoundPlayer player = new SoundPlayer(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_game);
        onInit();
    }
    public void onInit() {
        choilai = findViewById(R.id.btnRePlay);
        choilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        tien = findViewById(R.id.tien);
        xulytien();
    }
    public void xulytien() {
        Intent intent = getIntent();
        int cauhoi = intent.getIntExtra("cauhoi", 0);
        if(cauhoi == 1) tien.setText("200.000");
        if(cauhoi == 2) tien.setText("400.000");
        if(cauhoi == 3) tien.setText("600.000");
        if(cauhoi == 4) tien.setText("1.000.000");
        if(cauhoi == 5) tien.setText("2.000.000");
        if(cauhoi == 6) tien.setText("3.000.000");
        if(cauhoi == 7) tien.setText("6.000.000");
        if(cauhoi == 8) tien.setText("10.000.000");
        if(cauhoi == 9) tien.setText("14.000.000");
        if(cauhoi == 10) tien.setText("22.000.000");
        if(cauhoi == 11) tien.setText("30.000.000");
        if(cauhoi == 12) tien.setText("40.000.000");
        if(cauhoi == 13) tien.setText("60.000.000");
        if(cauhoi == 14) tien.setText("85.000.000");
        if(cauhoi == 15) tien.setText("150.000.000");
        if(cauhoi == 0) tien.setText("0");
        if(cauhoi == 15) player.bestPlayer();
        else player.thuacuoc();
    }
}