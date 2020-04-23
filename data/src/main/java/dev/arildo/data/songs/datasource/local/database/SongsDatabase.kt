package dev.arildo.data.songs.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.arildo.data.songs.datasource.local.SongDao
import dev.arildo.data.songs.model.Song

@Database(entities = [Song::class], version = 1, exportSchema = false)
abstract class SongsDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao

    companion object {
        @Volatile
        private var instance: SongsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            SongsDatabase::class.java, "songs-database.db"
        ).build()
    }

}