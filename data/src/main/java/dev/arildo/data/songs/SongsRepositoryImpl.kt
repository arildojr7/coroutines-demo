package dev.arildo.data.songs

import dev.arildo.data.songs.exception.FailureRequestException
import dev.arildo.data.songs.exception.FailureRequestWithLocalDataException
import dev.arildo.data.songs.model.ResponseWrapper
import dev.arildo.data.songs.model.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

@ExperimentalCoroutinesApi
class SongsRepositoryImpl(
    private val songsLocalDataSource: SongsDataSource.Local,
    private val songsRemoteDataSource: SongsDataSource.Remote
) : SongsRepository {
    override suspend fun saveSongs(songs: List<Song>) {
        songsLocalDataSource.saveSongs(songs)
    }

    override suspend fun getSongs(artistId: String): Response<ResponseWrapper<Song>> {
        return songsRemoteDataSource.getSongs(artistId)
    }

    override suspend fun getSongsFlow(artistId: String, limit: Int): Flow<Response<ResponseWrapper<Song>>> {
        return flow {
            songsLocalDataSource.getSongsFlow().collect { local ->
                if (local.isNotEmpty()) {
                    emit(Response.success(ResponseWrapper(results = local)))
                }
                try {
                    songsRemoteDataSource.getSongs(artistId).body()?.results?.let { remote ->
                        if (!local.containsAll(remote)) {
                            saveSongs(remote)
                            emit(Response.success(ResponseWrapper(results = remote)))
                        }
                    }
                } catch (e: Exception) {
                    if (local.isNotEmpty()) {
                        throw FailureRequestWithLocalDataException()
                    } else {
                        throw FailureRequestException()
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}