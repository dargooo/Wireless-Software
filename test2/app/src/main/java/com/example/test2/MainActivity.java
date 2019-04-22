package com.example.test2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends Activity {

    protected static final String TAG = "MoneyAct";
    protected static final String KEY_INDEX = "index";
    public static int count;
    public static double balance;

    protected static final String DB_FILE = "money.db";

    Button btnAdd;
    Button btnSpend;
    Button btnHistory;

    public static ArrayList<String> dateList, cateList;
    public static ArrayList<Double> amountList;

    int mCurrentIndex = 0;

    public static SQLiteDatabase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dateList = new ArrayList<>();
        cateList = new ArrayList<>();
        amountList  = new ArrayList<>();
        connect();
System.out.println(balance);
        //前端
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.main_activity);

        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        if (info != null) {
            info.get("main");
        }

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityIntent = new Intent(MainActivity.this, UpdateActivity.class);
                Bundle newActivityInfo = new Bundle();
                newActivityInfo.putDouble("update", 1.1);  // putDouble, putString, etc.
                activityIntent.putExtras(newActivityInfo);
                MainActivity.this.startActivity(activityIntent);
            }
        });

        btnSpend = (Button)findViewById(R.id.btnSpend);
        btnSpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityIntent = new Intent(MainActivity.this, UpdateActivity.class);
                Bundle newActivityInfo = new Bundle();
                newActivityInfo.putDouble("update", 1.1);  // putDouble, putString, etc.
                activityIntent.putExtras(newActivityInfo);
                startActivity(activityIntent);
            }
        });

        btnHistory = (Button)findViewById(R.id.buttonView);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                Bundle newActivityInfo = new Bundle();
                newActivityInfo.putDouble("hist", 2.2);  // putDouble, putString, etc.
                activityIntent.putExtras(newActivityInfo);
                startActivity(activityIntent);
            }
        });
    }




    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }



    private void connect() {
        //连数据库
        boolean dbexist = getDatabasePath(DB_FILE).exists();
        if (dbexist) {
            Log.i(TAG, "Database exists!");
        }
        else
            Log.i(TAG, "Database does not exist!");
        myDB = openOrCreateDatabase(DB_FILE, Context.MODE_PRIVATE, null);
        //没有--现建
        if (!dbexist) {
            //create
            myDB.execSQL("CREATE TABLE Transactions(id INT primary key, date TEXT, amount DOUBLE, category TEXT)");
            //insert
            ContentValues row = new ContentValues();
            row.put("ID", 0);
            row.put("Date", "04/04/2019");
            row.put("amount", 0.0);
            row.put("category", "start");
            myDB.insert("Transactions", null, row);
        }
        count = 0;
        balance = 0.0;
        //从数据库调
        Cursor cursor = myDB.query("Transactions", null, null, null, null, null, null, null); // select * from Questions
        count = cursor.getCount();
        Log.i(TAG, "Number of rows = " + cursor.getCount());

        cursor.moveToFirst();
        do {
            dateList.add(cursor.getString(1));
            amountList.add(Double.valueOf(cursor.getString(2)));
            cateList.add(cursor.getString(3));
        } while (cursor.moveToNext());
        balance = amountList.get(0);
    }
}
