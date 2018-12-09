package com.romanenko.lew.birthdayremaider.View.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    @BindView(R.id.frag_edit_celebr_date)
    TextView date;
    @BindView(R.id.frag_edit_celebr_comment)
    TextView comment;
    @BindView(R.id.frag_edit_celebr_image)
    ImageView picture;
    @BindView(R.id.frag_edit_celebr_done_but)
    ImageButton doneBut;
   @BindView(R.id.picture_container)
    RelativeLayout pictureContainer;

    @BindView(R.id.frag_edit_celebr_mok)
    ImageView pictureMock;
    private static final int REQUEST_WEIGHT = 1;
    private static final int REQUEST_ANOTHER_ONE = 2;
    private int idUser;
    private int   heightImageContact,  weightImageContact;

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


        ViewTreeObserver vto = picture.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                picture.getViewTreeObserver().removeOnPreDrawListener(this);
                heightImageContact = picture.getMeasuredHeight();
                weightImageContact = picture.getMeasuredWidth();
                return true;
            }
        });


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

    @OnClick(R.id.frag_edit_celebr_done_but)
    public void onClickDone() {
        goToListCelebration();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    private void goToListCelebration() {
        ((MainActivity) getActivity()).setFragment(BaseFragments.NAV_DRAW_FRAGMENT, null);
    }

    public void openFragAddRemainder() {
        updatBundle.putInt("idUser", idUser);
        ((MainActivity) getActivity()).setFragment(BaseFragments.ADD_CELEBR_FRAGMENT, updatBundle);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_WEIGHT:
                    //...
                    break;
                case REQUEST_ANOTHER_ONE:
                    //...
                    break;
            }

            presenter.pullPersonalPage(idUser);

        }
    }

    @Override
    public void setName(String name, String surName) {
        this.name.setText(name +" "+surName);
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
           picture.setImageURI(Uri.fromFile(new File(path)));
           updatBundle.putString(FragAddReminder.TAG_PICTURE_CONTACT, path);
           pictureContainer.setBackgroundColor(Color.TRANSPARENT);
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
