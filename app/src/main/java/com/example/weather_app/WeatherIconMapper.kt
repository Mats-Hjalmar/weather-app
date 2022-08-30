package com.example.weather_app

import com.example.weather_app.R.drawable.*

class WeatherIconMapper {
    companion object {
        fun map(weatherIcon: Int): Int? {
            return pairs[weatherIcon]
        }

        private val pairs : Map<Int, Int> = mapOf(
            113 to property_1_sun,
            116 to property_1_partly_cloudy,
            119 to property_1_cloudy,
            122 to property_1_cloudy,
            143 to property_1_fog,

            176 to property_1_cloud_rain,
            179 to property_1_cloud_snow,
            182 to property_1_cloud_snow,
            185 to property_1_hale,
            200 to property_1_lightning,
            227 to property_1_snow,
            230 to property_1_snow,
            248 to property_1_fog,
            260 to property_1_snowflake,
            263 to property_1_fog,
            266 to property_1_fog,
            281 to property_1_hale,
            284 to property_1_snow,
            293 to property_1_cloud_rain,
            296 to property_1_cloud_rain,
            299 to property_1_rain,
            302 to property_1_rain,
            305 to property_1_rain,
            308 to property_1_rain,
            311 to property_1_cloud_snow,
        )
    }
}