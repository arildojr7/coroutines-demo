package dev.arildo.data.songs.api

import dev.arildo.data.songs.model.ResponseWrapper
import dev.arildo.data.songs.model.Song
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SongsApiService {

    @GET("/artist/{artistId}")
    suspend fun getSongs(@Path("artistId") artistId: String): Response<ResponseWrapper<Song>>
}