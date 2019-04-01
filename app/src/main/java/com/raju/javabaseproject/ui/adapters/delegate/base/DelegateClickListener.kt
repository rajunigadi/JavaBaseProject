package com.raju.javabaseproject.ui.adapters.delegate.base

import android.view.View

interface DelegateClickListener {
    fun onClick(position: Int, v: View)
}