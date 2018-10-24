package com.romanenko.lew.birthdayremaider.View.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerMVPCompListCelebr;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPMListCelebration;
import com.romanenko.lew.birthdayremaider.ListCelebrationContract;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.ModelListCelebration;
import com.romanenko.lew.birthdayremaider.Presenter.PresenterListCelebration;
import com.romanenko.lew.birthdayremaider.R;
import com.romanenko.lew.birthdayremaider.View.Adapters.CelebrationAdapterList;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lev- on 21.03.2018.
 */

public class FragListCelebration extends android.support.v4.app.Fragment implements ListCelebrationContract.ViewListBirthday {

    @BindView(R.id.lv_main)
    RecyclerView recyclerViewMain;
    private static final int REQUEST_ADD_REMAINDER = 1;
    // private static final int REQUEST_ANOTHER_ONE = 2;

    String name, surName, comment, type_celebr, date;

    @Inject
    ListCelebrationContract.PresenterListBirthday presenter;

    private CelebrationVO celebrationVO1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_birthday, null);
        ButterKnife.bind(this, view);

        // presenter = new PresenterListCelebration(this);
        DaggerMVPCompListCelebr.builder()
                .mVPMListCelebration(new MVPMListCelebration(this, new PresenterListCelebration(getContext())))
                .build()
                .inject(this);

        presenter.attachView(this);
        presenter.attachModel(new ModelListCelebration());

        presenter.viewIsReady();

        loadData();
        return view;
    }

    /*public void setPresenter( ListCelebrationContract.PresenterListCelebration presenter){
        this.presenter = presenter;
    }*/

    @OnClick(R.id.add_remind)
    public void onClickAddRemindBut() {
        openFragAddRemainder();
    }

    //TODO ArrayList Mock
    public void loadData() {
        presenter.pullListCelebration();
    }

    public void openFragAddRemainder() {
        DialogFragment fragment = new FragAddReminder();
        fragment.setTargetFragment(this, REQUEST_ADD_REMAINDER);
        Bundle bundle = new Bundle();
        if(celebrationVO1 != null)
        bundle.putInt("idUser",(int)celebrationVO1.getIdUser());
        fragment.setArguments(bundle);
        fragment.show(getFragmentManager(), fragment.getClass().getName());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_ADD_REMAINDER:
                    // int weight = data.getIntExtra(FragAddReminder.TAG_WEIGHT_SELECTED, -1);
                    //используем полученные результаты
                    //.

                    String name = data.getStringExtra(FragAddReminder.TAG_NAME);
                    String surName = data.getStringExtra(FragAddReminder.TAG_SUR_NAME);
                    String comment = data.getStringExtra(FragAddReminder.TAG_COMMENT);
                    String type_celebr = data.getStringExtra(FragAddReminder.TAG_TYPE_CELEBR);
                    String date = data.getStringExtra(FragAddReminder.TAG_DATE);
                    String pathPictureContact = data.getStringExtra(FragAddReminder.TAG_PICTURE_CONTACT);
                    //openFragAddRemainder();
                     addRemainder(name, surName, comment, type_celebr, date,pathPictureContact);
                    break;

                //обработка других requestCode
            }

        }

    }


    @Override
    public void loadListCelebration( List<CelebrationVO> items) {

        CelebrationAdapterList.RecyclerViewClickListener listener = new CelebrationAdapterList.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position,CelebrationVO celebrationVO) {
                Fragment fragment  = null;
                try {
                    fragment = FragEditCelebration.class.newInstance();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                Bundle bundle = new Bundle();
                bundle.putInt("idUser",(int)celebrationVO.getIdUser());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            }
        };

        if(celebrationVO1 != null)
        celebrationVO1 = items.get(items.size() - 1);

        CelebrationAdapterList celebrationAdapterList = new CelebrationAdapterList(this.getContext(), items,listener);
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMain.setAdapter(celebrationAdapterList);
        recyclerViewMain.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
    }

    public void addRemainder(String name, String serName, String comment, String date, String typeCelebration,String pathPictureContact) {
        // presenter.addRemainder(name, surName, comment, type_celebr, date);
       // presenter.addRemainder(name, serName, comment, date, typeCelebration,pathPictureContact);
    }

    public void setNameList() {

    }

    public void setSurName() {

    }

    public void setDate() {

    }

    public void setFoto() {

    }


}
