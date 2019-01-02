package com.raju.javabaseproject.ui.adapters.base;

import android.view.View;

/**
 * Created by Rajashekhar Vanahalli on 12/21/17.
 */

public interface ItemClickListener<T> {
    void onClick(T item, int position, View view);
}
