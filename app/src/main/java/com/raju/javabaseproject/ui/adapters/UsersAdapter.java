package com.raju.javabaseproject.ui.adapters;

import com.raju.javabaseproject.ui.adapters.delegate.UsersDelegate;
import com.raju.javabaseproject.ui.adapters.delegate.base.AdapterDelegate;
import com.raju.javabaseproject.ui.adapters.delegate.base.DelegatingListAdapter;
import com.raju.javabaseproject.ui.adapters.delegate.base.ListAdapterDelegate;

import javax.inject.Inject;

public class UsersAdapter extends DelegatingListAdapter {

    @Inject
    UsersAdapter() {

    }

    @Override
    protected AdapterDelegate[] createDelegates() {
        return new ListAdapterDelegate[]{
                new UsersDelegate()
        };
    }
}