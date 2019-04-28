package com.example.test3;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends Activity {

    EditText txtDate, txtAmount, txtCategory;
    Button btnAdd, btnSpend, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);

        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        if (info != null) {
            info.get("update");
        }

        txtDate = (EditText)findViewById(R.id.editTextDate);
        txtAmount = (EditText)findViewById(R.id.editTextAmount);
        txtCategory = (EditText)findViewById(R.id.editTextCategory);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnSpend = (Button)findViewById(R.id.btnChart);
        btnBack = (Button)findViewById(R.id.btnBack3);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityIntent = new Intent(UpdateActivity.this, MainActivity.class);
                Bundle newActivityInfo = new Bundle();
                newActivityInfo.putDouble("main", 3.3);  // putDouble, putString, etc.
                activityIntent.putExtras(newActivityInfo);
                UpdateActivity.this.startActivity(activityIntent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(txtDate.getText()) && !TextUtils.isEmpty(txtAmount.getText()) && !TextUtils.isEmpty(txtCategory.getText())) {
                    ContentValues row = new ContentValues();
                    row.put("ID", MainActivity.count++);
                    row.put("Date", txtDate.getText().toString());
                    row.put("amount", Double.valueOf(txtAmount.getText().toString()));
                    row.put("category", txtCategory.getText().toString());
                    MainActivity.myDB.insert("Transactions", null, row);

                    MainActivity.balance += Double.valueOf(txtAmount.getText().toString());
                    ContentValues row0 = new ContentValues();
                    row0.put("ID", 0);
                    row0.put("Date", MainActivity.dateList.get(0));
                    row0.put("amount", MainActivity.balance);
                    row0.put("category", MainActivity.cateList.get(0));
                    MainActivity.myDB.update("Transactions", row0, "ID = ?", new String[] {"0"});
                    Toast.makeText(UpdateActivity.this, "Added money successfully!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(UpdateActivity.this, "Please fill in all blanks!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(txtDate.getText()) && !TextUtils.isEmpty(txtAmount.getText()) && !TextUtils.isEmpty(txtCategory.getText())) {
                    ContentValues row = new ContentValues();
                    row.put("ID", MainActivity.count++);
                    row.put("Date", txtDate.getText().toString());
                    row.put("amount", 0 - Double.valueOf(txtAmount.getText().toString()));
                    row.put("category", txtCategory.getText().toString());
                    MainActivity.myDB.insert("Transactions", null, row);

                    MainActivity.balance -= Double.valueOf(txtAmount.getText().toString());
                    ContentValues row0 = new ContentValues();
                    row0.put("ID", 0);
                    row0.put("Date", MainActivity.dateList.get(0));
                    row0.put("amount", MainActivity.balance);
                    row0.put("category", MainActivity.cateList.get(0));
                    MainActivity.myDB.update("Transactions", row0, "ID = ?", new String[] {"0"});
                    Toast.makeText(UpdateActivity.this, "Spent money successfully!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(UpdateActivity.this, "Please fill in all blanks!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
