package com.example.dbgroupwork.Data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [PlanLocal::class, ClasificacionLocal::class], exportSchema = true , version = 1)
abstract class FitAppDatabase: RoomDatabase() {
    abstract fun getPlanDao(): PlanDao
    abstract fun getClacificacionDao(): ClasificacionDao

}