package com.raju.javabaseproject.ui.fragments.base

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

import butterknife.ButterKnife
import butterknife.Unbinder
import com.google.android.material.snackbar.Snackbar


abstract class BaseFragment protected constructor(private val layoutId: Int, private val title: String) : Fragment() {

    private var unbinder: Unbinder? = null
    protected var isActivityResult = false

    init {
        hideKeyboard()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId, container, false)
        unbinder = ButterKnife.bind(this, view)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (unbinder != null) {
            unbinder!!.unbind()
            unbinder = null
        }
    }

    protected fun hideKeyboard() {
        if (activity != null && activity!!.currentFocus != null && activity!!.currentFocus!!.windowToken != null) {
            val ime = activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            ime.hideSoftInputFromWindow(activity!!.currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    @JvmOverloads
    protected fun showSnackBar(message: String) {
        if (activity != null && activity!!.window != null && isAdded) {
            val view = activity!!.window.decorView.findViewById<View>(android.R.id.content)
            //final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
            if (view != null) {
                var snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                val id = com.google.android.material.R.id.snackbar_text
                val textView = snackbar.view.findViewById(id) as TextView
                textView.setTextColor(ContextCompat.getColor(view.context, android.R.color.white))
                snackbar.show()
            } else {
                if (activity != null)
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
            }
        } else {
            if (activity != null)
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
    }

    protected fun showSnackBar(message: String, action: Boolean = false) {
        if (activity != null && activity!!.window != null && isAdded) {
            val view = activity!!.window.decorView.findViewById<View>(android.R.id.content)
            //final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
            if (view != null) {
                var snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)

                if(action) {
                    val id = com.google.android.material.R.id.snackbar_text
                    val textView = snackbar.view.findViewById(id) as TextView
                    textView.setTextColor(ContextCompat.getColor(view.context, android.R.color.white))
                    snackbar.setAction("Dismiss") { snackbar.dismiss() }
                }
                snackbar.show()
            } else {
                if (activity != null)
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
            }
        } else {
            if (activity != null)
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
    }
}

