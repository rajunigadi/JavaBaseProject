package com.raju.javabaseproject.ui.adapters.delegate.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raju.javabaseproject.data.model.ListItem

abstract class DelegatingAdapter<T> :
        RecyclerView.Adapter<RecyclerView.ViewHolder>(), DelegateClickListener {

    open var data: T? = null
    open var filteredData: T? = null
    var adm: AdapterDelegatesManager<T> = AdapterDelegatesManager<T>()

    fun setup() {
        val delegates = createDelegates()
        val size: Int = delegates.size
        for (i in 0 until size) {
            adm.addDelegate(delegates[i])
        }
    }

    protected abstract fun createDelegates(): Array<AdapterDelegate<T>>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return adm.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        adm.onBindViewHolder(filteredData!!, position, holder)
    }

    override fun getItemViewType(position: Int): Int {
        return adm.getItemViewType(filteredData!!, position)
    }

    protected fun clickable(delegate: ListAdapterDelegate<ListItem>): ListAdapterDelegate<ListItem> {
        delegate.setDelegateClickListener(this)
        return delegate
    }
}
