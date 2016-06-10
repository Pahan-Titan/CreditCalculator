package com.tanat.ua.creditcalculator;

import android.content.Intent;
import android.provider.Settings;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener{

    GridView gridViewData, gridViewTitle;
    ArrayAdapter<String> adapter;
    String[] results;
    TextView textViewProfit, textViewTotalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        gridViewData = (GridView) findViewById(R.id.gridView);
        gridViewTitle = (GridView) findViewById(R.id.gridView2);

        textViewProfit = (TextView) findViewById(R.id.textViewProfit);
        textViewTotalAmount = (TextView) findViewById(R.id.textViewTotalAmount);

        Intent intent = getIntent();
        String procedure = intent.getStringExtra("procedure");
        int type = intent.getIntExtra("type", 0);
        int amountMoney = intent.getIntExtra("amountMoney", 0);
        Double percent = intent.getDoubleExtra("percent", 0);
        int time = intent.getIntExtra("time", 0);

        String[] title = null;
        String[] titleCredit = {"Month \n ", "Month pay \n ","Commision", "Amount pay"};
        String[] titleDeposit = {"Month \n ", "Amount of deposit","Profit \n", "After a month"};
        String resume1 = null;
        String resume2 = null;
        String PriceCredit = "Оverpayment for the entire period: ";
        String TotalCost = "Total amount cost credit: ";
        String Profit = "Profit from the deposit: ";
        String TotalAmount = "Amount for closed deposit: ";

        if (procedure.equals("credit")){
            title = titleCredit;
            resume1 = PriceCredit;
            resume2 = TotalCost;
            switch (type){
                case (1):
                    results = Calculate.credit00(amountMoney, percent, time);
                    break;
                case (2):
                    results = Calculate.credit01(amountMoney, percent, time);
                    break;
            }
        }
        if (procedure.equals("deposit")){
            title = titleDeposit;
            resume1 = Profit;
            resume2 = TotalAmount;
            switch (type){
                case (1):
                    results = Calculate.deposit000(amountMoney, percent, time);
                    break;
                case (2):
                    results = Calculate.deposit100(amountMoney, percent, time);
                    break;
                case (3):
                    results = Calculate.deposit01(amountMoney, percent, time);
                    break;
                case (4):
                    results = Calculate.deposit11(amountMoney, percent, time);
                    break;
            }
        }


        String[] data = new String[results.length - 2];
        System.arraycopy(results, 0, data, 0, results.length - 2);
        //System.arraycopy(results, 0, data, title.length, results.length);

        textViewProfit.setText(resume1 + results[results.length - 2]);
        textViewTotalAmount.setText(resume2 + results[results.length - 1]);

        adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.tvText, title);
        gridViewTitle = (GridView) findViewById(R.id.gridView2);
        gridViewTitle.setAdapter(adapter);

        adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.tvText, data);
        gridViewData = (GridView) findViewById(R.id.gridView);
        gridViewData.setAdapter(adapter);

        adjustGridView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void adjustGridView() {
        gridViewTitle.setNumColumns(4);
        gridViewTitle.setVerticalSpacing(5);
        gridViewTitle.setHorizontalSpacing(5);

        gridViewData.setNumColumns(4);
        gridViewData.setVerticalSpacing(5);
        gridViewData.setHorizontalSpacing(5);
    }

    @Override
    public void onClick(View v) {

    }
}
