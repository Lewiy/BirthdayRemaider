package com.romanenko.lew.birthdayremaider.View.validation;

import android.content.Context;
import android.content.res.Resources;
import android.widget.TextView;
import android.widget.Toast;

import com.romanenko.lew.birthdayremaider.R;

public class Validation {

    public static int EMPTY_STRING = 0;
    public static int OVERFLOW_STRING = 16;

    public static boolean checkEmptyField(Context context,TextView textView) {
        boolean valid = true;
        if (textView.getText().toString().length() == EMPTY_STRING) {
            valid = false;
            textView.setError(context.getResources().getString(R.string.strng_empty));
            return valid;
        }

        return valid;
    }

    public static boolean checkOverflowText(Context context,TextView textView) {
        boolean valid = true;
        if (textView.getText().toString().length() >= OVERFLOW_STRING) {
            valid = false;
            textView.setError(context.getResources().getString(R.string.string_overflow));
            return valid;
        }

        return valid;
    }


    public static boolean checkDateNotSet(Context context,TextView textView) {
         String message = context.getResources().getString(R.string.date_empty);
        boolean valid = true;
        if (textView.getText().toString().length() == EMPTY_STRING) {
            valid = false;
            Toast.makeText(context,message,Toast.LENGTH_LONG).show();
            return valid;
        }

        return valid;
    }

}
