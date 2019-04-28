package com.example.test3;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class SummaryActivity extends Activity {

    Button btnBack3, btnMoneyAdded, btnMoneySpent;
    EditText txtStart, txtEnd;
    TextView txtNum, txtTotal, txtAverage, txtMax, txtMin;
    DecimalFormat df = new DecimalFormat(".##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_activity);

        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        if (info != null) {
            info.get("summary");
        }

        txtStart = (EditText)findViewById(R.id.editTextStart2);
        txtEnd = (EditText)findViewById(R.id.editTextEnd2);
        txtNum = (TextView)findViewById(R.id.textViewNumber);
        txtTotal = (TextView)findViewById(R.id.textViewTotal);
        txtAverage = (TextView)findViewById(R.id.textViewAverage);
        txtMax = (TextView)findViewById(R.id.textViewMax);
        txtMin = (TextView)findViewById(R.id.textViewMin);

        btnMoneyAdded = (Button)findViewById(R.id.btnMoneyAdded2);
        btnMoneyAdded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startDate = txtStart.getText().toString();
                String endDate = txtEnd.getText().toString();

                String whereClause = "date > ? AND date < ? AND amount > 0";
                String[] whereArgs = new String[] { startDate, endDate };
                Cursor cursor = MainActivity.myDB.query("Transactions", null, whereClause, whereArgs, null, null, null);
                txtNum.setText(String.valueOf(cursor.getCount()));

                double total = 0.0;

                if (cursor.getCount() == 0) {
                    txtTotal.setText("$0.0");
                    txtAverage.setText("$0.0");
                    txtMax.setText("N/A");
                    txtMin.setText("N/A");
                }
                else {
                    cursor.moveToFirst();
                    do {
                        total += cursor.getDouble(2);
                    } while (cursor.moveToNext());
                    txtTotal.setText("$" + total);

                    txtAverage.setText("$" + df.format(total/cursor.getCount()));
                    String[] tableColumns = new String[] {"MAX(amount)"};
                    Cursor cursor2 = MainActivity.myDB.query("Transactions", tableColumns, whereClause, whereArgs, null, null, null);
                    cursor2.moveToFirst();
                    txtMax.setText("$" + cursor2.getDouble(0));

                    String[] tableColumns3 = new String[] {"MIN(amount)"};
                    Cursor cursor3 = MainActivity.myDB.query("Transactions", tableColumns3, whereClause, whereArgs, null, null, null);
                    cursor3.moveToFirst();
                    txtMin.setText("$" + cursor3.getDouble(0));
                }
            }
        });

        btnMoneySpent = (Button)findViewById(R.id.btnMoneySpent2);
        btnMoneySpent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startDate = txtStart.getText().toString();
                String endDate = txtEnd.getText().toString();

                String whereClause = "date > ? AND date < ? AND amount < 0";
                String[] whereArgs = new String[] { startDate, endDate };
                Cursor cursor = MainActivity.myDB.query("Transactions", null, whereClause, whereArgs, null, null, null);
                txtNum.setText(String.valueOf(cursor.getCount()));

                double total = 0.0;

                if (cursor.getCount() == 0) {
                    txtTotal.setText("$0.0");
                    txtAverage.setText("$0.0");
                    txtMax.setText("N/A");
                    txtMin.setText("N/A");
                }
                else {
                    cursor.moveToFirst();
                    do {
                        total -= cursor.getDouble(2);
                    } while (cursor.moveToNext());
                    txtTotal.setText("$" + total);

                    txtAverage.setText("$" + df.format(total/cursor.getCount()));
                    String[] tableColumns = new String[] {"MAX(amount)"};
                    Cursor cursor2 = MainActivity.myDB.query("Transactions", tableColumns, whereClause, whereArgs, null, null, null);
                    cursor2.moveToFirst();
                    txtMax.setText("$" + (0 - cursor2.getDouble(0)));

                    String[] tableColumns3 = new String[] {"MIN(amount)"};
                    Cursor cursor3 = MainActivity.myDB.query("Transactions", tableColumns3, whereClause, whereArgs, null, null, null);
                    cursor3.moveToFirst();
                    txtMin.setText("$" + (0 - cursor3.getDouble(0)));
                }

            }
        });

        btnBack3 = (Button)findViewById(R.id.btnBack3);
        btnBack3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent activityIntent = new Intent(SummaryActivity.this, MainActivity.class);
                        Bundle newActivityInfo = new Bundle();
                        newActivityInfo.putDouble("main", 3.3);  // putDouble, putString, etc.
                        activityIntent.putExtras(newActivityInfo);
                        SummaryActivity.this.startActivity(activityIntent);
                    }
                });


    }
}
