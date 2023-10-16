package com.example.ailatrieuphu.Model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {
    private SQLiteDatabase db;

    public QuestionDao(Context context) {
        DbHelper helper = new DbHelper(context);
        helper.openDatabase();
        db = helper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<QuestionModel> get(String sql, String... selectArgs) {
        List<QuestionModel> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs);
        while (cursor.moveToNext()) {
            QuestionModel emp = new QuestionModel();

            emp.setQuestion(cursor.getString(cursor.getColumnIndex("question")));
            emp.setCasea(cursor.getString(cursor.getColumnIndex("casea")));
            emp.setCaseb(cursor.getString(cursor.getColumnIndex("caseb")));
            emp.setCasec(cursor.getString(cursor.getColumnIndex("casec")));
            emp.setCased(cursor.getString(cursor.getColumnIndex("cased")));
            emp.setTruecase(cursor.getInt(cursor.getColumnIndex("truecase")));
            list.add(emp);
        }
        cursor.close(); // Đóng con trỏ sau khi sử dụng.
        return list;
    }

    public QuestionModel getQuestion(int level) {
        String sql = "SELECT * FROM Question WHERE level = ? ORDER BY RANDOM() LIMIT 1";
        String[] selectArgs = { String.valueOf(level) };
        List<QuestionModel> questionList = get(sql, selectArgs);

        if (questionList.size() > 0) {
            return questionList.get(0);
        } else {
            return null;
        }
    }

}
