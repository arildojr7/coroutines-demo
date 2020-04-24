package dev.arildo.coroutinesdemo.multiplerequests.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import dev.arildo.coroutinesdemo.R
import dev.arildo.coroutinesdemo.core.adapter.SongAdapter
import dev.arildo.coroutinesdemo.core.base.BaseActivity
import dev.arildo.coroutinesdemo.core.util.showElapsedTime
import dev.arildo.coroutinesdemo.databinding.ActivityMultipleRequestsBinding
import dev.arildo.coroutinesdemo.multiplerequests.viewmodel.MultipleRequestsViewModel
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MultipleRequestsActivity :
    BaseActivity<ActivityMultipleRequestsBinding>(R.layout.activity_multiple_requests) {

    private val viewModel: MultipleRequestsViewModel by viewModel()
    private val songsAdapter by lazy { SongAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
        binding.rvSongs.adapter = songsAdapter

        launch {
            viewModel.getSongs()
        }
    }

    override fun subscribeUi() {
        viewModel.songs.observe(this, Observer {
            songsAdapter.setData(it)
        })
        viewModel.elapsedTime.observe(this, Observer {
            showElapsedTime(this, it)
        })
    }

}