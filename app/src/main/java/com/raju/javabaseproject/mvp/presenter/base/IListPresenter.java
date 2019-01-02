package com.raju.javabaseproject.mvp.presenter.base;

import com.raju.javabaseproject.mvp.view.base.IDataView;

/**
 * Created by Rajashekhar Vanahalli on 05/04/18.
 */

public interface IListPresenter<V extends IDataView> extends IPresenter {
    void init(V view);
}
