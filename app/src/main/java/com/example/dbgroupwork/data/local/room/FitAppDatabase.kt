package com.example.dbgroupwork.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PlanLocal::class, ClasificacionLocal::class], exportSchema = true , version = 1)
abstract class FitAppDatabase: RoomDatabase() {
    abstract fun getDao(): PlanDao
    abstract fun getClasificacionDao(): ClasificacionDao

    companion object {

        var db: FitAppDatabase? = null

        fun provideDatabase(application: Context): FitAppDatabase {
            if (db == null) {
                synchronized(this) {
                    db = Room.databaseBuilder(
                        application,
                        FitAppDatabase::class.java, "FitAppDb.db"
                    ).build()
                }
            }
            return db!!
        }
    }
}