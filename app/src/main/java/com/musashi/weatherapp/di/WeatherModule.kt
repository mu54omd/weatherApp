package com.musashi.weatherapp.di

import android.app.Application
import androidx.room.Room
import com.musashi.weatherapp.data.local.CityDao
import com.musashi.weatherapp.data.local.CityDatabase
import com.musashi.weatherapp.data.remote.WeatherApi
import com.musashi.weatherapp.data.repository.WeatherRepositoryImpl
import com.musashi.weatherapp.domain.repository.WeatherRepository
import com.musashi.weatherapp.utils.Constants.BASE_URL
import com.musashi.weatherapp.utils.Constants.CITY_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Provides
    @Singleton
    fun provideCityDatabase( application: Application):CityDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = CityDatabase::class.java,
            name = CITY_DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideCityDao(
        cityDatabase: CityDatabase
    ): CityDao{
        return cityDatabase.cityDao
    }

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherApi: WeatherApi,
        cityDao: CityDao,
    ): WeatherRepository{
        return WeatherRepositoryImpl(weatherApi = weatherApi, cityDao = cityDao)
    }

}