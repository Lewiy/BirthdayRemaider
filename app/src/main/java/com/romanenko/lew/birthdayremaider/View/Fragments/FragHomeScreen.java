package com.romanenko.lew.birthdayremaider.View.Fragments;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerMVPHomeScreen;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPMHomeScreen;
import com.romanenko.lew.birthdayremaider.HomeScreenContract;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DTO.DateCelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.CelebrListNameDateFotoDTO;
import com.romanenko.lew.birthdayremaider.Model.ModelHomeScreen;
import com.romanenko.lew.birthdayremaider.Presenter.PresenterHomeScreen;
import com.romanenko.lew.birthdayremaider.R;
import com.romanenko.lew.birthdayremaider.View.Adapters.CelebrationAdapterGridView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragHomeScreen extends android.support.v4.app.Fragment implements HomeScreenContract.ViewCelebrations {

    @BindView(R.id.gridViewHomeScreen)
    GridView gridViewHomeScreen;
    @BindView(R.id.empty_grid_message)
    TextView emptyTextView;

    @Inject
    HomeScreenContract.PresenterCelebrations presenter;

    private static final int REQUEST_CODE_READ_CONTACTS = 1;
    private static boolean READ_CONTACTS_GRANTED = false;
    private int hasReadContactPermission;

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
        CelebrationAdapterGridView celebrationAdapterGridView = new CelebrationAdapterGridView(getContext(), datumCelebrationForLists);
        gridViewHomeScreen.setAdapter(celebrationAdapterGridView);
        isEmptyGridView(gridViewHomeScreen);
    }

    private void isEmptyGridView(GridView gridViewHomeScreen) {
        emptyTextView.setText(R.string.no_nearest_celebrations);
        gridViewHomeScreen.setEmptyView(emptyTextView);
    }

    @Override
    public void showView(String error) {

    }
}
