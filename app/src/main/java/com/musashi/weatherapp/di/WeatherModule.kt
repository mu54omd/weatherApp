package com.musashi.weatherapp.di

import android.app.Application
import androidx.room.Room
import com.musashi.weatherapp.data.local.BookmarkDao
import com.musashi.weatherapp.data.local.BookmarkDatabase
import com.musashi.weatherapp.data.local.CityDao
import com.musashi.weatherapp.data.local.CityDatabase
import com.musashi.weatherapp.data.preferences.LocalUserManagerImpl
import com.musashi.weatherapp.data.remote.MapApi
import com.musashi.weatherapp.data.remote.WeatherApi
import com.musashi.weatherapp.data.repository.WeatherRepositoryImpl
import com.musashi.weatherapp.domain.preferences.LocalUserManager
import com.musashi.weatherapp.domain.repository.WeatherRepository
import com.musashi.weatherapp.utils.Constants.CITY_BOOKMARK_DATABASE_NAME
import com.musashi.weatherapp.utils.Constants.MAP_API_BASE_URL
import com.musashi.weatherapp.utils.Constants.WEATHER_API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    //Provide DataStore for Hilt
    @Provides
    @Singleton
    fun provideLocalManager(application: Application):LocalUserManager{
        return LocalUserManagerImpl(application)
    }

//    //Provide City Database for Hilt
//    @Provides
//    @Singleton
//    fun provideCityDatabase( application: Application):CityDatabase{
//        return Room
//            .databaseBuilder(
//            context = application,
//            klass = CityDatabase::class.java,
//            name = CITY_DATABASE_NAME)
//            .fallbackToDestructiveMigration()
//            .build()
//    }
//Provide City Database for Hilt
@Provides
@Singleton
fun provideCityDatabase( application: Application):CityDatabase{
    return Room
        .databaseBuilder(
            context = application,
            klass = CityDatabase::class.java,
            name = "city_db_edited.db"
        )
        .fallbackToDestructiveMigration()
        .createFromAsset("city_db_edited.db")
        .build()
}

    //Provide City DAO for Hilt
    @Provides
    @Singleton
    fun provideCityDao(
        cityDatabase: CityDatabase
    ): CityDao{
        return cityDatabase.cityDao
    }

    //Provide Bookmark Database for Hilt
    @Provides
    @Singleton
    fun provideBookmarkDatabase( application: Application): BookmarkDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = BookmarkDatabase::class.java,
            name = CITY_BOOKMARK_DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    //Provide Bookmark Database for Hilt
    @Provides
    @Singleton
    fun provideBookmarkDao(
        bookmarkDatabase: BookmarkDatabase
    ): BookmarkDao{
        return bookmarkDatabase.bookmarkDao
    }

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi{
        return Retrofit.Builder()
            .baseUrl(WEATHER_API_BASE_URL)
            .client(
                OkHttpClient.Builder().apply {
                    this.addInterceptor(
                        HttpLoggingInterceptor().apply {
                            this.level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                        .connectTimeout(3,TimeUnit.SECONDS)
                        .readTimeout(20,TimeUnit.SECONDS)
                        .writeTimeout(25, TimeUnit.SECONDS)
                }
                    .certificatePinner(
                        CertificatePinner.Builder()

                            .build()
                    )
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMapApi(): MapApi {
        return Retrofit.Builder()
            .baseUrl(MAP_API_BASE_URL)
            .client(
                OkHttpClient.Builder().apply {
                    this.addInterceptor(
                        HttpLoggingInterceptor().apply {
                            this.level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                        .connectTimeout(3,TimeUnit.SECONDS)
                        .readTimeout(20,TimeUnit.SECONDS)
                        .writeTimeout(25, TimeUnit.SECONDS)
                }
                    .certificatePinner(
                        CertificatePinner.Builder()

                            .build()
                    )
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MapApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherApi: WeatherApi,
        mapApi: MapApi,
        cityDao: CityDao,
        bookmarkDao: BookmarkDao
    ): WeatherRepository{
        return WeatherRepositoryImpl(
            weatherApi = weatherApi,
            mapApi = mapApi,
            cityDao = cityDao,
            bookmarkDao = bookmarkDao
            )
    }

}