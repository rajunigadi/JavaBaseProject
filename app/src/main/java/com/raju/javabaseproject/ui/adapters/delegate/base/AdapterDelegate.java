package com.raju.javabaseproject.ui.adapters.delegate.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Rajashekhar Vanahalli on 12/21/17.
 */

public interface AdapterDelegate<T> {

    boolean isForViewType(@NonNull T items, int position);

    @NonNull
    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

    void onBindViewHolder(@NonNull T items, int position, @NonNull RecyclerView.ViewHolder holder);

    void setDelegateClickListener(DelegateClickListener delegateClickListener);
}
