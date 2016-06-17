package com.main.yusufi.sunshine;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yusufi on 6/10/16.
 */
public class ForecastFrag extends Fragment {

    public void fetchWeatherData() {
        execNetworkConnection();
    }

    public ForecastFrag() {

    }

    public String execNetworkConnection(String cityOrPostalCode) {
        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        String jsonStr = "";

        try {

            //URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94403,us&mode=json&units=metric&cnt=7&appid=3cb8d8182ed7e98aa4564f17b58dcbc4");
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q="+cityOrPostalCode+"&mode=json&units=metric&cnt=7&appid=3cb8d8182ed7e98aa4564f17b58dcbc4");
            //URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            if (inputStream == null)
                return "";

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null)
                stringBuffer.append(line + "\n");
            if (stringBuffer.length() == 0)
                return "";

            jsonStr = stringBuffer.toString();

        } catch (IOException ex) {
            Log.e("On-----", "Error ", ex);

        }
        catch (Exception ex) {
            //Log.e("On-----", "Error ", ex);
            ex.printStackTrace();
        }
        finally
        {
            if (connection != null) {
                connection.disconnect();
            }
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            return jsonStr;
        }
        //return "";

    }

    public class  FetchWeatherTask extends AsyncTask<String,void,String[]> {

        @Override
        protected String[] doInBackground(String... params) {
            String s=execNetworkConnection(params[0]);
            publishProgress(s);
            return s;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values[0]);
        }
    }

}
