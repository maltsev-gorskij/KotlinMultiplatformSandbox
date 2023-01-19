package ru.lyrian.kotlinmultiplatformsandbox.android.core.ui

import android.app.Activity
import android.widget.Toast

fun Activity.showShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
