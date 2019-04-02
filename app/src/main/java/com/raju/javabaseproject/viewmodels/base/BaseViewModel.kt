package com.raju.javabaseproject.viewmodels.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected var compositeDisposable: CompositeDisposable? = null
    val progress = MutableLiveData<Boolean>()

    fun init() {
        this.compositeDisposable = CompositeDisposable()
    }

    fun setProgressStatus(boolean: Boolean) {
        progress.postValue(boolean)
    }

    fun destroy() {
        if (compositeDisposable != null) {
            compositeDisposable!!.clear()
            compositeDisposable = null
        }
    }
}
