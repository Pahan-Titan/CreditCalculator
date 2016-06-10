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
    static double[] profitFor;
    static DecimalFormatSymbols s;
    static DecimalFormat f;

    public Calculate (){
    }

    private static double Percent (double number, double percent){
        return number * 0.01 * percent;
    }

    public static String[] credit00 (int amountCredit, double percent, int time){
        mountPay = amountCredit / time;                 //месячный платёж по кредиту
        commision = Percent(amountCredit, percent);     //коммисия в месяц
        amountMounthPay = mountPay + commision;         //сума месячного платежа
        priceCredit = commision * time;                 //переплата за весь период
        totalAmount = amountMounthPay * time;           //полная сума по выплат по кредиту

        String[] results;
        results = resultsConversion(time);

        return results;
    }

    public static String[] credit01 (int amountCredit, double percent, int time){
        mountPay = amountCredit / time;
        commision = Percent(amountCredit, percent) / 12;
        amountMounthPay = mountPay + commision;
        priceCredit = commision * time;
        totalAmount = amountMounthPay * time;

  /*      ArrayList<String> array = new ArrayList<>();

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
        results = array.toArray(new String[array.size()]);*/

        String[] results;
        results = resultsConversion(time);

        return results;
    }

    public static String[] deposit000(int amountDeposit, double percent, int time){
        mountPay = amountDeposit;
        commision = Percent(amountDeposit, percent);
        amountMounthPay = mountPay + commision;
        priceCredit = commision * time;                 //сумма заработаная за время кедита
        totalAmount = amountMounthPay * time;           //будет получено в конце срока

        String[] results;
        results = resultsConversion(time);

        return results;
    }

    public static String[] deposit100 (int amountDeposit, double percent, int time){
        mountPay = amountDeposit;
        commision = Percent(amountDeposit, percent) / 12;
        amountMounthPay = mountPay + commision;
        priceCredit = commision * time;                 //сумма заработаная за время кедита
        totalAmount = amountMounthPay * time;           //будет получено в конце срока

        String[] results;
        results = resultsConversion(time);

        return results;
    }

    public static String[] deposit01 (int amountDeposit, double percent, int time){
        double[] commisionM = new double[time+1];
        double[] afterMonth = new double[time+1];

        commisionM[0] = Percent(amountDeposit, percent);
        afterMonth[0] = amountDeposit + commisionM[0];
        priceCredit = commisionM[0];

        for (int i = 1; i < time+1; i++){
            commisionM[i] = Percent(afterMonth[i-1], percent);
            afterMonth[i] = afterMonth[i-1] + commisionM[i];
            priceCredit = priceCredit + commisionM[i];                 //сумма заработаная за время кедита
        }

        totalAmount = amountDeposit + priceCredit;           //будет получено в конце срока

        String[] results;
        results = compoundInterest(time, commisionM, afterMonth);

        return results;
    }

    public static String[] deposit11 (int amountDeposit, double percent, int time){
        double[] commisionM = new double[time+1];
        double[] afterMonth = new double[time+1];

        commisionM[0] = Percent(amountDeposit, percent/12);
        afterMonth[0] = amountDeposit + commisionM[0];
        priceCredit = commisionM[0];

        for (int i = 1; i < time+1; i++){
            commisionM[i] = Percent(afterMonth[i-1], percent/12);
            afterMonth[i] = afterMonth[i-1] + commisionM[i];
            priceCredit = priceCredit + commisionM[i];                 //сумма заработаная за время кедита
        }

        totalAmount = amountDeposit + priceCredit;           //будет получено в конце срока

        String[] results;
        results = compoundInterest(time, commisionM, afterMonth);

        return results;
    }

 /*   public static String[] deposit101 (int amountDeposit, double percent, int time){
        double[] commisionM = new double[time+1];
        double[] afterMonth = new double[time+1];

        commisionM[0] = Percent(amountDeposit, percent/12);
        afterMonth[0] = amountDeposit + commisionM[0];
        priceCredit = commisionM[0];

        for (int i = 1; i < time+1; i++){
            commisionM[i] = Percent(afterMonth[i-1], percent/12);
            afterMonth[i] = afterMonth[i-1] + commisionM[i];
            priceCredit = priceCredit + commisionM[i];                 //сумма заработаная за время кедита
        }

        int i = 1;
        do{
            if (i > 12 && (i-1)%12 == 0){
                afterMonth[i] = amountDeposit + commisionM[0];
                profitFor[(i-1)/12] = priceCredit;
            }
            else {
                commisionM[i] = Percent(afterMonth[i - 1], percent / 12);
                afterMonth[i] = afterMonth[i - 1] + commisionM[i];
            }
            priceCredit = priceCredit + commisionM[i];                 //сумма заработаная за время кедита
            i++;
        } while (i < time);

        totalAmount = amountDeposit + priceCredit;           //будет получено в конце срока

        String[] results;
        results = compoundInterest(time, commisionM, afterMonth);

        return results;
    }*/

    private static String[] resultsConversion (int time){
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

    private static String[] compoundInterest(int time, double[] commisionM, double[] afterMonth){
        ArrayList<String> array = new ArrayList<>();

        s = new DecimalFormatSymbols();
        s.setDecimalSeparator('.');
        f = new DecimalFormat("#,##0.00", s);

        for (int i = 1; i < time + 1; i++){
            array.add(Integer.toString(i));
            array.add(f.format(afterMonth[i-1]));
            array.add(f.format(commisionM[i - 1]));
            array.add(f.format(afterMonth[i]));
        }

        array.add(f.format(priceCredit));
        array.add(f.format(totalAmount));

        String[] results;
        results = array.toArray(new String[array.size()]);
        return results;
    }
}

