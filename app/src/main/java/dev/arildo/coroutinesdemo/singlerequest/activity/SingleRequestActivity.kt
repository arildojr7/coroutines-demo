package dev.arildo.coroutinesdemo.singlerequest.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import dev.arildo.coroutinesdemo.R
import dev.arildo.coroutinesdemo.core.adapter.SongAdapter
import dev.arildo.coroutinesdemo.core.base.BaseActivity
import dev.arildo.coroutinesdemo.core.util.showElapsedTime
import dev.arildo.coroutinesdemo.databinding.ActivitySingleRequestBinding
import dev.arildo.coroutinesdemo.singlerequest.viewmodel.SingleRequestViewModel
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class SingleRequestActivity :
    BaseActivity<ActivitySingleRequestBinding>(R.layout.activity_single_request) {

    private val viewModel: SingleRequestViewModel by viewModel()
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