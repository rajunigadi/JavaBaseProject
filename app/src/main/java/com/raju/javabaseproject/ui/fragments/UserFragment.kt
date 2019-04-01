package com.raju.javabaseproject.ui.fragments

import androidx.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View

import com.raju.javabaseproject.R
import com.raju.javabaseproject.data.model.User
import com.raju.javabaseproject.ui.adapters.UserAdapter
import com.raju.javabaseproject.ui.adapters.base.ClickableItemTarget
import com.raju.javabaseproject.ui.adapters.base.ItemClickListener
import com.raju.javabaseproject.ui.adapters.delegate.base.DelegatingAdapter
import com.raju.javabaseproject.ui.fragments.base.BaseDaggerFragment
import com.raju.javabaseproject.viewmodels.UserViewModel

import javax.inject.Inject

import butterknife.BindView
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber

class UserFragment : BaseDaggerFragment<UserViewModel>(R.layout.fragment_user, "User"), ItemClickListener<User> {

    @BindView(R.id.recycler_view)
    lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupAdapter(recyclerView!!)

        viewModel!!.result.observe(this, Observer {
            hideLoading()
            adapter!!.refactorItems(it)
            recyclerView!!.visibility = View.VISIBLE
        })

        viewModel!!.error.observe(this, Observer { s ->
            hideLoading()
            showSnackBar(s)
        })
    }

    override fun onStart() {
        super.onStart()
        if (viewModel != null) {
            viewModel!!.init()
            viewModel!!.getUsers()
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel!!.destroy()
    }

    override fun onItemClick(view: View, position: Int, item: User) {
       Timber.d("onItemClick")
    }

    fun showLoading() {
        Timber.d("showLoading")
    }

    fun hideLoading() {
        Timber.d("hideLoading")
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        recyclerView!!.layoutManager = layoutManager
    }

    private fun setupAdapter(recyclerView: RecyclerView) {
        if (recyclerView.adapter !== adapter) {
            recyclerView.adapter = adapter

            if (adapter is ClickableItemTarget<*>) {
                val target = adapter as ClickableItemTarget<User>?
                target!!.setOnItemClickListener(this)
            }

            if (adapter is DelegatingAdapter<*>) {
                adapter!!.setup()
            }
        }
    }

    companion object {

        fun newInstance(): UserFragment {
            return UserFragment()
        }
    }
}