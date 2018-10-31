package com.raju.javabaseproject.ui.fragments;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.raju.javabaseproject.R;
import com.raju.javabaseproject.data.model.User;
import com.raju.javabaseproject.ui.adapters.UsersAdapter;
import com.raju.javabaseproject.ui.adapters.base.ClickableItemTarget;
import com.raju.javabaseproject.ui.adapters.base.ItemClickListener;
import com.raju.javabaseproject.ui.adapters.delegate.base.DelegatingAdapter;
import com.raju.javabaseproject.ui.custom.SimpleDividerItemDecoration;
import com.raju.javabaseproject.ui.fragments.base.BaseDaggerFragment;
import com.raju.javabaseproject.viewmodels.UserViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public class UserFragment extends BaseDaggerFragment<UserViewModel> implements ItemClickListener<User> {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    UsersAdapter adapter;

    public UserFragment() {
        super(R.layout.fragment_users, "Users");
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(recyclerView != null) {
            setupRecyclerView();
            setupAdapter(recyclerView);
        }
        viewModel.userResult.observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                hideLoading();
                adapter.refactorItems(users);
            }
        });

        viewModel.error.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String error) {
                hideLoading();
                Toast.makeText(getActivity(), "Something went wrong, try later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        showLoading();
        viewModel.getUsers();
    }

    protected void setupRecyclerView() {
        if(recyclerView != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        }
    }

    protected void setupAdapter(RecyclerView recyclerView) {
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

    private void showLoading() {
        if(progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void hideLoading() {
        if(progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(User item, int position, View view) {
        Timber.d("Item clicked");
    }
}
