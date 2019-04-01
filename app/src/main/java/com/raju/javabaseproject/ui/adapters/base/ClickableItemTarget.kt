package com.raju.javabaseproject.ui.adapters.base

interface ClickableItemTarget<T> {
    fun setOnItemClickListener(listener: ItemClickListener<T>)
}