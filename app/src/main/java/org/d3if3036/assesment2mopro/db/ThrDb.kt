package org.d3if3036.assesment2mopro.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ThrEntity::class], version = 1, exportSchema = false)
abstract class ThrDb : RoomDatabase() {
    abstract val dao: ThrDao

    companion object {

        @Volatile
        private var INSTANCE: ThrDb? = null

        fun getInstance(context: Context): ThrDb {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ThrDb::class.java,
                        "thr.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}