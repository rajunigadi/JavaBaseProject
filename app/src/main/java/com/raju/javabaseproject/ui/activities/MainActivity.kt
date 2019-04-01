package com.raju.javabaseproject.ui.activities

import android.os.Bundle

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
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, fragment)
        ft.commit()
    }
}
