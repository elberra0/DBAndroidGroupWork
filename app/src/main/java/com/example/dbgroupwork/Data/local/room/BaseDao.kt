package com.example.dbgroupwork.Data.local.room
import androidx.room.*
import kotlinx.coroutines.flow.Flow

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insert(entity: T)

    @Update
    suspend fun update(entity: T)

    @Delete
    suspend fun delete(entity: T)

    suspend fun getAll(): Flow<List<T>>
}