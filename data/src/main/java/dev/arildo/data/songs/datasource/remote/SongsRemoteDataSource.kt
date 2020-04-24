package dev.arildo.data.songs.datasource.remote

import dev.arildo.data.songs.SongsDataSource
import dev.arildo.data.songs.api.SongsApiService
import dev.arildo.data.songs.model.ResponseWrapper
import dev.arildo.data.songs.model.Song
import retrofit2.Response

class SongsRemoteDataSource(private val apiService: SongsApiService) : SongsDataSource.Remote {
    override suspend fun getSongs(artistId: String): Response<ResponseWrapper<Song>> {
        return apiService.getSongs(artistId)
    }
}