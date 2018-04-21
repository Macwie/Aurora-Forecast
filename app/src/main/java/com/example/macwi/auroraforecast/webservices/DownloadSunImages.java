package com.example.macwi.auroraforecast.webservices;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.macwi.auroraforecast.R;
import com.github.lzyzsd.circleprogress.DonutProgress;

import java.net.URL;

/**
 * Created by macwi on 09.01.2018.
 */

public class DownloadSunImages extends AsyncTask<URL, Integer, Boolean> {

    private View view;
    private Bitmap[] suns = new Bitmap[14];
    private ProgressBar progressBar;

    public DownloadSunImages(View view) {
        this.view = view;
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Boolean doInBackground(URL... urls) {
        try {
            for(int i=0;i<3;i++) {
                suns[i] = BitmapFactory.decodeStream(urls[i].openConnection().getInputStream());
                publishProgress((i+1)*33);
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        progressBar.setVisibility(View.GONE);
        display();
    }

    private void display() {
        Resources res = view.getResources();
        int id;

        for (int i=0;i<3;i++) {

            id = res.getIdentifier("sun"+(i+1), "id", view.getContext().getPackageName());
            ImageView sun = view.findViewById(id);
            sun.setImageBitmap(suns[i]);
        }
    }
}
