package com.example.macwi.auroraforecast.webservices;

import com.example.macwi.auroraforecast.utils.weather.WeatherData;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by macwi on 21.01.2018.
 */

public interface IDownloadWeatherData {

    @GET("/data/2.5/weather")
    void getCurrentWeather(@Query("lat") double lat, @Query("lon") double lon, @Query("APPID") String api_key, Callback<WeatherData> callback);

}
