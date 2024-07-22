package com.musashi.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.musashi.weatherapp.domain.model.CityModel

@Database(entities = [CityModel::class], version = 1)
abstract class CityDatabase: RoomDatabase() {
    abstract val cityDao: CityDao
}