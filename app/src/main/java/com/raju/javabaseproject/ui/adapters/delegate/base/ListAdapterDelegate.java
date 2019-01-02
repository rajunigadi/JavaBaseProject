package com.raju.javabaseproject.ui.adapters.delegate.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raju.javabaseproject.ui.adapters.base.ViewAdapterHolder;
import com.raju.javabaseproject.mvp.model.ListItem;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Rajashekhar Vanahalli on 12/21/17.
 */

public abstract class ListAdapterDelegate<T extends ListItem> implements AdapterDelegate<List<T>> {
    protected ListAdapterDelegate(int layoutId, Class<T> recordType) {
        this.layoutId   = layoutId;
        this.recordType = recordType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater minInflater = LayoutInflater.from(parent.getContext());
        View view = minInflater.inflate(layoutId, parent, false);
        return formViewHolder(view);
    }

    @Override
    public boolean isForViewType(@NonNull List<T> items, int position) {
        ListItem record = items.get(position);
        if (recordType == null) {
            return record == null;
        } else {
            return recordType.isInstance(record);
        }
    }

    @Override
    public final void onBindViewHolder(@NonNull List<T> items, int position, @NonNull RecyclerView.ViewHolder holder) {
        T viewModel = items.get(position);
        ((ViewAdapterHolder<T>)holder).setData(viewModel, position);
    }

    protected abstract RecyclerView.ViewHolder formViewHolder(@NonNull View v);

    @Override
    public final void setDelegateClickListener(DelegateClickListener delegateClickListener) {
        this.delegateClickListener = delegateClickListener;
    }

    protected class AdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected AdapterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (delegateClickListener != null) {
                delegateClickListener.onClick(getLayoutPosition(), view);
            }
        }
    }

    protected DelegateClickListener delegateClickListener;
    private final int layoutId;
    private final Class<T> recordType;
}
