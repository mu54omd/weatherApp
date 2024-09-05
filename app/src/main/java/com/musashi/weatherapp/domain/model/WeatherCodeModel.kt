package com.musashi.weatherapp.domain.model

import com.musashi.weatherapp.R

enum class WeatherCodeModel(val imageId: Int, val stringId: Int) {
    ClearSky(R.drawable.clear_sky, R.string.clear_sky),
    ClearSkyNight(R.drawable.clear_sky_night, R.string.clear_sky),
    MainlyClear(R.drawable.mainly_clear, R.string.mainly_clear),
    MainlyClearNight(R.drawable.mainly_clear_night, R.string.mainly_clear),
    PartlyCloudy(R.drawable.partly_cloudy, R.string.partly_cloudy),
    PartlyCloudyNight(R.drawable.partly_cloudy_night, R.string.partly_cloudy),
    Overcast(R.drawable.overcast, R.string.overcast),
    Fog(R.drawable.fog, R.string.fog),
    FogDepositingRime(R.drawable.fog_depositing_rime, R.string.fog_depositing_rime),
    DrizzleLight(R.drawable.drizzle_light, R.string.drizzle_light),
    DrizzleModerate(R.drawable.drizzle_moderate, R.string.drizzle_moderate),
    DrizzleDense(R.drawable.drizzle_dense, R.string.drizzle_dense),
    FreezingDrizzleLight(R.drawable.freezing_drizzle_light, R.string.freezing_drizzle_light),
    FreezingDrizzleDense(R.drawable.freezing_drizzle_dense, R.string.freezing_drizzle_dense),
    RainSlight(R.drawable.rain_slight, R.string.rain_slight),
    RainModerate(R.drawable.rain_moderate, R.string.rain_moderate),
    RainHeavy(R.drawable.rain_heavy, R.string.rain_heavy),
    FreezingRainLight(R.drawable.freezing_rain_light, R.string.freezing_rain_light),
    FreezingRainHeavy(R.drawable.freezing_rain_heavy, R.string.freezing_rain_heavy),
    SnowFallSlight(R.drawable.snowfall_slight, R.string.snowfall_slight),
    SnowFallModerate(R.drawable.snowfall_moderate, R.string.snowfall_moderate),
    SnowFallHeavy(R.drawable.snowfall_heavy, R.string.snowfall_heavy),
    SnowGrain(R.drawable.snow_grain, R.string.snow_grain),
    RainShowerSlight(R.drawable.rain_shower_slight, R.string.rain_shower_slight),
    RainShowerModerate(R.drawable.rain_shower_moderate, R.string.rain_shower_moderate),
    RainShowerViolent(R.drawable.rain_shower_violent, R.string.rain_shower_violent),
    SnowShowerSlight(R.drawable.snow_shower_slight, R.string.snow_shower_slight),
    SnowShowerHeavy(R.drawable.snow_shower_heavy, R.string.snow_shower_heavy),
    ThunderstormSlight(R.drawable.thunderstorm_slight, R.string.thunderstorm_slight),
    ThunderstormModerate(R.drawable.thunderstorm_moderate, R.string.thunderstorm_moderate),
    ThunderStormSlightHail(R.drawable.thunderstorm_slight_hail, R.string.thunderstorm_slight_hail),
    ThunderStormHeavyHail(R.drawable.thunderstorm_heavy_hail, R.string.thunderstorm_heavy_hail),
}