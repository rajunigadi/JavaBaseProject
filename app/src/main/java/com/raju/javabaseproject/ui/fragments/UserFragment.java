package com.raju.javabaseproject.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.raju.javabaseproject.R;
import com.raju.javabaseproject.mvp.model.ServerResponse;
import com.raju.javabaseproject.mvp.presenter.UserPresenter;
import com.raju.javabaseproject.mvp.view.IUsersView;
import com.raju.javabaseproject.ui.fragments.base.BaseFragment;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by Rajashekhar Vanahalli on 09/04/18.
 */
public class UserFragment extends BaseFragment<UserPresenter> implements IUsersView {

    public UserFragment() {
        super(R.layout.fragment_user, "User");
    }

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        /*if (presenter != null) {
            presenter.init(this);
            presenter.getHomeData();
        }*/
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.destroy();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void bindData(ServerResponse serverResponse) {

    }

    @Override
    public Context getContext() {
        return getActivity();
    }
}