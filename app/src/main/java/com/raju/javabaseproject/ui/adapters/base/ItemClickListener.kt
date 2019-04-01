package com.raju.javabaseproject.ui.adapters.base

import android.view.View

interface ItemClickListener<T> {
    fun onItemClick(view: View, position: Int, item: T)
}