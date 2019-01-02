package com.raju.javabaseproject.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.raju.javabaseproject.R;
import com.raju.javabaseproject.ui.activities.base.BaseActivity;
import com.raju.javabaseproject.ui.fragments.UserFragment;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Bundle bundle = new Bundle();
        Fragment fragment = UserFragment.newInstance();
        fragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }
}
