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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerMVPHomeScreen;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPMHomeScreen;
import com.romanenko.lew.birthdayremaider.HomeScreenContract;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DTO.DateCelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DTO.HomeCelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.CelebrListNameDateFotoDTO;
import com.romanenko.lew.birthdayremaider.Model.ModelHomeScreen;
import com.romanenko.lew.birthdayremaider.Notification.DataNotifReceiver;
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

    private CelebrationAdapterGridView celebrationAdapterGridView;

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

       /* DataNotifReceiver dataNotifReceiver = new DataNotifReceiver(getActivity());
        dataNotifReceiver.runNotification();*/

        celebrationAdapterGridView = new CelebrationAdapterGridView(getContext());
        gridViewHomeScreen.setAdapter(celebrationAdapterGridView);

        onClickGridView(gridViewHomeScreen);
        return view;
    }

    @Override
    public void showCelebrations(List<CelebrListNameDateFotoDTO> datumCelebrationForLists) {

    }

    @Override
    public void showItemCelebrHome(HomeCelebrationVO homeCelebrationVO) {
        celebrationAdapterGridView.addItem(homeCelebrationVO);
        emptyTextView.setVisibility(View.GONE);
    }


    @Override
    public void showView(String error) {

    }

    private void onClickGridView(GridView gridViewHomeScreen) {
        gridViewHomeScreen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HomeCelebrationVO homeCelebrationVO = celebrationAdapterGridView.getItem(i);

                Fragment fragment = null;
                try {
                    fragment = FragEditCelebration.class.newInstance();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                Bundle bundle = new Bundle();
                bundle.putInt("idUser", (int) homeCelebrationVO.getId());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.content_frame, fragment, FragEditCelebration.class.toString())
                        .addToBackStack(FragEditCelebration.class.toString())
                        .commit();
            }
        });
    }

}
