package com.raju.javabaseproject.ui.fragments;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.raju.javabaseproject.R;
import com.raju.javabaseproject.data.model.User;
import com.raju.javabaseproject.ui.adapters.UserAdapter;
import com.raju.javabaseproject.ui.adapters.base.ClickableItemTarget;
import com.raju.javabaseproject.ui.adapters.base.ItemClickListener;
import com.raju.javabaseproject.ui.adapters.delegate.base.DelegatingAdapter;
import com.raju.javabaseproject.ui.fragments.base.BaseDaggerFragment;
import com.raju.javabaseproject.viewmodels.UserViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;

public class UserFragment extends BaseDaggerFragment<UserViewModel> implements ItemClickListener<User> {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    UserAdapter adapter;

    public UserFragment() {
        super(R.layout.fragment_user, "User");
    }

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        setupAdapter(recyclerView);

        viewModel.result.observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                hideLoading();
                adapter.refactorItems(users);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

        viewModel.error.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                hideLoading();
                showSnackBar(s);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (viewModel != null) {
            viewModel.init();
            viewModel.getUsers();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        viewModel.destroy();
    }

    public void showLoading() {

    }

    public void hideLoading() {

    }

    @Override
    public void onClick(User item, int position, View view) {
        Timber.d("User clicked");
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setupAdapter(RecyclerView recyclerView) {
        if (recyclerView.getAdapter() != adapter) {
            recyclerView.setAdapter(adapter);

            if (adapter instanceof ClickableItemTarget) {
                @SuppressWarnings("unchecked")
                ClickableItemTarget<User> target = (ClickableItemTarget<User>) adapter;
                target.setItemClickListener(this);
            }

            if (adapter instanceof DelegatingAdapter) {
                adapter.setup();
            }
        }
    }
}