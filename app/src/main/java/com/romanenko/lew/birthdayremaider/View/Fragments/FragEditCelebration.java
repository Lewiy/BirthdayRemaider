package com.romanenko.lew.birthdayremaider.View.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
    @BindView(R.id.frag_edit_celebr_time_to_alarm)
    TextView timeToAlarm;
    @BindView(R.id.frag_edit_celebr_date)
    TextView date;
    @BindView(R.id.frag_edit_celebr_comment)
    TextView comment;
    @BindView(R.id.frag_edit_celebr_image)
    ImageView picture;

    private int idUser;

    @Inject
    public EditProfileCelebration.PresenterEditCelebration presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_personal_page, null);
        ButterKnife.bind(this, view);

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

        presenter.deleteCelebration();
    }

    @OnClick(R.id.frag_edit_celebr_edit_but)
    public void onClickEdit() {
        openFragAddRemainder();
    }

    public void openFragAddRemainder() {
        DialogFragment fragment = new FragAddReminder();
        // fragment.setTargetFragment(this, REQUEST_ADD_REMAINDER);
        fragment.show(getFragmentManager(), fragment.getClass().getName());
    }

    @Override
    public void setName(String name) {
        this.name.setText(name);
    }

    @Override
    public void setTypeCelebration(String typeCelebration) {
        this.typeCelebration.setText(typeCelebration);
    }

    @Override
    public void setComment(String comment) {
        this.comment.setText(comment);
    }

    @Override
    public void setDate(String date) {
        this.date.setText(date);
    }

    @Override
    public void setTimeToAlarm(String timeToAlarm) {
        this.timeToAlarm.setText(timeToAlarm);
    }

    @Override
    public void setPictureContact(String path) {
        File f = new File(path);
        Drawable d = Drawable.createFromPath(f.getAbsolutePath());
       picture.setBackground(d);
    }
}
