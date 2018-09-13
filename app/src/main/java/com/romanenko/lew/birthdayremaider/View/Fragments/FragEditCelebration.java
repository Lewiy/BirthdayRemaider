package com.romanenko.lew.birthdayremaider.View.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.romanenko.lew.birthdayremaider.R;

import butterknife.BindView;
import butterknife.OnClick;

public class FragEditCelebration extends android.support.v4.app.Fragment {


    @BindView(R.id.frag_edit_celebr_name)
    EditText name;
    @BindView(R.id.frag_edit_celebr_type)
    EditText type;
    @BindView(R.id.frag_edit_celebr_time_to_alarm)
    EditText timeToAlarm;
    @BindView(R.id.frag_edit_celebr_date)
    EditText date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_personal_page, null);
        return view;


    }

    @OnClick(R.id.frag_edit_celebr_delete_but)
    public void onClickDelete() {

    }

    @OnClick(R.id.frag_edit_celebr_edit_but)
    public void onClickEdit() {

    }

}
