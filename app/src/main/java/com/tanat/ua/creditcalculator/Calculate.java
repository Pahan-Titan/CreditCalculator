package com.tanat.ua.creditcalculator;

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

        String mountPaySt = Double.toString(mountPay);
        String commisionSt = Double.toString(commision);
        String amountMounthPaySt = Double.toString(amountMounthPay);

        for (int i = 1; i < time + 1; i++){
            array.add(Integer.toString(i));
            array.add(mountPaySt);
            array.add(commisionSt);
            array.add(amountMounthPaySt);
        }

        array.add(Double.toString(priceCredit));
        array.add(Double.toString(totalAmount));

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
