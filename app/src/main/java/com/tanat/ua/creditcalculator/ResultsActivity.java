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

    GridView gridView, gridView2;
    ArrayAdapter<String> adapter;
    String[] results;
    TextView textViewPriceCredit, textViewTotalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView2 = (GridView) findViewById(R.id.gridView2);

        textViewPriceCredit = (TextView) findViewById(R.id.textViewPriceCredit);
        textViewTotalAmount = (TextView) findViewById(R.id.textViewTotalAmount);

        Intent intent = getIntent();
        int type = intent.getIntExtra("type", 0);
        int amountCredit = intent.getIntExtra("amountCredit", 0);
        Double percent = intent.getDoubleExtra("percent", 0);
        int time = intent.getIntExtra("time", 0);

        switch (type){
            case (1):
                results = Calculate.calculate00(amountCredit, percent, time);
                break;
        }

        String[] title = {"Mount \n ", "Mount pay \n ","Commision", "Amount pay"};

        String[] data = new String[results.length - 2];
        System.arraycopy(results, 0, data, 0, results.length - 2);
        //System.arraycopy(results, 0, data, title.length, results.length);

        textViewPriceCredit.setText(results[results.length - 2]);
        textViewTotalAmount.setText(results[results.length - 1]);

        adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.tvText, title);
        gridView = (GridView) findViewById(R.id.gridView2);
        gridView.setAdapter(adapter);

        adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.tvText, data);
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(adapter);

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
        gridView2.setNumColumns(4);
        gridView2.setVerticalSpacing(5);
        gridView2.setHorizontalSpacing(5);

        gridView.setNumColumns(4);
        gridView.setVerticalSpacing(5);
        gridView.setHorizontalSpacing(5);
    }

    @Override
    public void onClick(View v) {

    }
}
