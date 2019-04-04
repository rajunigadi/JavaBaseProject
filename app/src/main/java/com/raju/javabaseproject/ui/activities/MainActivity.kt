package com.raju.javabaseproject.ui.activities

import android.os.Bundle
import androidx.fragment.app.transaction

import com.raju.javabaseproject.R
import com.raju.javabaseproject.ui.activities.base.BaseActivity
import com.raju.javabaseproject.ui.fragments.UserFragment

import butterknife.ButterKnife

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        val bundle = Bundle()
        val fragment = UserFragment.newInstance()
        fragment.arguments = bundle


        // ktx concept
        supportFragmentManager.transaction(allowStateLoss = true) {
            replace(R.id.fragment_container, fragment)
        }
    }
}