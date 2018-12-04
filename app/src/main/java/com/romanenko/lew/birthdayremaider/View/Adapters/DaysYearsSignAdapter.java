package com.romanenko.lew.birthdayremaider.View.Adapters;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.R;

public class DaysYearsSignAdapter {
    public static final String SPACE = " ";

    public static String adapterSignLeftDays(Context context, int countDays) {

        switch (countDays) {
            case 0: return context.getResources().getString(R.string.days_0);
            case 1: return den(context,countDays);
            case 21: return den(context,countDays);
            case 31: return den(context,countDays);
            case 41: return den(context,countDays);
            case 51: return den(context,countDays);
            case 61: return den(context,countDays);
            case 71: return den(context,countDays);
            case 81: return den(context,countDays);

            case 2: return dna(context,countDays);
            case 3: return dna(context,countDays);
            case 4: return dna(context,countDays);
            case 22: return dna(context,countDays);
            case 23: return dna(context,countDays);
            case 24: return dna(context,countDays);
            case 32: return dna(context,countDays);
            case 33: return dna(context,countDays);
            case 34: return dna(context,countDays);
            case 42: return dna(context,countDays);
            case 43: return dna(context,countDays);
            case 44: return dna(context,countDays);
            case 52: return dna(context,countDays);
            case 53: return dna(context,countDays);
            case 54: return dna(context,countDays);
            case 62: return dna(context,countDays);
            case 63: return dna(context,countDays);
            case 64: return dna(context,countDays);
            case 72: return dna(context,countDays);
            case 73: return dna(context,countDays);
            case 74: return dna(context,countDays);
            case 82: return dna(context,countDays);
            case 83: return dna(context,countDays);
            case 84: return dna(context,countDays);

            default: return dney(context,countDays);
        }

    }

    private static String den(Context context, int countDays){
        return  context.getResources().getString(R.string.left_1_day)
                + SPACE
                + countDays
                + SPACE
                + context.getResources().getString(R.string.days_1);
    }

    private static String dney(Context context, int countDays){
        return context.getResources().getString(R.string.left_number_days)
                + SPACE
                + countDays
                + SPACE
                + context.getResources().getString(R.string.days);
    }

    private static  String dna(Context context, int countDays){
      return   context.getResources().getString(R.string.left_number_days)
                + SPACE
                + countDays
                + SPACE
                + context.getResources().getString(R.string.days_2_3_4_44);
    }



    public static String adapterSignLeftYears(Context context, int countYears) {
        switch (countYears) {
            case 0: return context.getResources().getString(R.string.in_this_year);
            case 1: return god(context,countYears);
            case 21: return god(context,countYears);
            case 31: return god(context,countYears);
            case 41: return god(context,countYears);
            case 51: return god(context,countYears);
            case 61: return god(context,countYears);
            case 71: return god(context,countYears);
            case 81: return god(context,countYears);
            case 91: return god(context,countYears);
            case 101: return god(context,countYears);

            case 2: return goda(context,countYears);
            case 3: return goda(context,countYears);
            case 4: return goda(context,countYears);
            case 22: return goda(context,countYears);
            case 23: return goda(context,countYears);
            case 24: return goda(context,countYears);
            case 32: return goda(context,countYears);
            case 33: return goda(context,countYears);
            case 34: return goda(context,countYears);
            case 42: return goda(context,countYears);
            case 43: return goda(context,countYears);
            case 44: return goda(context,countYears);
            case 52: return goda(context,countYears);
            case 53: return goda(context,countYears);
            case 54: return goda(context,countYears);
            case 62: return goda(context,countYears);
            case 63: return goda(context,countYears);
            case 64: return goda(context,countYears);
            case 72: return goda(context,countYears);
            case 73: return goda(context,countYears);
            case 74: return goda(context,countYears);
            case 82: return goda(context,countYears);
            case 83: return goda(context,countYears);
            case 84: return goda(context,countYears);
            case 92: return goda(context,countYears);
            case 93: return goda(context,countYears);
            case 94: return goda(context,countYears);

            default: return let(context,countYears);
        }

    }

    private static String god (Context context, int countYears){
        return   countYears
                + SPACE
                + context.getResources().getString(R.string.year_1);
    }

    private static String goda(Context context, int countYears){
        return   countYears
                + SPACE
                + context.getResources().getString(R.string.year_2_3_4_44);
    }

    private static  String let (Context context, int countYears){
        return     countYears
                + SPACE
                + context.getResources().getString(R.string.years_other);
    }


}
