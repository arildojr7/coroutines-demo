package dev.arildo.data.songs

import dev.arildo.data.songs.model.ResponseWrapper
import dev.arildo.data.songs.model.Song
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SongsDataSource {

    interface Local {
        suspend fun getSongs() : Flow<List<Song>>
        suspend fun saveSongs(songs: List<Song>)
    }

    interface Remote {
        suspend fun getSongs(artistId: String, limit: Int): Response<ResponseWrapper<Song>>
    }
}