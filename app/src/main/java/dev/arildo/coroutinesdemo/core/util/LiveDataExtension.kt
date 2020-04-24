package dev.arildo.coroutinesdemo.core.util

import androidx.lifecycle.MutableLiveData

infix fun <T> MutableLiveData<List<T>>.addData(data: List<T>?) {
    value = mutableListOf<T>().apply {
        data?.let { addAll(it) }
        this@addData.value?.let { addAll(it) }

    }
}