package com.example.assignment1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    EditText centiText, fahText, kmText, mileText, kgText, lbText, literText, gallonText;
    Button resetBtn;

    DecimalFormat df = new DecimalFormat(".######");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        centiText = (EditText) findViewById(R.id.editTextCenti);
        fahText = (EditText) findViewById(R.id.editTextFah);
        kmText = (EditText) findViewById(R.id.editTextKm);
        mileText = (EditText) findViewById(R.id.editTextMile);
        kgText = (EditText) findViewById(R.id.editTextKg);
        lbText = (EditText) findViewById(R.id.editTextLb);
        literText = (EditText) findViewById(R.id.editTextLiter);
        gallonText = (EditText) findViewById(R.id.editTextGallon);
        resetBtn = (Button) findViewById(R.id.resetButton);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                centiText.removeTextChangedListener(centiWatcher);
                fahText.removeTextChangedListener(fahWatcher);
                kmText.removeTextChangedListener(kmWatcher);
                mileText.removeTextChangedListener(mileWatcher);
                kgText.removeTextChangedListener(kgWatcher);
                lbText.removeTextChangedListener(lbWatcher);
                literText.removeTextChangedListener(literWatcher);
                gallonText.removeTextChangedListener(gallonWatcher);
                centiText.setText("");
                fahText.setText("");
                kmText.setText("");
                mileText.setText("");
                kgText.setText("");
                lbText.setText("");
                literText.setText("");
                gallonText.setText("");
                centiText.addTextChangedListener(centiWatcher);
                fahText.addTextChangedListener(fahWatcher);
                kmText.addTextChangedListener(kmWatcher);
                mileText.addTextChangedListener(mileWatcher);
                kgText.addTextChangedListener(kgWatcher);
                lbText.addTextChangedListener(lbWatcher);
                literText.addTextChangedListener(literWatcher);
                gallonText.addTextChangedListener(gallonWatcher);
            }
        });

        centiText.addTextChangedListener(centiWatcher);
        fahText.addTextChangedListener(fahWatcher);
        kmText.addTextChangedListener(kmWatcher);
        mileText.addTextChangedListener(mileWatcher);
        kgText.addTextChangedListener(kgWatcher);
        lbText.addTextChangedListener(lbWatcher);
        literText.addTextChangedListener(literWatcher);
        gallonText.addTextChangedListener(gallonWatcher);


    }



    //C -> F
    private final TextWatcher centiWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == "0.0") return;
            double centi = 0.0, fah = 0.0;
            try {
                fahText.removeTextChangedListener(fahWatcher);
                if (s.length() != 0) {
                    centi = Double.parseDouble(s.toString());
                    fah = centi * 1.8 + 32;
                }
                fahText.setText(df.format(fah));
                Log.i("Main Activity", "Time: " + System.currentTimeMillis() + "\tConvert from " + centi + " ℃ to " + df.format(fah) + " ℉");
            }
            catch (NumberFormatException e) {
                fahText.setText("");
            }
            fahText.addTextChangedListener(fahWatcher);

        }

        @Override
        public void afterTextChanged(Editable s) { }
    };

    //F -> C
    private final TextWatcher fahWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s == "0.0") return;
            double centi = 0.0, fah = 0.0;
            try {
                centiText.removeTextChangedListener(centiWatcher);
                if (s.length() != 0) {
                    fah = Double.parseDouble(s.toString());
                    centi = (fah - 32) * 5 / 9;
                }
                centiText.setText(df.format(centi));
                Log.i("Main Activity", "Time: " + System.currentTimeMillis() + "\tConvert from " + fah + " ℉ to " + df.format(centi) + " ℃");
            }
            catch (NumberFormatException e) {
                centiText.setText("");
            }
            centiText.addTextChangedListener(centiWatcher);
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };

    //km -> mile
    private final TextWatcher kmWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s == "0.0") return;
            double km = 0.0, mile = 0.0;
            try {
                mileText.removeTextChangedListener(mileWatcher);
                if (s.length() != 0) {
                    km = Double.parseDouble(s.toString());
                    mile = km * 1.609344;
                }
                mileText.setText(df.format(mile));
                Log.i("Main Activity", "Time: " + System.currentTimeMillis() + "\tConvert from " + km + " km to " + df.format(mile) + " mile");
            }
            catch (NumberFormatException e) {
                mileText.setText("");
            }
            mileText.addTextChangedListener(mileWatcher);
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };

    //mile -> km
    private final TextWatcher mileWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s == "0.0") return;
            double km = 0.0, mile = 0.0;
            try {
                kmText.removeTextChangedListener(kmWatcher);
                if (s.length() != 0) {
                    mile = Double.parseDouble(s.toString());
                    km = mile * 0.621371;
                }
                kmText.setText(df.format(km));
                Log.i("Main Activity", "Time: " + System.currentTimeMillis() + "\tConvert from " + mile + " mile to " + df.format(km) + " km");
            }
            catch (NumberFormatException e) {
                kmText.setText("");
            }
            kmText.addTextChangedListener(kmWatcher);
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };

    //kg -> lb
    private final TextWatcher kgWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s == "0.0") return;
            double kg = 0.0, lb = 0.0;
            try {
                lbText.removeTextChangedListener(lbWatcher);
                if (s.length() != 0) {
                    kg = Double.parseDouble(s.toString());
                    lb = kg / 0.45359237;
                }
                lbText.setText(df.format(lb));
                Log.i("Main Activity", "Time: " + System.currentTimeMillis() + "\tConvert from " + kg + " kg to " + df.format(lb) + " lb");
            }
            catch (NumberFormatException e) {
                lbText.setText("");
            }
            lbText.addTextChangedListener(lbWatcher);
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };

    //lb -> kg
    private final TextWatcher lbWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s == "0.0") return;
            double kg = 0.0, lb = 0.0;
            try {
                kgText.removeTextChangedListener(kgWatcher);
                if (s.length() != 0) {
                    lb = Double.parseDouble(s.toString());
                    kg = lb * 2.20462262185;
                }
                kgText.setText(df.format(kg));
                Log.i("Main Activity", "Time: " + System.currentTimeMillis() + "\tConvert from " + lb + " lb to " + df.format(kg) + " kg");
            }
            catch (NumberFormatException e) {
                kgText.setText("");
            }
            kgText.addTextChangedListener(kgWatcher);
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };

    //liter -> gallon
    private final TextWatcher literWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s == "0.0") return;
            double liter = 0.0, gallon = 0.0;
            try {
                gallonText.removeTextChangedListener(gallonWatcher);
                if (s.length() != 0) {
                    liter = Double.parseDouble(s.toString());
                    gallon = liter * 3.78541178;
                }
                gallonText.setText(df.format(gallon));
                Log.i("Main Activity", "Time: " + System.currentTimeMillis() + "\tConvert from " + liter + " liter to " + df.format(gallon) + " gallon");
            }
            catch (NumberFormatException e) {
                gallonText.setText("");
            }
            gallonText.addTextChangedListener(gallonWatcher);
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };

    //gallon -> liter
    private final TextWatcher gallonWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s == "0.0") return;
            double liter = 0.0, gallon = 0.0;
            try {
                literText.removeTextChangedListener(literWatcher);
                if (s.length() != 0) {
                    gallon = Double.parseDouble(s.toString());
                    liter = gallon * 0.264172052;
                }
                literText.setText(df.format(liter));
                Log.i("Main Activity", "Time: " + System.currentTimeMillis() + "\tConvert from " + gallon + " gallon to " + df.format(liter) + " liter");
            }
            catch (NumberFormatException e) {
                literText.setText("");
            }
            literText.addTextChangedListener(literWatcher);
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };


}
