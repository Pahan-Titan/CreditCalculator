package com.tanat.ua.creditcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editAmountCredit, editValuePercent, editQuMounth;
    RadioButton radioButtonMounth, radioButtonYear, radioButtonAmount, radioButtonBalance;
    Button buttonCalculate;
    String[] results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAmountCredit = (EditText) findViewById(R.id.editAmountCredit);
        editValuePercent = (EditText) findViewById(R.id.editValuePercent);
        editQuMounth = (EditText) findViewById(R.id.editQuMounth);

        radioButtonMounth = (RadioButton) findViewById(R.id.radioButtonMounth);
        radioButtonYear = (RadioButton) findViewById(R.id.radioButtonYear);
        radioButtonAmount = (RadioButton) findViewById(R.id.radioButtonAmount);
        radioButtonBalance = (RadioButton) findViewById(R.id.radioButtonBalance);

        buttonCalculate = (Button) findViewById(R.id.buttonCalculate);
        buttonCalculate.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
    //            infoTextView.setText("Вы выбрали кота!");
                return true;
            case R.id.action_clear:
    //            infoTextView.setText("Вы выбрали кошку!");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onClick(View v) {
    /*    String amountCredit = editAmountCredit.getText().toString();
        String percent = editValuePercent.getText().toString();
        String time = editQuMounth.getText().toString();
*/
        int amountCredit = Integer.parseInt(editAmountCredit.getText().toString());
        double percent = Double.parseDouble(editValuePercent.getText().toString());
        int time = Integer.parseInt(editQuMounth.getText().toString());

        Intent intent = new Intent(this, ResultsActivity.class);

        if (radioButtonMounth.isChecked() || radioButtonAmount.isChecked()){
            intent.putExtra("type", 1);
        }

        intent.putExtra("amountCredit", amountCredit);
        intent.putExtra("percent", percent);
        intent.putExtra("time", time);
        startActivity(intent);
    }
}