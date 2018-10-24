package com.romanenko.lew.birthdayremaider.View.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerMVPHomeScreen;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPMHomeScreen;
import com.romanenko.lew.birthdayremaider.HomeScreenContract;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.CelebrListNameDateFotoDTO;
import com.romanenko.lew.birthdayremaider.Model.ModelHomeScreen;
import com.romanenko.lew.birthdayremaider.Presenter.PresenterHomeScreen;
import com.romanenko.lew.birthdayremaider.R;
import com.romanenko.lew.birthdayremaider.View.Adapters.CelebrationAdapterGridView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragHomeScreen extends android.support.v4.app.Fragment implements HomeScreenContract.ViewCelebrations{

    @BindView(R.id.gridViewHomeScreen)
    GridView gridViewHomeScreen;

    @Inject
    HomeScreenContract.PresenterCelebrations presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_screen, null);
        ButterKnife.bind(this, view);

        DaggerMVPHomeScreen.builder()
                .mVPMHomeScreen(new MVPMHomeScreen(this, new PresenterHomeScreen(getContext())))
                .build()
                .inject(this);

        presenter.attachView(this);
        presenter.attachModel(new ModelHomeScreen());

        presenter.viewIsReady();
        presenter.loadCelebrations();

        return view;
    }

    @Override
    public void showCelebrations(List<CelebrListNameDateFotoDTO> datumCelebrationForLists) {
        CelebrationAdapterGridView celebrationAdapterGridView = new  CelebrationAdapterGridView(getContext(),datumCelebrationForLists);
        gridViewHomeScreen.setAdapter(celebrationAdapterGridView);
    }
}
