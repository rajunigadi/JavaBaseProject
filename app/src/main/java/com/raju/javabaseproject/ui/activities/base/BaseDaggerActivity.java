package com.raju.javabaseproject.ui.activities.base;

import com.raju.javabaseproject.viewmodels.base.BaseViewModel;

import javax.inject.Inject;

public class BaseDaggerActivity<V extends BaseViewModel> extends BaseActivity {

    @Inject
    V viewModel;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(viewModel != null) {
            viewModel.destroy();
        }
    }
}
