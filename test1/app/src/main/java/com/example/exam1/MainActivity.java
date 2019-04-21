package com.example.exam1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity {

    public static final String PREF_NOTE = "MyNotes";
    public static final String PREF_BALANCE = "MyBal";

    EditText textDate;
    EditText textAmount;
    EditText textLocation;
    Button buttonAdd;
    Button buttonLess;

    LinearLayout noteList;
    Context myActivity;
    TextView textCur;
    Double balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myActivity = (Context) this;
        textDate = (EditText) findViewById(R.id.editTextDate);
        textAmount = (EditText) findViewById(R.id.editTextAmount);
        textLocation = (EditText) findViewById(R.id.editTextLoc);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonLess= (Button) findViewById(R.id.buttonLess);

        noteList = (LinearLayout) findViewById(R.id.noteList);
        textCur = (TextView) findViewById(R.id.textViewBal);
        balance = Double.parseDouble(Store.getMoney(this));
        textCur.setText(balance + "");

        TextView note = new TextView(myActivity);
        note.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        note.setGravity(Gravity.LEFT);
        note.setTextSize(18);
        note.setText(getNote(MainActivity.this));
        noteList.addView(note);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"SetTextI18n", "ResourceType"})
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(textAmount.getText()) && !TextUtils.isEmpty(textLocation.getText()) && !TextUtils.isEmpty(textLocation.getText())){
                    TextView newNote = new TextView(myActivity);
                    newNote.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    newNote.setGravity(Gravity.LEFT);
                    newNote.setTextSize(18);
                    newNote.setText(textDate.getText() + " : Added $" + textAmount.getText() + " at " + textLocation.getText());
                    noteList.addView(newNote);
                    balance += Double.parseDouble(String.valueOf(textAmount.getText()));
                    textCur.setText(balance + "");
                    setMoney(balance + "",MainActivity.this);
                    setNote(getNote(MainActivity.this) + "\n" + newNote.getText(),MainActivity.this);
                }
                else {
                    Toast.makeText(MainActivity.this, "Please fill in all blanks!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonLess.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"SetTextI18n", "ResourceType"})
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(textAmount.getText()) && !TextUtils.isEmpty(textLocation.getText()) && !TextUtils.isEmpty(textLocation.getText())){
                    TextView newNote = new TextView(myActivity);
                    newNote.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    newNote.setGravity(Gravity.LEFT);
                    newNote.setTextSize(18);
                    newNote.setText(textDate.getText() + " : Spent $" + textAmount.getText() + " at " + textLocation.getText());
                    noteList.addView(newNote);
                    balance -= Double.parseDouble(String.valueOf(textAmount.getText()));
                    textCur.setText(balance + "");
                    setMoney(balance + "",MainActivity.this);
                    setNote(getNote(MainActivity.this) + "\n" + newNote.getText(),MainActivity.this);
                }
                else {
                    Toast.makeText(MainActivity.this, "Please fill in all blanks!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private static String getUserInfo(String key, Context context, String defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(key,
                Activity.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

    private static void setUserInfo(String key, String Value, Context context) {
        SharedPreferences settings = context.getSharedPreferences(key,
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, Value);
        editor.commit();
    }

    public static String getMoney(Context context) {
        return getUserInfo(MainActivity.PREF_BALANCE, context, "0.00");
    }

    public static void setMoney(String session, Context context) {
        setUserInfo(MainActivity.PREF_BALANCE, session, context);
    }

    public static String getNote(Context context) {
        return getUserInfo(MainActivity.PREF_NOTE, context, "");
    }

    public static void setNote(String session, Context context) {
        setUserInfo(MainActivity.PREF_NOTE, session, context);
    }

}
