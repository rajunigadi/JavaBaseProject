package com.raju.javabaseproject.ui.adapters.base

interface ViewAdapterHolder<T> {
    fun setData(data: T, position: Int)
}