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

    @Query("SELECT COUNT(*) FROM CityModel")
    suspend fun getTableCount(): Long

    @Query("SELECT DISTINCT countryName FROM CityModel")
    fun getCountries(): Flow<List<String>>

    @Query("SELECT DISTINCT countryNameFa FROM CityModel")
    fun getCountriesFa(): Flow<List<String>>

    @Query("SELECT * FROM CityModel WHERE countryName=:countryName OR countryNameFa=:countryName")
    fun selectCountry(countryName: String): Flow<List<CityModel>>

    @Query("SELECT * FROM CityModel WHERE cityName=:cityName AND (countryName=:countryName OR countryNameFa=:countryName)")
    suspend fun selectCity(cityName: String, countryName: String): CityModel?
}