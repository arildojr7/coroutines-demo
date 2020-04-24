package dev.arildo.coroutinesdemo.core.util

import android.content.Context
import android.widget.Toast
import dev.arildo.coroutinesdemo.R

fun showElapsedTime(context: Context, elapsedTime: Long) {
    Toast.makeText(context, context.getString(R.string.elapsed_time, elapsedTime.toString()), Toast.LENGTH_LONG).show()
}