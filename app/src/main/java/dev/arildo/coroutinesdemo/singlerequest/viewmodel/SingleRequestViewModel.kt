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

    var elapsedTime: Long = 0L
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
        val limit = 5

        initialTime = System.currentTimeMillis()

        // REQUESTS
        val response1 = songsRepository.getSongs(artistId1, limit)
        val response2 = songsRepository.getSongs(artistId2, limit)
        val response3 = songsRepository.getSongs(artistId3, limit)
        val response4 = songsRepository.getSongs(artistId4, limit)
        val response5 = songsRepository.getSongs(artistId5, limit)

        elapsedTime = System.currentTimeMillis() - initialTime

        
        //
        _songs addData response1.body()?.results?.filter { it.wrapperType == WrapperTypeEnum.TRACK.getValue() }
        _songs addData response2.body()?.results?.filter { it.wrapperType == WrapperTypeEnum.TRACK.getValue() }
        _songs addData response3.body()?.results?.filter { it.wrapperType == WrapperTypeEnum.TRACK.getValue() }
        _songs addData response4.body()?.results?.filter { it.wrapperType == WrapperTypeEnum.TRACK.getValue() }
        _songs addData response5.body()?.results?.filter { it.wrapperType == WrapperTypeEnum.TRACK.getValue() }

        isLoading.postValue(false)
    }

    override fun onCleared() {
        coroutineContext.cancel()
        super.onCleared()
    }

}