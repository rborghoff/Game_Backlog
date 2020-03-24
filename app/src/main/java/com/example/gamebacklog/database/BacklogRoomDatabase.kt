package com.example.gamebacklog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gamebacklog.model.Backlog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
@Database(entities = [Backlog::class],version = 1, exportSchema = false)
abstract class BacklogRoomDatabase : RoomDatabase() {
    abstract fun backlogDao(): BacklogDao

    companion object{
        private const val DATABASE_NAME = "GAMEBACKLOG_DATABASE"

        @Volatile
        private var INSTANCE: BacklogRoomDatabase? = null

        fun getDatabase(context: Context): BacklogRoomDatabase?{
            if (INSTANCE == null){
                synchronized(BacklogRoomDatabase::class.java){
                    if(INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,BacklogRoomDatabase::class.java,DATABASE_NAME
                        )
                            .allowMainThreadQueries()
                            .addCallback(object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    INSTANCE?.let { database ->
                                        CoroutineScope(Dispatchers.IO).launch {
                                            database.backlogDao().insertBacklog(Backlog("Title", "title",2, "maandag",3))
                                        }
                                    }
                                }
                            })

                            .build()
                    }

                }
            }
            return INSTANCE
        }
    }
}