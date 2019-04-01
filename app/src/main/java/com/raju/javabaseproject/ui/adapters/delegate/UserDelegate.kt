package com.raju.javabaseproject.ui.adapters.delegate

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.raju.javabaseproject.R
import com.raju.javabaseproject.data.model.User
import com.raju.javabaseproject.ui.adapters.base.ViewAdapterHolder
import com.raju.javabaseproject.ui.adapters.delegate.base.ListAdapterDelegate
import com.raju.javabaseproject.utlities.image.ImageRequester

import butterknife.BindView

class UserDelegate(private val imageRequester: ImageRequester) : ListAdapterDelegate<User>(R.layout.layout_user_item, User::class.java) {

    override fun formViewHolder(v: View): RecyclerView.ViewHolder {
        return HomeHolder(v)
    }

    protected inner class HomeHolder(view: View) : AdapterViewHolder(view), ViewAdapterHolder<User> {

        @BindView(R.id.iv_avatar)
        lateinit var ivAvatar: ImageView

        @BindView(R.id.tv_name)
        lateinit var tvName: TextView

        @BindView(R.id.tv_repo)
        lateinit var tvRepo: TextView

        override fun setData(data: User, position: Int) {
            imageRequester.loadImage(data.avatarUrl!!, ivAvatar!!)
            tvName!!.text = data.login
            tvRepo!!.text = data.reposUrl
        }
    }
}