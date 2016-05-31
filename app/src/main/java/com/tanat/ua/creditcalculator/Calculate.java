package com.tanat.ua.creditcalculator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

/**
 * Created by TaNaT on 23.05.2016.
 */
public class Calculate {

    static double mountPay;
    static double commision;
    static double amountMounthPay;
    static double priceCredit;
    static double totalAmount;
    static DecimalFormatSymbols s;
    static DecimalFormat f;

    public Calculate (){
        s = new DecimalFormatSymbols();
        s.setDecimalSeparator(',');
        f = new DecimalFormat("#,##0.00", s);
    }

    private static double Percent (int number, double percent){
        return number * 0.01 * percent;
    }

    public static String[] calculate00 (int amountCredit, double percent, int time){
        mountPay = amountCredit / time;                 //месячный платёж по кредиту
        commision = Percent(amountCredit, percent);     //коммисия в месяц
        amountMounthPay = mountPay + commision;         //сума месячного платежа
        priceCredit = commision * time;                 //переплата за весь период
        totalAmount = amountMounthPay * time;           //полная сума по выплат по кредиту

        ArrayList<String> array = new ArrayList<>();

        s = new DecimalFormatSymbols();
        s.setDecimalSeparator('.');
        f = new DecimalFormat("#,##0.00", s);

        String mountPaySt = f.format(mountPay);
        String commisionSt = f.format(commision);
        String amountMounthPaySt = f.format(amountMounthPay);

        for (int i = 1; i < time + 1; i++){
            array.add(Integer.toString(i));
            array.add(mountPaySt);
            array.add(commisionSt);
            array.add(amountMounthPaySt);
        }

        array.add(f.format(priceCredit));
        array.add(f.format(totalAmount));

        String[] results;
        results = array.toArray(new String[array.size()]);

        return results;
    }

    public static String[] calculate01 (int amountCredit, double percent, int time){
        mountPay = amountCredit / time;
        commision = Percent(amountCredit, percent) / 12;
        amountMounthPay = mountPay + commision;
        priceCredit = commision * time;
        totalAmount = amountMounthPay * time;

        ArrayList<String> array = new ArrayList<>();

        s = new DecimalFormatSymbols();
        s.setDecimalSeparator('.');
        f = new DecimalFormat("#,##0.00", s);

        String mountPaySt = f.format(mountPay);
        String commisionSt = f.format(commision);
        String amountMounthPaySt = f.format(amountMounthPay);

        for (int i = 1; i < time + 1; i++){
            array.add(Integer.toString(i));
            array.add(mountPaySt);
            array.add(commisionSt);
            array.add(amountMounthPaySt);
        }

        array.add(f.format(priceCredit));
        array.add(f.format(totalAmount));

        String[] results;
        results = array.toArray(new String[array.size()]);

        return results;
    }

    public static String[] resultsConversion (){
        String[] results = {Double.toString(mountPay), Double.toString(commision),
                Double.toString(amountMounthPay), Double.toString(priceCredit),
                Double.toString(totalAmount)};
        return results;
    }
}
