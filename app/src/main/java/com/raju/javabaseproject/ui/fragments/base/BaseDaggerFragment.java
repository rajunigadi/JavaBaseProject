package com.raju.javabaseproject.ui.fragments.base;

import android.annotation.SuppressLint;

import com.raju.javabaseproject.viewmodels.base.BaseViewModel;

import javax.inject.Inject;

@SuppressLint("ValidFragment")
public class BaseDaggerFragment<V extends BaseViewModel> extends BaseFragment {

    @Inject
    protected V viewModel;

    protected BaseDaggerFragment(int layoutId, String title) {
        super(layoutId, title);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(viewModel != null) {
            viewModel.destroy();
        }
    }
}
