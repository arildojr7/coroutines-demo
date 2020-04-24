package dev.arildo.coroutinesdemo.multiplerequests.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import dev.arildo.coroutinesdemo.core.base.BaseViewModel
import dev.arildo.coroutinesdemo.core.util.addData
import dev.arildo.data.songs.SongsRepository
import dev.arildo.data.songs.model.Song
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class MultipleRequestsViewModel(private val songsRepository: SongsRepository) : BaseViewModel() {

    private var _songs = MutableLiveData<List<Song>>()
    val songs: LiveData<List<Song>> = Transformations.map(_songs) { it }

    val isLoading = MutableLiveData<Boolean>()
    val elapsedTime = MutableLiveData<Long>()

    init {
        isLoading.postValue(false)
    }

    suspend fun getSongs() {
        val artistId1 = "909253"
        val artistId2 = "1171421960"
        val artistId3 = "358714030"
        val artistId4 = "1419227"
        val artistId5 = "264111789"

        elapsedTime.postValue(measureTimeMillis {
            coroutineScope {

                // REQUEST 1
                launch {
                    songsRepository.getSongs(artistId1).let { response ->
                        _songs addData response.body()?.results
                    }
                }

                // REQUEST 2
                launch {
                    songsRepository.getSongs(artistId2).let { response ->
                        _songs addData response.body()?.results
                    }
                }

                // REQUEST 3
                launch {
                    songsRepository.getSongs(artistId3).let { response ->
                        _songs addData response.body()?.results
                    }
                }

                // REQUEST 4
                launch {
                    songsRepository.getSongs(artistId4).let { response ->
                        _songs addData response.body()?.results
                    }
                }

                // REQUEST 5
                launch {
                    songsRepository.getSongs(artistId5).let { response ->
                        _songs addData response.body()?.results
                    }
                }

            }
        })

    }

}