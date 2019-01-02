package com.raju.javabaseproject.ui.adapters;

import com.raju.javabaseproject.ui.adapters.delegate.UserDelegate;
import com.raju.javabaseproject.ui.adapters.delegate.base.AdapterDelegate;
import com.raju.javabaseproject.ui.adapters.delegate.base.DelegatingListAdapter;
import com.raju.javabaseproject.ui.adapters.delegate.base.ListAdapterDelegate;

import javax.inject.Inject;

public class UserAdapter extends DelegatingListAdapter {

    @Inject
    UserAdapter() {

    }

    @Override
    protected AdapterDelegate[] createDelegates() {
        return new ListAdapterDelegate[]{
                clickable(new UserDelegate())
        };
    }
}