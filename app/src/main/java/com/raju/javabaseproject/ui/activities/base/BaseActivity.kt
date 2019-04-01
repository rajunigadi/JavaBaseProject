package com.raju.javabaseproject.ui.activities.base

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

import com.raju.javabaseproject.R

import javax.inject.Inject

import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector

open class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentDispatchingAndroidInjector
    }

    protected fun showSnackBar(message: String) {
        showSnackBar(message, false, null)
    }

    protected fun showSnackBar(message: String, hasAction: Boolean) {
        showSnackBar(message, hasAction, null)
    }

    protected fun showSnackBar(message: String, isAction: Boolean, action: String?) {
        var lAction = action
        val view = window.decorView.findViewById<View>(android.R.id.content)
        if (view != null) {
            val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            if (isAction) {
                snackBar.duration = Snackbar.LENGTH_INDEFINITE
                if (TextUtils.isEmpty(lAction)) lAction = /*getResources().getString(R.string.dismiss)*/ "Dismiss"
                snackBar.setAction(lAction) { snackBar.dismiss() }
            }
            snackBar.setActionTextColor(Color.WHITE)
            val sbView = snackBar.view
            val textView = sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.setTextColor(Color.WHITE)
            snackBar.show()
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    protected fun hideKeyboard() {
        if (currentFocus != null && currentFocus!!.windowToken != null) {
            val ime = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            ime.hideSoftInputFromWindow(currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}
