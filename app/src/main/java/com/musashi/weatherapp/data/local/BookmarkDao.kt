package com.musashi.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.musashi.weatherapp.domain.model.CityModel
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(cityModel: CityModel)

    @Delete
    suspend fun delete(cityModel: CityModel)

    @Query("SELECT * FROM CityModel")
    fun getBookmarkedCities(): Flow<List<CityModel>>

}