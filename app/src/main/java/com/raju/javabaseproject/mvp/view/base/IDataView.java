package com.raju.javabaseproject.mvp.view.base;

import android.content.Context;

/**
 * Created by Rajashekhar Vanahalli on 05/04/18.
 */

public interface IDataView {
    void showLoading();

    void hideLoading();

    void showError(String message);

    Context getContext();
}