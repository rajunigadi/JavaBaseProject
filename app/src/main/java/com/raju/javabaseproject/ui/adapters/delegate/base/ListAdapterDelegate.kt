package com.raju.javabaseproject.ui.adapters.delegate.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import com.raju.javabaseproject.data.model.ListItem
import com.raju.javabaseproject.ui.adapters.base.ViewAdapterHolder

abstract class ListAdapterDelegate<T : ListItem>
constructor(private val layoutId: Int, private val recordType: Class<T>) : AdapterDelegate<MutableList<T>> {

    private var delegateClickListener: DelegateClickListener? = null

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return formViewHolder(view)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(items: MutableList<T>, position: Int, holder: RecyclerView.ViewHolder) {
        val viewModel: T = items[position]
        (holder as ViewAdapterHolder<T>).setData(viewModel, position)
    }

    override fun isForViewType(items: MutableList<T>, position: Int): Boolean {
        var item: ListItem = items[position]
        return recordType.isInstance(item)
    }

    override fun setDelegateClickListener(delegateClickListener: DelegateClickListener) {
        this.delegateClickListener = delegateClickListener
    }

    protected abstract fun formViewHolder(view: View): RecyclerView.ViewHolder

    protected open inner class AdapterViewHolder protected constructor(private val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            ButterKnife.bind(this, view)
            view.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            delegateClickListener?.onClick(layoutPosition, view)
        }
    }
}