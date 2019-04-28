package com.example.test3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChartActivity extends Activity {

    Button btnBack, btnAdded, btnSpent;
    ConstraintLayout canvas;
    EditText txtStart, txtEnd;
    List<Double> addList, spentList;
    Double addTotal, spentTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_activity);

        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        if (info != null) {
            info.get("chart");
        }

        canvas = (ConstraintLayout)findViewById(R.id.canvas);
        txtStart = (EditText)findViewById(R.id.editTextStart2);
        txtEnd = (EditText)findViewById(R.id.editTextEnd2);
        addTotal = 0.0;
        spentTotal = 0.0;

        btnAdded = (Button)findViewById(R.id.btnMoneyAdded2);
        btnAdded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addList = new ArrayList<>();
                addTotal = 0.0;
                String startDate = txtStart.getText().toString();
                String endDate = txtEnd.getText().toString();
                String[] selection = new String[]{"Amount", "SUM(Amount) As total"};
                String whereClause = "date > ? AND date < ? AND amount > 0";
                String[] whereArgs = new String[] { startDate, endDate };
                String groupBy = "category";
                Cursor cursor = MainActivity.myDB.query("Transactions", selection, whereClause, whereArgs, groupBy, null, null);
                cursor.moveToFirst();
                if (cursor.getCount() > 0) {
                    do {
                        Double cur = cursor.getDouble(1);
                        addList.add(cur);
                        addTotal += cur;
                    } while (cursor.moveToNext());
                    View drawPie = new DrawPie(ChartActivity.this, addList, addTotal);
                    canvas.addView(drawPie);
                }
                else {
                    Toast.makeText(ChartActivity.this, "No data matches!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSpent = (Button)findViewById(R.id.btnMoneySpent2);
        btnSpent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spentList = new ArrayList<>();
                spentTotal = 0.0;
                String startDate = txtStart.getText().toString();
                String endDate = txtEnd.getText().toString();
                String[] selection = new String[]{"Amount", "SUM(Amount) As total"};
                String whereClause = "date > ? AND date < ? AND amount < 0";
                String[] whereArgs = new String[] { startDate, endDate };
                String groupBy = "category";
                Cursor cursor = MainActivity.myDB.query("Transactions", selection, whereClause, whereArgs, groupBy, null, null);
                cursor.moveToFirst();
                if (cursor.getCount() > 0) {
                    do {
                        Double cur = cursor.getDouble(1);
                        spentList.add(cur);
                        spentTotal += cur;
                    } while (cursor.moveToNext());
                    View drawPie = new DrawPie(ChartActivity.this, spentList, spentTotal);
                    canvas.addView(drawPie);
                }
                else {
                    Toast.makeText(ChartActivity.this, "No data matches!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack = (Button)findViewById(R.id.btnBack4);
        btnBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent activityIntent = new Intent(ChartActivity.this, MainActivity.class);
                        Bundle newActivityInfo = new Bundle();
                        newActivityInfo.putDouble("main", 3.3);  // putDouble, putString, etc.
                        activityIntent.putExtras(newActivityInfo);
                        ChartActivity.this.startActivity(activityIntent);
                    }
                });

    }





    private class DrawPie extends View {

        List<Double> list;
        Double total;

        public DrawPie(Context context, List<Double> list, Double total) {
            super(context);
            this.list = list;
            this.total = total;
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint piePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

            //画圆
            piePaint.setColor(Color.DKGRAY);
            RectF pie = new RectF(canvas.getWidth()/6, canvas.getHeight()/8 + 40,
                    canvas.getWidth()*5/6, canvas.getHeight()/8 + 40 + canvas.getWidth()*2/3);
            canvas.drawOval(pie, piePaint);

            //画弧
            float degree, start = -90;
            for (int i = 0; i < list.size(); i++) {
                degree = (float) ((float)360 * list.get(i) / total);
                //color
                Random rnd = new Random();
                piePaint.setARGB(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                piePaint.setStyle(Paint.Style.FILL_AND_STROKE);
                //开画
                canvas.drawArc(pie, start, degree, true, piePaint);
                start += degree;
            }

        }


    }




}
