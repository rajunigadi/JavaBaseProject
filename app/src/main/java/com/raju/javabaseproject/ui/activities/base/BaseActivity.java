package com.raju.javabaseproject.ui.activities.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.raju.javabaseproject.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Rajashekhar Vanahalli on 05/04/18.
 */

public class BaseActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    protected void showSnackBar(String message) {
        showSnackBar(message, false, null);
    }

    protected void showSnackBar(String message, boolean hasAction) {
        showSnackBar(message, hasAction, null);
    }

    protected void showSnackBar(String message, boolean isAction, String action) {
        View view = getWindow().getDecorView().findViewById(android.R.id.content);
        if(view != null) {
            final Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
            if(isAction) {
                snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
                if(TextUtils.isEmpty(action)) action = /*getResources().getString(R.string.dismiss)*/ "Dismiss";
                snackbar.setAction(action, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snackbar.dismiss();
                    }
                });
            }
            snackbar.setActionTextColor(Color.WHITE);
            View sbView = snackbar.getView();
            TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    protected void hideKeyboard() {
        if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
            InputMethodManager ime = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            ime.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
}
