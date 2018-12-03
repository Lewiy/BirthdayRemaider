package com.romanenko.lew.birthdayremaider.View.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.romanenko.lew.birthdayremaider.BaseFragments;
import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerMVPCompAddRemain;
import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerMVPCompEditCelebr;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPMAddRemainder;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPMEditCelebration;
import com.romanenko.lew.birthdayremaider.EditProfileCelebration;
import com.romanenko.lew.birthdayremaider.Model.ModelAddRemainder;
import com.romanenko.lew.birthdayremaider.Model.ModelEditCelebration;
import com.romanenko.lew.birthdayremaider.Presenter.PresenterAddRemainder;
import com.romanenko.lew.birthdayremaider.Presenter.PresenterEditCelebration;
import com.romanenko.lew.birthdayremaider.R;
import com.romanenko.lew.birthdayremaider.View.Activities.MainActivity;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragEditCelebration extends android.support.v4.app.Fragment implements EditProfileCelebration.ViewEditCelebration {

    @BindView(R.id.frag_edit_celebr_name)
    TextView name;
    @BindView(R.id.frag_edit_celebr_type)
    TextView typeCelebration;
    //@BindView(R.id.frag_edit_celebr_time_to_alarm)
    //TextView timeToAlarm;
    @BindView(R.id.frag_edit_celebr_date)
    TextView date;
    @BindView(R.id.frag_edit_celebr_comment)
    TextView comment;
    @BindView(R.id.frag_edit_celebr_image)
    ImageView picture;
    @BindView(R.id.frag_edit_celebr_done)
    ImageButton imgButton;

    @BindView(R.id.frag_edit_celebr_mok)
    ImageView pictureMock;
    private static final int REQUEST_WEIGHT = 1;
    private static final int REQUEST_ANOTHER_ONE = 2;
    private int idUser;

    @Inject
    public EditProfileCelebration.PresenterEditCelebration presenter;
    private Bundle updatBundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_personal_page, null);
        ButterKnife.bind(this, view);
        updatBundle = new Bundle();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            idUser = bundle.getInt("idUser", -1);
        }

        DaggerMVPCompEditCelebr.builder()
                .mVPMEditCelebration(new MVPMEditCelebration(this, new PresenterEditCelebration(getActivity())))
                .build()
                .inject(this);

        presenter.attachView(this);
        presenter.attachModel(new ModelEditCelebration());

        presenter.viewIsReady();

        presenter.pullPersonalPage(idUser);
        return view;


    }

    @OnClick(R.id.frag_edit_celebr_delete_but)
    public void onClickDelete() {

        goToListCelebration();
        presenter.deleteCelebration();
    }

    @OnClick(R.id.frag_edit_celebr_edit_but)
    public void onClickEdit() {
        openFragAddRemainder();
    }

    @OnClick(R.id.frag_edit_celebr_done)
    public void onClickDone() {
        goToListCelebration();

    }


    private void goToListCelebration() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    public void openFragAddRemainder() {
        updatBundle.putInt("idUser", idUser);
        ((MainActivity) getActivity()).setFragment(BaseFragments.ADD_CELEBR_FRAGMENT, updatBundle);


        /*FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.content_frame_main_activity, new FragAddReminder())
                .addToBackStack(null)
                .commit();*/

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_WEIGHT:
                    //int weight = data.getIntExtra(WeightDialogFragment.TAG_WEIGHT_SELECTED, -1)
                    //используем полученные результаты
                    //...
                    break;
                case REQUEST_ANOTHER_ONE:
                    //...
                    break;
                //обработка других requestCode
            }

            presenter.pullPersonalPage(idUser);

        }
    }

    @Override
    public void setName(String name, String surName) {
        this.name.setText(name);
        updatBundle.putString(FragAddReminder.TAG_NAME, name);
        updatBundle.putString(FragAddReminder.TAG_SUR_NAME, surName);
    }

    @Override
    public void setTypeCelebration(String typeCelebration) {
        this.typeCelebration.setText(typeCelebration);
        updatBundle.putString(FragAddReminder.TAG_TYPE_CELEBR, typeCelebration);
    }

    @Override
    public void setComment(String comment) {
        this.comment.setText(comment);
        updatBundle.putString(FragAddReminder.TAG_COMMENT, comment);
    }

    @Override
    public void setDate(String date) {
        this.date.setText(date);
        updatBundle.putString(FragAddReminder.TAG_DATE, date);
    }

    @Override
    public void setTimeToAlarm(String timeToAlarm) {
        //  this.timeToAlarm.setText(timeToAlarm);

    }

    @Override
    public void setPictureContact(String path) {
        if (path != null) {
            pictureMock.setVisibility(View.GONE);
            imgButton.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_done_white_24dp));
            File f = new File(path);
            Drawable d = Drawable.createFromPath(f.getAbsolutePath());
            picture.setBackground(d);
            updatBundle.putString(FragAddReminder.TAG_PICTURE_CONTACT, path);
        }

    }

    @Override
    public void setIdUser(int userId) {
        updatBundle.putInt(FragAddReminder.TAG_USERID, userId);
    }

    @Override
    public void setIdDate(int dateId) {
        updatBundle.putInt(FragAddReminder.TAG_DATEID, dateId);
    }

    @Override
    public void showView(String error) {

    }
}
