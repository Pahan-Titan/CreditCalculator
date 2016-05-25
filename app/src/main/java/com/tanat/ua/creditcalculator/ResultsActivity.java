package com.tanat.ua.creditcalculator;

import android.content.Intent;
import android.provider.Settings;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class ResultsActivity extends AppCompatActivity {

    GridView gridView, gridView2;
    ArrayAdapter<String> adapter;
    String[] results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView2 = (GridView) findViewById(R.id.gridView2);

        Intent intent = getIntent();
/*        int type = Integer.parseInt(intent.getStringExtra("type").toString());
        int amountCredit = Integer.parseInt(intent.getStringExtra("amountCredit").toString());
        Double percent = Double.parseDouble(intent.getStringExtra("percent").toString());
        int time = Integer.parseInt(intent.getStringExtra("time").toString());
*/
        int type = intent.getIntExtra("type", 0);
        int amountCredit = intent.getIntExtra("amountCredit", 0);
        Double percent = intent.getDoubleExtra("percent", 0);
        int time = intent.getIntExtra("time", 0);

        switch (type){
            case (1):
                results = Calculate.calculate00(amountCredit, percent, time);
                break;
        }

        String[] title = {"колонка 1", "колонка 2","колонка 3", "колонка 4"};

        String[] data = new String[results.length + title.length];
        System.arraycopy(title, 0, data, 0, title.length);
        System.arraycopy(results, 0, data, title.length, results.length);

        adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.tvText, title);
        gridView = (GridView) findViewById(R.id.gridView2);
        gridView.setAdapter(adapter);

        adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.tvText, results);
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(adapter);

        adjustGridView();
    }

    private void adjustGridView() {
        gridView2.setNumColumns(4);
        gridView2.setVerticalSpacing(5);
        gridView2.setHorizontalSpacing(5);
        gridView.setNumColumns(4);
        gridView.setVerticalSpacing(5);
        gridView.setHorizontalSpacing(5);
    }
}
