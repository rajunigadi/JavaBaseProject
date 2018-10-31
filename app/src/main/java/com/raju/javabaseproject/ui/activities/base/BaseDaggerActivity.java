package com.raju.javabaseproject.ui.activities.base;

import com.raju.javabaseproject.viewmodels.base.BaseViewModel;

import javax.inject.Inject;

public class BaseDaggerActivity<V extends BaseViewModel> extends BaseActivity {

    @Inject
    protected V viewModel;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(viewModel != null) {
            viewModel.destroy();
        }
    }
}

