package dev.arildo.coroutinesdemo.core.util

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend infix fun <T> MutableLiveData<List<T>>.addData(data: List<T>?) {
    withContext(Dispatchers.Main){
        value = mutableListOf<T>().apply {
            data?.let { addAll(it) }
            this@addData.value?.let { addAll(it) }

        }
    }
}