package com.raju.javabaseproject.ui.activities.base

import com.raju.javabaseproject.viewmodels.base.BaseViewModel

import javax.inject.Inject

class BaseDaggerActivity<V : BaseViewModel> : BaseActivity() {

    @Inject
    lateinit var viewModel: V

    override fun onDestroy() {
        super.onDestroy()
        if (viewModel != null) {
            viewModel!!.destroy()
        }
    }
}
