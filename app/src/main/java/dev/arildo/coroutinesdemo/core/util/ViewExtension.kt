package dev.arildo.coroutinesdemo.core.util

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.transition.AutoTransition
import androidx.transition.Scene
import androidx.transition.TransitionManager
import dev.arildo.coroutinesdemo.R

fun showElapsedTime(context: Context, elapsedTime: Long) {
    Toast.makeText(context, context.getString(R.string.elapsed_time, elapsedTime.toString()), Toast.LENGTH_LONG).show()
}
fun View.enterFromTop(duration: Int = 500) {
    val originalY = this.y
    visibility = View.VISIBLE
    this.y = 0 - this.height.toFloat()
    this.animate().y(originalY).setDuration(duration.toLong()).start()
}

fun View.enterFromBottom(duration: Int = 500) {
    val originalY = this.y
    visibility = View.VISIBLE
    this.y = rootView.height + this.height.toFloat()
    this.animate().y(originalY).setDuration(duration.toLong()).start()
}

fun View.exitToTop(duration: Int = 500) {
    val originalHeight = 0 - this.height
    this.animate().y(originalHeight.toFloat()).setDuration(duration.toLong()).start()
}
fun View.exitToBottom(duration: Int = 500) {
    val originalHeight = this.height
    this.animate().y(rootView.height+originalHeight.toFloat()).setDuration(duration.toLong()).start()
}

inline fun <T : View> T.runTransition(block: T.() -> Unit): T {
    TransitionManager.beginDelayedTransition(this.parent as ViewGroup)
    block()
    return this
}

inline fun <T : View> T.runTransition(duration: Long, block: T.() -> Unit): T {
    val transition = AutoTransition()
    transition.duration = duration
    transition.interpolator = AccelerateDecelerateInterpolator()
    TransitionManager.go(Scene(this.parent as ViewGroup), transition)
    block()
    return this
}

fun View.fadeColor(color: Int) {
    val from = ContextCompat.getColor(this.context, android.R.color.white)
    val to = ContextCompat.getColor(this.context, color)

    val anim = ValueAnimator()
    anim.setIntValues(from, to)
    anim.setEvaluator(ArgbEvaluator())
    anim.addUpdateListener { valueAnimator -> this.setBackgroundColor(valueAnimator.animatedValue as Int) }

    anim.duration = 1200
    anim.start()
}
