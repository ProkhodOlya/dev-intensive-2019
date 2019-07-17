package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val imm: InputMethodManager? = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val v = currentFocus
    if (v != null && imm != null && imm.isActive)
        imm.hideSoftInputFromWindow(v.windowToken, 0)
}