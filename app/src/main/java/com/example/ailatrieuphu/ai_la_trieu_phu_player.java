package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ailatrieuphu.Model.QuestionDao;
import com.example.ailatrieuphu.Model.QuestionModel;

public class ai_la_trieu_phu_player extends AppCompatActivity implements View.OnClickListener {
    ImageView btnHelpCall, btnChangeQuestion, btn5050, btnAudience;
    QuestionDao dao;
    TextView questions;
    Button dapana, dapanb, dapanc, dapand;
    QuestionModel ch;
    TextView cauhoithu;
    int cauhoi = 1;
    String dan = "";
    boolean traloi = true;
    SoundAnswer soundAnswer = new SoundAnswer(this, this);
    SoundPlayer player = new SoundPlayer(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_la_trieu_phu_player);
        dao = new QuestionDao(this);
        onInit();
        player.playNhac1den5();
    }

    public void onInit() {
        btnHelpCall = findViewById(R.id.helpCall);
        btnChangeQuestion = findViewById(R.id.helpDoiCauHoi);
        btn5050 = findViewById(R.id.help5050);
        btnAudience = findViewById(R.id.helpKhanGia);
        questions = findViewById(R.id.questions);
        dapana = findViewById(R.id.dapana);
        dapanb = findViewById(R.id.dapanb);
        dapanc = findViewById(R.id.dapanc);
        dapand = findViewById(R.id.dapand);
        cauhoithu = findViewById(R.id.cauhoi);
        btnHelpCall.setOnClickListener(this);
        btnChangeQuestion.setOnClickListener(this);
        btn5050.setOnClickListener(this);
        btnAudience.setOnClickListener(this);
        dapana.setOnClickListener(this);
        dapanb.setOnClickListener(this);
        dapanc.setOnClickListener(this);
        dapand.setOnClickListener(this);
        getCauHoi();

    }

    @Override
    public void onClick(View view) {
        player.stopNen();
        if (R.id.dapana == view.getId()) xuLyCauHoi("a");
        else if (R.id.dapanb == view.getId()) xuLyCauHoi("b");
        else if (R.id.dapanc == view.getId()) xuLyCauHoi("c");
        else if (R.id.dapand == view.getId()) xuLyCauHoi("d");
    }

    public void xuLyCauHoi(String da) {
        if (traloi == true) {
            traloi = false;
            dan = da;
            activeQuestion(da);
            soundAnswer.xuLyCauHoi(da);

        }
    }

    public void activeQuestion(String da) {
        if (da.equals("a")) dapana.setActivated(true);
        if (da.equals("b")) dapanb.setActivated(true);

        if (da.equals("c")) dapanc.setActivated(true);

        if (da.equals("d")) dapand.setActivated(true);

    }

    public void getCauHoi() {
        cauhoithu.setText(String.valueOf(cauhoi));
        player.nhaccauhoi(cauhoi);
        ch = dao.getQuestion(cauhoi);
        questions.setText(ch.getQuestion());
        dapana.setText(ch.getCasea());
        dapanb.setText(ch.getCaseb());
        dapanc.setText(ch.getCasec());
        dapand.setText(ch.getCased());
    }

    public void xulydapan() {
        int dadung = 0;
        if (dan.equals("a")) dadung = 1;
        if (dan.equals("b")) dadung = 2;
        if (dan.equals("c")) dadung = 3;
        if (dan.equals("d")) dadung = 4;


        if (dadung == ch.getTruecase()) {
            dapandung(ch.getTruecase());
        } else dapansai(ch.getTruecase());
    }

    public void dapandung(int dapan) {
        if (dapan == 1) dapana.setBackgroundResource(R.drawable.player_answer_background_true);
        if (dapan == 2) dapanb.setBackgroundResource(R.drawable.player_answer_background_true);
        if (dapan == 3) dapanc.setBackgroundResource(R.drawable.player_answer_background_true);
        if (dapan == 4) dapand.setBackgroundResource(R.drawable.player_answer_background_true);
        soundAnswer.correctAnswer(dan, cauhoi);
    }
    public void resetButton() {
        dapana.setBackgroundResource(R.drawable.btn_answer);
        dapanb.setBackgroundResource(R.drawable.btn_answer);
        dapanc.setBackgroundResource(R.drawable.btn_answer);
        dapand.setBackgroundResource(R.drawable.btn_answer);
        dapana.setActivated(false);
        dapanb.setActivated(false);
        dapanc.setActivated(false);
        dapand.setActivated(false);
        traloi = true;
    }
    public void chuyencauhoi() {
        if(cauhoi < 15) cauhoi++;
        resetButton();
        if(cauhoi == 6) {
            soundAnswer.cauhoiso5();
        } else if(cauhoi == 11) {

        } else if(cauhoi == 15) {

        }
        else {
            player.startNen();
            getCauHoi();
        }
    }

    public void dapansai(int dapan) {
        if (dapan == 1) dapana.setBackgroundResource(R.drawable.player_answer_background_wrong);
        if (dapan == 2) dapanb.setBackgroundResource(R.drawable.player_answer_background_wrong);
        if (dapan == 3) dapanc.setBackgroundResource(R.drawable.player_answer_background_wrong);
        if (dapan == 4) dapand.setBackgroundResource(R.drawable.player_answer_background_wrong);
        soundAnswer.wrongAnswer(ch.getTruecase());
    }
    public void startCau6() {
        player.playNhac6den9();
        getCauHoi();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.destoryAll();
    }

    @Override
    protected void onResume() {
        super.onResume();
        player.startNen();
    }
}