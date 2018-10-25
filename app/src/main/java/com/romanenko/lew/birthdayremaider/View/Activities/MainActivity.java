package com.romanenko.lew.birthdayremaider.View.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.romanenko.lew.birthdayremaider.BaseFragments;
import com.romanenko.lew.birthdayremaider.R;
import com.romanenko.lew.birthdayremaider.View.Fragments.FragAddReminder;
import com.romanenko.lew.birthdayremaider.View.Fragments.FragNavDraw;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragment(BaseFragments.NAV_DRAW_FRAGMENT);
    }

    public void setFragment(BaseFragments baseFragments) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (baseFragments) {
            case NAV_DRAW_FRAGMENT:
                fragmentClass = FragNavDraw.class;
                replaceFragment(fragment, fragmentClass);
                break;
            case ADD_CELEBR_FRAGMENT:
                fragmentClass = FragAddReminder.class;
                replaceFragment(fragment, fragmentClass);
                break;
            default:
                fragmentClass = FragNavDraw.class;
                replaceFragment(fragment, fragmentClass);
        }
    }

    private void replaceFragment(Fragment fragment, Class fragmentClass) {
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.content_frame_main_activity, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        return false;
    }
}
