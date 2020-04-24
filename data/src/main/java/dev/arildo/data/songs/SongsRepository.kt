package dev.arildo.data.songs

import dev.arildo.data.songs.model.ResponseWrapper
import dev.arildo.data.songs.model.Song
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SongsRepository {
    suspend fun getSongs(artistId: String, limit: Int): Response<ResponseWrapper<Song>>

    suspend fun getSongsFlow(artistId: String, limit: Int): Flow<Response<ResponseWrapper<Song>>>

    suspend fun saveSongs(songs: List<Song>)
}