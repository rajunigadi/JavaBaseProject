package com.raju.javabaseproject.ui.adapters

import com.raju.javabaseproject.data.model.ListItem
import com.raju.javabaseproject.ui.adapters.delegate.UserDelegate
import com.raju.javabaseproject.ui.adapters.delegate.base.AdapterDelegate
import com.raju.javabaseproject.ui.adapters.delegate.base.DelegatingListAdapter
import com.raju.javabaseproject.ui.adapters.delegate.base.ListAdapterDelegate
import com.raju.javabaseproject.utlities.image.ImageRequester

import javax.inject.Inject

class UserAdapter @Inject
internal constructor() : DelegatingListAdapter<ListItem>() {

    @Inject
    lateinit var imageRequester: ImageRequester

    @Suppress("UNCHECKED_CAST")
    override fun createDelegates(): Array<AdapterDelegate<MutableList<ListItem>>> {
        val delegate1: ListAdapterDelegate<ListItem> = clickable(UserDelegate(imageRequester) as ListAdapterDelegate<ListItem>)
        return arrayOf(delegate1)
    }
}