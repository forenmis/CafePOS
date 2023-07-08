package com.example.presentation.utils

import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide

fun EditText.getValue(): String {
    return this.text.toString()
}

fun ImageView.setImage(str: Any) {
    Glide.with(this)
        .load(str)
        .into(this)
}