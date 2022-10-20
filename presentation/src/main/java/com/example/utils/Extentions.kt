package com.example.utils

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified T : Activity> Context.startActivity(data: Intent? = null) {

    Intent(this, T::class.java).apply {
        data?.let {
            this.putExtras(data)
        }
    }.also {
        startActivity(it)
    }
}