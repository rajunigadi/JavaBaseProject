package com.raju.javabaseproject.viewmodels

import androidx.lifecycle.MutableLiveData

import com.google.gson.Gson
import com.raju.javabaseproject.data.model.ListItem
import com.raju.javabaseproject.data.source.UserRepository
import com.raju.javabaseproject.viewmodels.base.BaseViewModel

import javax.inject.Inject

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserViewModel @Inject
internal constructor(private val repository: UserRepository, private val gson: Gson) : BaseViewModel() {

    var result: MutableLiveData<MutableList<ListItem>> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()

    fun getUsers() {
        compositeDisposable!!.add(repository.users
                .map {
                    val listItems = mutableListOf<ListItem>()
                    listItems += it
                    return@map listItems
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result.postValue(it) },
                        { error.postValue(it.message) }
                ))
    }
}