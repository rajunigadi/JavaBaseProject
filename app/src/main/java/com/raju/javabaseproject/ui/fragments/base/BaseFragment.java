package com.raju.javabaseproject.ui.fragments.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;



public abstract class BaseFragment extends Fragment {

    protected BaseFragment(int layoutId, String title) {
        this.layoutId = layoutId;
        this.title = title;
        hideKeyboard();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutId, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder !=null){
            unbinder.unbind();
            unbinder = null;
        }
    }

    protected void hideKeyboard() {
        if (getActivity() != null && getActivity().getCurrentFocus() != null && getActivity().getCurrentFocus().getWindowToken() != null) {
            InputMethodManager ime = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            ime.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    protected void showSnackBar(String message) {
        showSnackBar(message, false, null);
    }

    protected void showSnackBar(String message, boolean hasAction) {
        showSnackBar(message, hasAction, null);
    }

    protected void showSnackBar(String message, boolean isAction, String name) {
        if(getActivity() != null && getActivity().getWindow() != null && isAdded()) {
            View view = getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
            //final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
            if (view != null) {
                final Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
                if (isAction) {
                    snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
                    if (TextUtils.isEmpty(name)) name = "Dismiss";
                    snackbar.setAction(name, new View.OnClickListener() {
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
                if(getActivity() != null)
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        } else {
            if(getActivity() != null)
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }

    private Unbinder unbinder;
    private int layoutId;
    private String title;
    protected boolean isActivityResult = false;
}

