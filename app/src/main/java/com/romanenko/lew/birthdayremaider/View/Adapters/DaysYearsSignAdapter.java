package com.romanenko.lew.birthdayremaider.View.Adapters;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.R;

public class DaysYearsSignAdapter {
    public static final String SPACE = " ";

    public static String adapterSignLeftDays(Context context, int countDays) {

        switch (countDays) {
            case 0:
                return context.getResources().getString(R.string.days_0);
            case 1:
                return context.getResources().getString(R.string.left_1_day)
                        + SPACE
                        + countDays
                        + SPACE
                        + context.getResources().getString(R.string.days_1);
            case 2:
                return context.getResources().getString(R.string.left_number_days)
                        + SPACE
                        + countDays
                        + SPACE
                        + context.getResources().getString(R.string.days_2_3_4_44);
            case 3:
                return context.getResources().getString(R.string.left_number_days)
                        + SPACE
                        + countDays
                        + SPACE
                        + context.getResources().getString(R.string.days_2_3_4_44);
            case 4:
                return context.getResources().getString(R.string.left_number_days)
                        + SPACE
                        + countDays
                        + SPACE
                        + context.getResources().getString(R.string.days_2_3_4_44);
            case 44:
                return context.getResources().getString(R.string.left_number_days)
                        + SPACE
                        + countDays
                        + SPACE
                        + context.getResources().getString(R.string.days_2_3_4_44);
        }
        return context.getResources().getString(R.string.left_number_days)
                + SPACE
                + countDays
                + SPACE
                + context.getResources().getString(R.string.days);
    }

    public static String adapterSignLeftYears(Context context, int countYears) {
        switch (countYears) {
            case 0:
                return context.getResources().getString(R.string.in_this_year);
            case 1:
                return
                        countYears
                        + SPACE
                        + context.getResources().getString(R.string.year_1);
            case 2:
                return
                         countYears
                        + SPACE
                        + context.getResources().getString(R.string.year_2_3_4_44);
            case 3:
                return
                         countYears
                        + SPACE
                        + context.getResources().getString(R.string.year_2_3_4_44);
            case 4:
                return
                         countYears
                        + SPACE
                        + context.getResources().getString(R.string.year_2_3_4_44);
            case 44:
                return
                         countYears
                        + SPACE
                        + context.getResources().getString(R.string.year_2_3_4_44);
        }
        return
                 countYears
                + SPACE
                + context.getResources().getString(R.string.years_other);
    }


}
