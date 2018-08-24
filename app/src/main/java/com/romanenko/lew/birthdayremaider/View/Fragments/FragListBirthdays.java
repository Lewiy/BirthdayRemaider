package com.romanenko.lew.birthdayremaider.View.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerMVPComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPModule;
import com.romanenko.lew.birthdayremaider.ListBirthdayContract;
import com.romanenko.lew.birthdayremaider.Model.PListBirthdayItem;
import com.romanenko.lew.birthdayremaider.Presenter.Presenter;
import com.romanenko.lew.birthdayremaider.Presenter.PresenterListBirthday;
import com.romanenko.lew.birthdayremaider.R;
import com.romanenko.lew.birthdayremaider.View.Adapters.BirthdayAdapterList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lev- on 21.03.2018.
 */

public class FragListBirthdays extends android.support.v4.app.Fragment implements ListBirthdayContract.ViewListBirthday{

    @BindView(R.id.lv_main)
    RecyclerView recyclerViewMain;
    @BindView(R.id.add_remind)
    FloatingActionButton mButAddRemind;


    @Inject
    Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_birthday, null);
        ButterKnife.bind(this, view);
        loadData();
     // presenter = new PresenterListBirthday(this);
        DaggerMVPComponent.builder()
                .mVPModule(new MVPModule(this,new PresenterListBirthday()))
                .build()
                .mvpinjectListBirthday(this);
        presenter.attachView(this);
        return view;
    }

    /*public void setPresenter( ListBirthdayContract.PresenterListBirthday presenter){
        this.presenter = presenter;
    }*/

    @OnClick(R.id.add_remind)
    public void onClickAddRemindBut(){
        loadData();
    }
    //TODO ArrayList Mock
    public void loadData() {

        List<PListBirthdayItem> items = new ArrayList<>();
        PListBirthdayItem item1 = new PListBirthdayItem();
        item1.setMainText("Romanenko Lev");
        PListBirthdayItem item2 = new PListBirthdayItem();
        item2.setMainText("Romanenko Lev");
        PListBirthdayItem item3 = new PListBirthdayItem();
        item3.setMainText("Romanenko Lev");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        BirthdayAdapterList birthdayAdapterList = new BirthdayAdapterList(this.getContext(), items);
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMain.setAdapter(birthdayAdapterList);
        recyclerViewMain.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
    }
}
