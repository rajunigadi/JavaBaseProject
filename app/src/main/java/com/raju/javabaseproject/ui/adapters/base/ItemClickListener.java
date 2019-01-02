package com.raju.javabaseproject.ui.adapters.base;

import android.view.View;

public interface ItemClickListener<T> {
    void onClick(T item, int position, View view);
}
