package com.example.ailatrieuphu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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
    Button dapana, dapanb, dapanc, dapand, stopPlayer;
    QuestionModel ch;
    TextView cauhoithu;
    int cauhoi = 1;
    boolean traloi = true;

    String dan = "";
    boolean nammuoi = true, goi = true, khangia = true, changeQuestion = true;
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
        stopPlayer = findViewById(R.id.btnStopPlayer);
        btnHelpCall.setOnClickListener(this);
        btnChangeQuestion.setOnClickListener(this);
        btn5050.setOnClickListener(this);
        btnAudience.setOnClickListener(this);
        dapana.setOnClickListener(this);
        dapanb.setOnClickListener(this);
        dapanc.setOnClickListener(this);
        dapand.setOnClickListener(this);
        stopPlayer.setOnClickListener(this);
        getCauHoi();

    }

    @Override
    public void onClick(View view) {
        player.stopNen();
        if (R.id.dapana == view.getId()) xuLyCauHoi("a");
        else if (R.id.dapanb == view.getId()) xuLyCauHoi("b");
        else if (R.id.dapanc == view.getId()) xuLyCauHoi("c");
        else if (R.id.dapand == view.getId()) xuLyCauHoi("d");
        else if (R.id.btnStopPlayer == view.getId()) tambietluon();
        else if (R.id.helpDoiCauHoi == view.getId()) doicauhoi();
        else if (R.id.help5050 == view.getId()) help5050();
        else if(R.id.helpCall == view.getId()) goidienthoai();
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
        resetButton();
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
        if (cauhoi == 15) {
            cauhoi = 16;
            tambietluon();
        }
    }

    public void resetButton() {
        dapana.setBackgroundResource(R.drawable.btn_answer);
        dapanb.setBackgroundResource(R.drawable.btn_answer);
        dapanc.setBackgroundResource(R.drawable.btn_answer);
        dapand.setBackgroundResource(R.drawable.btn_answer);
        dapana.setVisibility(View.VISIBLE);
        dapanb.setVisibility(View.VISIBLE);
        dapanc.setVisibility(View.VISIBLE);
        dapand.setVisibility(View.VISIBLE);
        dapana.setActivated(false);
        dapanb.setActivated(false);
        dapanc.setActivated(false);
        dapand.setActivated(false);

        traloi = true;
    }

    public void chuyencauhoi() {
        if (cauhoi < 15) cauhoi++;

        if (cauhoi == 6) {
            soundAnswer.cauhoiso5();
        } else if (cauhoi == 11) {
            soundAnswer.vuotquacau10();
        } else if (cauhoi == 15) {
            soundAnswer.cau15();
        } else {
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

    public void startCau11() {
        player.playNhac10den15();
        getCauHoi();
    }

    public void startCau15() {
        player.startNen();
        getCauHoi();
    }

    public void tambiet() {
        player.destoryAll();
        int newCauHoi = 0;
        if (cauhoi >= 6 && cauhoi <= 11) newCauHoi = 5;
        if (cauhoi >= 12 && cauhoi < 14) newCauHoi = 10;
        Intent intent = new Intent(this, WinGame.class);
        intent.putExtra("cauhoi", newCauHoi);
        startActivity(intent);
    }

    public void tambietluon() {
        player.destoryAll();
        Intent intent = new Intent(this, WinGame.class);
        intent.putExtra("cauhoi", cauhoi - 1);
        startActivity(intent);
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

    public void onBackPressed() {
        showExitConfirmationDialog();
    }

    public void doicauhoi() {
        if (changeQuestion == true && traloi == true) {
            btnChangeQuestion.setImageResource(R.drawable.player_button_image_help_change_question_x);
            changeQuestion = false;
            getCauHoi();
            player.startNen();

        }
    }
    public void goidienthoai() {
        if(goi == true && traloi == true) {
            goi = false;
            btnHelpCall.setImageResource(R.drawable.player_button_image_help_call_x);
            soundAnswer.helpCall();
        }
    }

    private void showExitConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận thoát");
        builder.setMessage("Bạn có muốn dừng cuộc chơi ?");

        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Khi người dùng chọn "Có", thoát khỏi ứng dụng
                tambietluon();
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void helpCall() {
        String answer = "";
        int daTrue = ch.getTruecase();
        if(daTrue == 1) answer = "A";
        if(daTrue == 2) answer = "B";
        if(daTrue == 3) answer = "C";
        if(daTrue == 4) answer = "D";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Gọi điện thoại");
        builder.setMessage("Bạn của bạn tư vấn cho bạn đáp án: "+ answer);

        builder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void help5050() {
        if (nammuoi == true && traloi == true) {
            soundAnswer.trogiup5050();
            btn5050.setImageResource(R.drawable.player_button_image_help_5050_x);
            nammuoi = false;
        }
    }

    public void trogiup5050() {

        int correctAnswer = ch.getTruecase();
        int dem = 0;
        for (int i = 1; i <= 4; i++) {
            if (i == correctAnswer) continue;
            if (dem == 2) break;
            if (i == 1) dapana.setVisibility(View.INVISIBLE);
            if (i == 2) dapanb.setVisibility(View.INVISIBLE);
            if (i == 3) dapanc.setVisibility(View.INVISIBLE);
            if (i == 4) dapand.setVisibility(View.INVISIBLE);
            dem++;
        }
    }
}



