package com.example.macwi.auroraforecast;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.macwi.auroraforecast.utils.weather.WeatherData;
import com.example.macwi.auroraforecast.webservices.DownloadKPData;
import com.example.macwi.auroraforecast.webservices.DownloadKPForecast;
import com.example.macwi.auroraforecast.webservices.DownloadMagnetometerData;
import com.example.macwi.auroraforecast.webservices.DownloadSolarWindData;
import com.example.macwi.auroraforecast.webservices.DownloadSunImages;
import com.example.macwi.auroraforecast.webservices.DownloadWeatherData;
import com.example.macwi.auroraforecast.webservices.IDownloadWeatherData;

import java.net.MalformedURLException;
import java.net.URL;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by macwi on 30.12.2017.
 */

public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        if(mPage == 1) {
            view = inflater.inflate(R.layout.aurora_fragment, container, false);
            initView(view);
        }
        else if(mPage == 2) {
            view = inflater.inflate(R.layout.weather_fragment, container, false);
            initView(view);
        }
        else if(mPage == 3) {
            view = inflater.inflate(R.layout.sun_fragment, container, false);
            initView(view);
        }

        return view;
    }

    private void initView(View view) {

        switch (mPage) {
            case 1:
                DownloadKPData downloadKPData = new DownloadKPData(view);
                DownloadMagnetometerData downloadMagnetometerData = new DownloadMagnetometerData(view);
                DownloadSolarWindData downloadSolarWindData = new DownloadSolarWindData(view);
                DownloadKPForecast downloadKPForecast = new DownloadKPForecast(view);

                try {
                    downloadKPData.execute(new URL("http://services.swpc.noaa.gov/text/wing-kp.txt"));
                    downloadMagnetometerData.execute(new URL("http://services.swpc.noaa.gov/text/ace-magnetometer.txt"));
                    downloadSolarWindData.execute(new URL("http://services.swpc.noaa.gov/text/ace-swepam.txt"));
                    downloadKPForecast.execute(new URL("http://services.swpc.noaa.gov/text/27-day-outlook.txt"));

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                break;
            case 3:
                DownloadSunImages downloadSunImages = new DownloadSunImages(view);
                try {
                    downloadSunImages.execute(new URL("https://sdo.gsfc.nasa.gov/assets/img/latest/latest_512_0193.jpg"),
                            new URL("https://sohowww.nascom.nasa.gov/data/synoptic/sunspots_earth/mdi_sunspots.jpg"),
                             new URL("https://sohowww.nascom.nasa.gov/data/realtime/c2/512/latest.jpg"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

}
