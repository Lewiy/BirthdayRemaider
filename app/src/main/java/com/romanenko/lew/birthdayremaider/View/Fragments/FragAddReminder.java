package com.romanenko.lew.birthdayremaider.View.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.romanenko.lew.birthdayremaider.R;

import butterknife.BindView;

public class FragAddReminder extends android.support.v4.app.DialogFragment {

  /*  @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_birthday, null);
        return view;
    }*/

    @BindView(R.id.frag_add_remainder_name)
    EditText fragAddRemainderDate;

   /* @BindView(R.id.frag_add_remainder_comment)
    EditText fragAddRemainderDate;
    @BindView(R.id.frag_add_remainder_sur_name)
    EditText fragAddRemainderDate;
    @BindView(R.id.frag_add_remainder_name)
    EditText fragAddRemainderDate;*/

    //тэг для передачи результата обратно
    public static final String TAG_WEIGHT_SELECTED = "weight";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_remainder,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent();
                       // intent.putExtra(TAG_WEIGHT_SELECTED, mNpWeight.getValue());
                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                    }
                });
       // getDialog().getWindow().setLayout(100, 100);
        return builder.create();
    }

}
