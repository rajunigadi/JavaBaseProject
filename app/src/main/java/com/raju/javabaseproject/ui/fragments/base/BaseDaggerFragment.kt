package com.raju.javabaseproject.ui.fragments.base

import android.annotation.SuppressLint

import com.raju.javabaseproject.viewmodels.base.BaseViewModel

import javax.inject.Inject

@SuppressLint("ValidFragment")
open class BaseDaggerFragment<V : BaseViewModel> protected constructor(layoutId: Int, title: String) : BaseFragment(layoutId, title) {

    @Inject
    lateinit var viewModel: V

    override fun onDestroyView() {
        super.onDestroyView()
        if (viewModel != null) {
            viewModel!!.destroy()
        }
    }
}
