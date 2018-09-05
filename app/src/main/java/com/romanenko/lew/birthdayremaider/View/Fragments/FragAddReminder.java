package com.romanenko.lew.birthdayremaider.View.Fragments;

import android.app.Activity;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.romanenko.lew.birthdayremaider.R;
import com.romanenko.lew.birthdayremaider.View.Activities.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragAddReminder extends android.support.v4.app.DialogFragment {

  /*  @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_birthday, null);
        return view;
    }*/

    @BindView(R.id.frag_add_remainder_name)
    EditText Name;
    @BindView(R.id.frag_add_remainder_sur_name)
    EditText SurName;
    @BindView(R.id.frag_add_remainder_comment)
    EditText Comment;
    @BindView(R.id.frag_add_remainder_date_text)
    TextView dateView;
    @BindView(R.id.frag_add_remainder_date)
    ImageView datePicture;
    @BindView(R.id.frag_add_remainder_spin_type)
    Spinner spinTypeCelebr;
    //@BindView(R.id.frag_add_remainder_date_picker)
    //CalendarView fragAddRemaindDatePicker;

    Calendar dateAndTime = Calendar.getInstance();
    String dateCelebrate;

    //тэг для передачи результата обратно
    public static final String TAG_NAME = "name",
            TAG_SUR_NAME = "surname",
            TAG_COMMENT = "comment",
            TAG_TYPE_CELEBR = "typeCelebr",
            TAG_DATE = "date";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_remainder, null);

        init(view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent();
                        // intent.putExtra(TAG_WEIGHT_SELECTED, mNpWeight.getValue());
                        intent = loadDataIntent(intent);

                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);

                        //

                    }
                });
        // getDialog().getWindow().setLayout(100, 100);
        return builder.create();
    }

    public void init(View view) {
        ButterKnife.bind(this, view);
        //dateView.setVisibility(View.GONE);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.spin_type_celebr, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTypeCelebr.setAdapter(adapter);

    }

    private Intent loadDataIntent(Intent intent) {
        intent.putExtra(TAG_NAME, Name.getText().toString());
        intent.putExtra(TAG_SUR_NAME, SurName.getText().toString());
        intent.putExtra(TAG_COMMENT, Comment.getText().toString());
        intent.putExtra(TAG_TYPE_CELEBR, spinTypeCelebr.getSelectedItem().toString());
        intent.putExtra(TAG_DATE, dateCelebrate);
        //  intent.putExtra(TAG_DATE, dateCelebrate);
        return intent;
    }

    @OnClick(R.id.frag_add_set_date)
    public void OnClickDatePicker() {
        new DatePickerDialog(getContext(), d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            dateCelebrate = dayOfMonth + "/" + ++monthOfYear + "/" + year;
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            dateView.setText(dateCelebrate);
            datePicture.setVisibility(View.GONE);
            dateView.setVisibility(View.VISIBLE);

        }
    };


}
