package com.raju.javabaseproject.ui.adapters.delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.raju.javabaseproject.R;
import com.raju.javabaseproject.mvp.model.EmptyItem;
import com.raju.javabaseproject.ui.adapters.base.ViewAdapterHolder;
import com.raju.javabaseproject.ui.adapters.delegate.base.ListAdapterDelegate;

/**
 * Created by Rajashekhar Vanahalli on 12/26/17.
 */

public class UserDelegate extends ListAdapterDelegate<EmptyItem> {

    public UserDelegate() {
        super(R.layout.layout_user_item, EmptyItem.class);
    }

    @Override
    protected final RecyclerView.ViewHolder formViewHolder(@NonNull View v) {
        return new UserDelegate.HomeHolder(v);
    }

    class HomeHolder extends AdapterViewHolder implements ViewAdapterHolder<EmptyItem> {

        HomeHolder(View view) {
            super(view);
        }

        @Override
        public void setData(EmptyItem data, int position) {

        }
    }
}