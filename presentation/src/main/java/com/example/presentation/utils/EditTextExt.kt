package com.example.presentation.utils

import android.widget.EditText

fun EditText.getValue(): String {
    return this.text.toString()
}