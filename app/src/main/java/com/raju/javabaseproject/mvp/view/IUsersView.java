package com.raju.javabaseproject.mvp.view;

import com.raju.javabaseproject.mvp.model.ServerResponse;
import com.raju.javabaseproject.mvp.view.base.IResettableView;

/**
 * Created by Rajashekhar Vanahalli on 1/8/18.
 */

public interface IUsersView extends IResettableView {
    void bindData(ServerResponse serverResponse);
}
