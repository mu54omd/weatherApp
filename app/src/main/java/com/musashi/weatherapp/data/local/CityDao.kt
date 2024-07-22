package com.musashi.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.musashi.weatherapp.domain.model.CityModel
import kotlinx.coroutines.flow.Flow


@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(cityModel: CityModel)

    @Query("SELECT * FROM CityModel")
    fun getCities(): Flow<List<CityModel>>

    @Query("SELECT * FROM CityModel WHERE cityName=:cityName")
    suspend fun getCity(cityName: String): CityModel?

}