package com.raju.javabaseproject.ui.adapters.delegate.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public abstract class DelegatingAdapter<T> extends RecyclerView.Adapter
        implements DelegateClickListener {

    DelegatingAdapter() {
        this.adm = new AdapterDelegatesManager<>();
    }

    public final void setup() {
        AdapterDelegate[] delegates = createDelegates();
        int size;
        if (null != delegates && (size = delegates.length) > 0) {
            for (int i = 0; i < size; i++ ) {
                adm.addDelegate(delegates[i]);
            }
        }
    }

    protected abstract AdapterDelegate[] createDelegates();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return adm.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        adm.onBindViewHolder(filteredData, position, holder);
    }

    @Override
    public void onClick(int position, View v) {

    }

    @Override
    public int getItemViewType(int position) {
        return adm.getItemViewType(filteredData, position);
    }

    protected final ListAdapterDelegate clickable(ListAdapterDelegate delegate) {
        delegate.setDelegateClickListener(this);
        return delegate;
    }

    protected T data;
    protected T filteredData;
    private final AdapterDelegatesManager<T> adm;
}
