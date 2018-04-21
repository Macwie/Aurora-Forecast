package com.example.macwi.auroraforecast;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.macwi.auroraforecast.webservices.DownloadWeatherData;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient mFusedLocationClient;
    private Location loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setElevation(0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getDeviceLocation();

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));

        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void onProbCardClick(View v) {
        ProbOfAuroraSee probOfAuroraSee = new ProbOfAuroraSee();
        int probability = probOfAuroraSee.getProbability(loc, getWindow().getDecorView().getRootView());
        TextView probabilityInfo = findViewById(R.id.infoProbability);
        probabilityInfo.setText("There is "+probability+"% chance of seeing aurora in your location right now.");
    }

    public void onTempCardClick(View v) {
        DownloadWeatherData downloadWeatherData = new DownloadWeatherData(getWindow().getDecorView().getRootView(), loc);
        downloadWeatherData.show();
    }

    public void getDeviceLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.INTERNET
            }, 1);
            return;
        }
        else
        {
            showLoc();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1:
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    showLoc();
                return;
        }

    }

    @SuppressLint("MissingPermission")
    private void showLoc() {

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {
                            System.out.println(location.getLatitude());
                            loc = location;
                        }
                    }
                });

    }

}
