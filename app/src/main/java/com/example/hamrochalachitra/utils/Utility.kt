package com.example.hamrochalachitra.utils

import android.view.View
import android.widget.ProgressBar

/**
 * Utils for the app.
 * */
object Utility {

    internal fun ProgressBar.showProgress() {
        this.visibility = View.VISIBLE
    }

    internal fun ProgressBar.hideProgress() {
        this.visibility = View.GONE
    }

}