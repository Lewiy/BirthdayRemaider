package com.romanenko.lew.birthdayremaider.View.validation;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

public abstract class ValidationWatcher implements TextWatcher {
    private final TextView textView;

    public ValidationWatcher(TextView textView) {
        this.textView = textView;
    }

   // public abstract void validate(TextView textView, String text);

    /*public void validate(TextView textView, String text){
        if(text.length() == 0)
            textView.setError();
        if(text.length() >= 15)
            textView.setError();
    }*/


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String text = textView.getText().toString();
       // validate(textView, text);
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
