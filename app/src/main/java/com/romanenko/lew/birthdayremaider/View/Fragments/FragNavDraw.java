package com.romanenko.lew.birthdayremaider.View.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.romanenko.lew.birthdayremaider.BaseFragments;
import com.romanenko.lew.birthdayremaider.R;
import com.romanenko.lew.birthdayremaider.View.Activities.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lev- on 20.03.2018.
 */

public class FragNavDraw extends android.support.v4.app.Fragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;


    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //  super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.navigation_drawer_main, null);
        initInterfaceComponent(view);

        return view;
    }


    public void initInterfaceComponent(View view) {

        ButterKnife.bind(this, view);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

        ActionBar actionbar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        actionbar.setDisplayHomeAsUpEnabled(true);

        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mNavigationView.setNavigationItemSelectedListener((MenuItem menuItem) -> {
            selectDrawerItem(menuItem);
            return true;
        });
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        Fragment fragment = null;
        Class fragmentClass = FragHomeScreen.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

    }

    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {

            case R.id.nav_home_screen:
                fragmentClass = FragHomeScreen.class;
                startFragmentNavDrawMenu(fragment, fragmentClass, menuItem);
                break;
            case R.id.nav_add_celebration:
                ((MainActivity) getActivity()).setFragment(BaseFragments.ADD_CELEBR_FRAGMENT, new Bundle());
                break;
            case R.id.nav_list_birthday:
                fragmentClass = FragListCelebration.class;
                startFragmentNavDrawMenu(fragment, fragmentClass, menuItem);
                break;
            case R.id.nav_settings:
                fragmentClass = FragmentSettings.class;
                startFragmentNavDrawMenu(fragment, fragmentClass, menuItem);
                break;
            default:
                fragmentClass = FragHomeScreen.class;
                startFragmentNavDrawMenu(fragment, fragmentClass, menuItem);
        }

        mDrawerLayout.closeDrawers();
    }

    private void startFragmentNavDrawMenu(Fragment fragment, Class fragmentClass, MenuItem menuItem) {
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }


        FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();


        menuItem.setChecked(true);

        mToolbar.setTitle(menuItem.getTitle());


    }

    private void startFragmentSeparated(Fragment fragment, Class fragmentClass) {
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(R.id.content_frame_main_activity, fragment, "lol")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
