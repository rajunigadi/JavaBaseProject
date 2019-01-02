package com.raju.javabaseproject.mvp.presenter.base;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.raju.javabaseproject.mvp.view.base.IResettableView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Rajashekhar Vanahalli on 05/04/18.
 */

public abstract class BasePresenter<V extends IResettableView> implements IListPresenter<V> {

    protected BasePresenter() {

    }

    @Override
    public void init(@NonNull V view) {
        this.disposable = new CompositeDisposable();
        this.view = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        if(disposable!=null){
            disposable.clear();
            disposable = null;
        }
        this.view = null;
    }

    protected final V getView() {
        return view;
    }

    @CallSuper
    protected void showLoading() {
        view.showLoading();
    }

    @CallSuper
    protected void hideLoading() {
        view.hideLoading();
    }

    private V view;
    protected CompositeDisposable disposable = new CompositeDisposable();
    protected final String TAG = getClass().getSimpleName();
}


