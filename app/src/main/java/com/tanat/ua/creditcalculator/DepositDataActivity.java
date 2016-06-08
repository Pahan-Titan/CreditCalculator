package com.tanat.ua.creditcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

public class DepositDataActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout line;
    EditText editTextAmoutDeposit, editValuePercent, editTextTime;
    RadioButton radioButtonMounth, radioButtonYear, radioButtonYes, radioButtonNo,
            radioButtonEveryM, radioButtonEveryY;
    Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_data);

        editTextAmoutDeposit = (EditText) findViewById(R.id.editTextAmoutDeposit);
        editValuePercent = (EditText) findViewById(R.id.editValuePercent);
        editTextTime = (EditText) findViewById(R.id.editTextTime);

        line = (LinearLayout) findViewById(R.id.line);

        radioButtonMounth = (RadioButton) findViewById(R.id.radioButtonMounth);
        radioButtonYear = (RadioButton) findViewById(R.id.radioButtonYear);
        radioButtonYes = (RadioButton) findViewById(R.id.radioButtonYes);
        radioButtonNo = (RadioButton) findViewById(R.id.radioButtonNo);
        radioButtonEveryM = (RadioButton) findViewById(R.id.radioButtonEveryM);
        radioButtonEveryY = (RadioButton) findViewById(R.id.radioButtonEveryY);

        radioButtonYes.setOnClickListener(this);
        radioButtonNo.setOnClickListener(this);

        buttonCalculate = (Button) findViewById(R.id.buttonCalculate);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate();
            }
        });

        line.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_deposit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_clear:
                editTextAmoutDeposit.setText("");
                editValuePercent.setText("");
                editTextTime.setText("");
                return true;
            case R.id.action_action_credit:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        RadioButton rb = (RadioButton) v;
        switch (rb.getId()) {
            case R.id.radioButtonYes:
                line.setVisibility(View.VISIBLE);
                break;
            case R.id.radioButtonNo:
                line.setVisibility(View.INVISIBLE);
                break;
        }
    }

    public void Calculate(){
        int amountCredit = Integer.parseInt(editTextAmoutDeposit.getText().toString());
        double percent = Double.parseDouble(editValuePercent.getText().toString());
        int time = Integer.parseInt(editTextTime.getText().toString());

        Intent intent = new Intent(this, ResultsActivity.class);

        if (radioButtonMounth.isChecked() && radioButtonYes.isChecked()
                && radioButtonEveryM.isChecked())
            intent.putExtra("type", 1);                                 //000
        if (radioButtonYear.isChecked())
            intent.putExtra("type", 2);                                 //100
        if (radioButtonMounth.isChecked() && radioButtonNo.isChecked())
            intent.putExtra("type", 3);                                 //01x
        if (radioButtonYear.isChecked() && radioButtonNo.isChecked())
            intent.putExtra("type", 4);                                 //11x
        if (radioButtonYear.isChecked() && radioButtonYes.isChecked()
                && radioButtonEveryY.isChecked())
            intent.putExtra("type", 5);                                 //101
        if (radioButtonMounth.isChecked() && radioButtonYes.isChecked()
                && radioButtonEveryY.isChecked())
            intent.putExtra("type", 6);                                 //001

        intent.putExtra("amountMoney", amountCredit);
        intent.putExtra("procedure", "deposit");
        intent.putExtra("percent", percent);
        intent.putExtra("time", time);
        startActivity(intent);
    }
}
