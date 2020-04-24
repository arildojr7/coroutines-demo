package dev.arildo.coroutinesdemo.singlerequest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dev.arildo.coroutinesdemo.core.util.addData
import dev.arildo.data.songs.SongsRepository
import dev.arildo.data.songs.enum.WrapperTypeEnum
import dev.arildo.data.songs.model.Song
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

class SingleRequestViewModel(private val songsRepository: SongsRepository) : ViewModel(),
    CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    private var _songs = MutableLiveData<List<Song>>()
    val songs: LiveData<List<Song>> = Transformations.map(_songs) { it }

    val isLoading = MutableLiveData<Boolean>()
    val elapsedTime = MutableLiveData<Long>()
    var initialTime: Long = 0L

    init {
        isLoading.postValue(true)
    }

    suspend fun getSongs() {
        val artistId1 = "909253"
        val artistId2 = "1171421960"
        val artistId3 = "358714030"
        val artistId4 = "1419227"
        val artistId5 = "264111789"

        initialTime = System.currentTimeMillis()

        // REQUESTS 1
        songsRepository.getSongs(artistId1).let { response ->
            _songs addData response.body()?.results
        }

        // REQUEST 2
        songsRepository.getSongs(artistId2).let { response ->
            _songs addData response.body()?.results
        }

        // REQUEST 3
        songsRepository.getSongs(artistId3).let { response ->
            _songs addData response.body()?.results
        }

        // REQUEST 4
        songsRepository.getSongs(artistId4).let { response ->
            _songs addData response.body()?.results
        }

        // REQUEST 5
        songsRepository.getSongs(artistId5).let { response ->
            _songs addData response.body()?.results
        }

        elapsedTime.postValue(System.currentTimeMillis() - initialTime)
        isLoading.postValue(false)
    }

    override fun onCleared() {
        coroutineContext.cancel()
        super.onCleared()
    }

}