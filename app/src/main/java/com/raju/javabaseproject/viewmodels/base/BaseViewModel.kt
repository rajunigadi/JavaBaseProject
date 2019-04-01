package com.raju.javabaseproject.viewmodels.base

import androidx.lifecycle.ViewModel

import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected var compositeDisposable: CompositeDisposable? = null

    fun init() {
        this.compositeDisposable = CompositeDisposable()
    }

    fun destroy() {
        if (compositeDisposable != null) {
            compositeDisposable!!.clear()
            compositeDisposable = null
        }
    }
}
