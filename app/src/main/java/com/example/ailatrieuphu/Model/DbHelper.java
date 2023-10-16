package com.example.ailatrieuphu.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;

public class DbHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "Question";
    private static String DB_PATH = "/data/data/com.example.ailatrieuphu/databases/";
    private Context mContext;
    private SQLiteDatabase myDatabase;

    public DbHelper(Context context) {

        super(context, DB_NAME, null, 1);

        this.mContext = context;
    }

    public void createDatabase() throws IOException {
        boolean dbExist = checkDatabase();

        if (!dbExist) {

            this.getReadableDatabase();

            try {
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDatabase() {
        String myPath = DB_PATH + DB_NAME;
        File file = new File(myPath);
        return file.exists();
    }

    private void copyDatabase() throws IOException {
        AssetManager assetManager = mContext.getAssets();
        InputStream inputStream = assetManager.open(DB_NAME + ".sqlite");
        String outFileName = DB_PATH + DB_NAME;
        OutputStream outputStream = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public void openDatabase() throws SQLException {
        if (myDatabase == null || !myDatabase.isOpen()) {
            String myPath = DB_PATH + DB_NAME;
            myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    @Override
    public synchronized void close() {
        if (myDatabase != null) {
            myDatabase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            createDatabase();

        } catch (IOException e) {
            Log.e("Loi", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
