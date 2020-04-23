package dev.arildo.data.songs.datasource.local

import dev.arildo.data.songs.SongsDataSource
import dev.arildo.data.songs.datasource.local.database.SongsDatabase
import dev.arildo.data.songs.model.Song
import kotlinx.coroutines.flow.Flow

class SongsLocalDataSource(private val appDatabase: SongsDatabase) : SongsDataSource.Local {
    override suspend fun getSongs(): Flow<List<Song>> {
        return appDatabase.songDao().getAll()
    }

    override suspend fun saveSongs(songs: List<Song>) {
        appDatabase.songDao().insertAll(songs)
    }

}