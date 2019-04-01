package com.raju.javabaseproject.ui.adapters.delegate.base

import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

interface AdapterDelegate<T> {
    fun isForViewType(@NonNull items: T, position: Int): Boolean
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(@NonNull items: T, position: Int, holder: RecyclerView.ViewHolder)
    fun setDelegateClickListener(delegateClickListener: DelegateClickListener)
}