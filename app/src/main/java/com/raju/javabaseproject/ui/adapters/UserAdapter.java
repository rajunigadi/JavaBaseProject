package com.raju.javabaseproject.ui.adapters;

import com.raju.javabaseproject.ui.adapters.delegate.UserDelegate;
import com.raju.javabaseproject.ui.adapters.delegate.base.AdapterDelegate;
import com.raju.javabaseproject.ui.adapters.delegate.base.DelegatingListAdapter;
import com.raju.javabaseproject.ui.adapters.delegate.base.ListAdapterDelegate;
import com.raju.javabaseproject.utlities.image.ImageRequester;

import javax.inject.Inject;

public class UserAdapter extends DelegatingListAdapter {

    private ImageRequester imageRequester;

    @Inject
    UserAdapter(ImageRequester imageRequester) {
        this.imageRequester = imageRequester;
    }

    @Override
    protected AdapterDelegate[] createDelegates() {
        return new ListAdapterDelegate[]{
                clickable(new UserDelegate(imageRequester))
        };
    }
}