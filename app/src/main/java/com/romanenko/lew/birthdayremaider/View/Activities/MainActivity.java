package com.romanenko.lew.birthdayremaider.View.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;

import com.romanenko.lew.birthdayremaider.BaseFragments;
import com.romanenko.lew.birthdayremaider.R;
import com.romanenko.lew.birthdayremaider.View.Fragments.FragAddReminder;
import com.romanenko.lew.birthdayremaider.View.Fragments.FragEditCelebration;
import com.romanenko.lew.birthdayremaider.View.Fragments.FragNavDraw;


public class MainActivity extends AppCompatActivity {


    //private Bundle bundleInform;
    private int dfg = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundleInform = getIntent().getExtras();
        String typeFragment = null;

        if (bundleInform != null)
            typeFragment = bundleInform.getString("FragmentType", null);

        startFragment(typeFragment, bundleInform);
    }


    private void startFragment(String fragmentStr, Bundle bundleInform) {

        if (fragmentStr != null) {
            if (fragmentStr.matches("EditFragment")) {
                setFragment(BaseFragments.EDIT_CELEBR_FRAGMENT, bundleInform);
            }

            if (fragmentStr.matches("EditFragmentFromNotif")) {
                startFirstFragment(bundleInform);
            }
        } else setFragment(BaseFragments.NAV_DRAW_FRAGMENT, bundleInform);


    }

    private void startFirstFragment( Bundle bundleInform) {
        Fragment fragment = null;
       Class fragmentClass = FragEditCelebration.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }


        fragment.setArguments(bundleInform);


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.content_frame_main_activity, fragment, fragmentClass.toString())
                .commit();
    }


    public void setFragment(BaseFragments baseFragments, Bundle bundleInform) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (baseFragments) {
            case NAV_DRAW_FRAGMENT:
                fragmentClass = FragNavDraw.class;
                replaceFragment(fragment, fragmentClass, bundleInform);
                break;
            case ADD_CELEBR_FRAGMENT:
                fragmentClass = FragAddReminder.class;
                replaceFragment(fragment, fragmentClass, bundleInform);
                break;
            case EDIT_CELEBR_FRAGMENT:
                fragmentClass = FragEditCelebration.class;
                replaceFragment(fragment, fragmentClass, bundleInform);
                break;
            default:
                fragmentClass = FragNavDraw.class;
                replaceFragment(fragment, fragmentClass, bundleInform);
        }
    }

    private void replaceFragment(Fragment fragment, Class fragmentClass, Bundle bundleInform) {
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //String str = fragmentClass.toString();

        fragment.setArguments(bundleInform);


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.content_frame_main_activity, fragment, fragmentClass.toString())
                .addToBackStack(fragmentClass.toString())
                .commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        return false;
    }
}
