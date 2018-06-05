package com.romanenko.lew.birthdayremaider.View.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.romanenko.lew.birthdayremaider.Model.POJO.PListBirthdayItem;
import com.romanenko.lew.birthdayremaider.R;
import com.romanenko.lew.birthdayremaider.View.Adapters.BirthdayAdapterList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lev- on 21.03.2018.
 */

public class FragListBirthdays extends android.support.v4.app.Fragment {

    @BindView(R.id.lv_main)
    RecyclerView recyclerViewMain;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_birthday, null);
        ButterKnife.bind(this, view);
        loadData();
        return view;
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
