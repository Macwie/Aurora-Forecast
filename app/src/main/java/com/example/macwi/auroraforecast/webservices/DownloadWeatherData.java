package com.example.macwi.auroraforecast.webservices;

import android.content.Context;
import android.location.Location;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macwi.auroraforecast.MainActivity;
import com.example.macwi.auroraforecast.R;
import com.example.macwi.auroraforecast.utils.weather.WeatherData;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by macwi on 21.01.2018.
 */

public class DownloadWeatherData {

    private View view;
    private RestAdapter retrofit;
    private IDownloadWeatherData weatherData;
    private final String API_KEY = "85e14e9eda0c8544bc31a1372de4b1b4";
    private Location location;

    public DownloadWeatherData(View view, Location location) {
        this.view = view;
        this.location = location;
    }

    public void show() {

        if(location == null)
            return;

        retrofit = new RestAdapter.Builder()
                .setEndpoint("http://api.openweathermap.org")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        weatherData = retrofit.create(IDownloadWeatherData.class);
        weatherData.getCurrentWeather(location.getLatitude(), location.getLongitude(), API_KEY, new Callback<WeatherData>() {

            @Override
            public void success(WeatherData weatherData, Response response) {
                System.out.println(weatherData.getName());

                setTemp(weatherData.getMain().getTemp());
                setClouds(weatherData.getClouds().getAll());
                setWind(weatherData.getWind().getSpeed(), weatherData.getWind().getDeg());
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error.getMessage());
            }
        });
    }

    private void setTemp(float temp) {
        TextView tempValue = view.findViewById(R.id.tempValue);
        tempValue.setText(String.format("%.1f", (temp-273.15))+" °C");
    }

    private void setClouds(int clouds) {
        ImageView cloudImg = view.findViewById(R.id.cloudImg);
        TextView cloudPerc = view.findViewById(R.id.cloudValue);
        cloudPerc.setText(clouds+" %");


        if(clouds == 0){
            cloudImg.setImageResource(R.drawable.sun);
        }else if(clouds > 0 && clouds <= 70) {
            cloudImg.setImageResource(R.drawable.partcloud);
        }else {
            cloudImg.setImageResource(R.drawable.cloud);
        }

    }

    private void setWind(float speed, float degree) {
        ImageView windImg = view.findViewById(R.id.windImg);
        windImg.setRotation(degree);

        TextView windSpeed = view.findViewById(R.id.windValue);
        windSpeed.setText(speed+" m/s");


    }

}
