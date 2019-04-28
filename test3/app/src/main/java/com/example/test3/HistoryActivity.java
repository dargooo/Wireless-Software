package com.example.test3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class HistoryActivity extends Activity {

    TableLayout table;
    TextView txtBalance;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);
        btnBack = (Button)findViewById(R.id.btnBack1);
        btnBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent activityIntent = new Intent(HistoryActivity.this, MainActivity.class);
                        Bundle newActivityInfo = new Bundle();
                        newActivityInfo.putDouble("main", 3.3);  // putDouble, putString, etc.
                        activityIntent.putExtras(newActivityInfo);
                        HistoryActivity.this.startActivity(activityIntent);
                    }
                });

        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        if (info != null) {
            info.get("hist");
        }

        db();

    }

    public void db() {

        table = (TableLayout) findViewById(R.id.tableLayout);
        for (int i = 1; i < MainActivity.count; i++) {
            final TextView txtDate = new TextView(this), txtAmount = new TextView(this), txtCategory = new TextView(this);
            txtDate.setText(MainActivity.dateList.get(i));
            txtDate.setTextSize(24);
            txtDate.setGravity(Gravity.CENTER_HORIZONTAL);

            txtAmount.setText(MainActivity.amountList.get(i).toString());
            txtAmount.setTextSize(24);
            txtAmount.setGravity(Gravity.CENTER_HORIZONTAL);

            txtCategory.setText(MainActivity.cateList.get(i));
            txtCategory.setTextSize(24);
            txtCategory.setGravity(Gravity.CENTER_HORIZONTAL);

            final TableRow row = new TableRow(this);
            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
            row.addView(txtDate);
            row.addView(txtAmount);
            row.addView(txtCategory);
            table.addView(row);
        }

        txtBalance = (TextView)findViewById(R.id.textViewCur);
        txtBalance.setText("Current Balance: $" + MainActivity.amountList.get(0));
    }
}
