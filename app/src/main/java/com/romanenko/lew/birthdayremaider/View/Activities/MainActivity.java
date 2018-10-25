package com.romanenko.lew.birthdayremaider.View.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.MultiAutoCompleteTextView;


import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerMVPCompListCelebr;
import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerMVPHomeScreen;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPMHomeScreen;
import com.romanenko.lew.birthdayremaider.HomeScreenContract;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.CelebrListNameDateFotoDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;
import com.romanenko.lew.birthdayremaider.Model.ModelHomeScreen;
import com.romanenko.lew.birthdayremaider.Model.ModelListCelebration;
import com.romanenko.lew.birthdayremaider.Presenter.PresenterHomeScreen;
import com.romanenko.lew.birthdayremaider.R;
import com.romanenko.lew.birthdayremaider.View.Adapters.CelebrationAdapterGridView;
import com.romanenko.lew.birthdayremaider.View.Fragments.FragAddReminder;
import com.romanenko.lew.birthdayremaider.View.Fragments.FragHomeScreen;
import com.romanenko.lew.birthdayremaider.View.Fragments.FragListCelebration;
import com.romanenko.lew.birthdayremaider.View.Fragments.FragmentSettings;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lev- on 20.03.2018.
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_main);
        initInterfaceComponent();
    }


    public void initInterfaceComponent() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mNavigationView.setNavigationItemSelectedListener((MenuItem menuItem) -> {
            selectDrawerItem(menuItem);
            return true;
        });
        // mNavigationView.setCheckedItem(R.id.nav_home_screen);
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
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        //menuItem.setChecked(true);
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
                fragmentClass = FragAddReminder.class;
                startFragmentSeparated(fragment, fragmentClass);
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

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer

    }

    private void startFragmentSeparated(Fragment fragment, Class fragmentClass) {
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(R.id.content_frame_other_fragments,fragment, "lol")
                .addToBackStack(null)
                .commit();
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
