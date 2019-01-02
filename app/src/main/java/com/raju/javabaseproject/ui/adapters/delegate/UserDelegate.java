package com.raju.javabaseproject.ui.adapters.delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.raju.javabaseproject.R;
import com.raju.javabaseproject.data.model.EmptyItem;
import com.raju.javabaseproject.data.model.User;
import com.raju.javabaseproject.ui.adapters.base.ViewAdapterHolder;
import com.raju.javabaseproject.ui.adapters.delegate.base.ListAdapterDelegate;
import com.raju.javabaseproject.utlities.image.ImageRequester;

import butterknife.BindView;

public class UserDelegate extends ListAdapterDelegate<User> {

    private ImageRequester imageRequester;

    public UserDelegate(ImageRequester imageRequester) {
        super(R.layout.layout_user_item, User.class);
        this.imageRequester = imageRequester;
    }

    @Override
    protected final RecyclerView.ViewHolder formViewHolder(@NonNull View v) {
        return new UserDelegate.HomeHolder(v);
    }

    class HomeHolder extends AdapterViewHolder implements ViewAdapterHolder<User> {

        @BindView(R.id.iv_avatar)
        ImageView ivAvatar;

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_repo)
        TextView tvRepo;

        HomeHolder(View view) {
            super(view);
        }

        @Override
        public void setData(User data, int position) {
            imageRequester.loadImage(data.getAvatarUrl(), ivAvatar);
            tvName.setText(data.getLogin());
            tvRepo.setText(data.getReposUrl());
        }
    }
}