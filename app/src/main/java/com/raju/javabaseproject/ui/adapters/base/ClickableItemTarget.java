package com.raju.javabaseproject.ui.adapters.base;

/**
 * Created by Rajashekhar Vanahalli on 12/21/17.
 */

public interface ClickableItemTarget<T> {
    void setItemClickListener(ItemClickListener<T> listener);
}
