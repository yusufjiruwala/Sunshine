package com.main.yusufi.sunshine;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView rootView = (ListView) findViewById(R.id.listview_sforecast);
        onViewCreated(rootView,savedInstanceState);
        String[] arrayStr = new String[]{"Yusuf", "Moiz", "jiruwala"};
        List<String> al = new ArrayList<String>(Arrays.asList(arrayStr));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, R.layout.list_item_forecast, R.id.listview_sforecast, al);
        rootView.setAdapter(adapter);
        adapter.add("hh");

        ForecastFrag fc=new ForecastFrag();
        fc.execNetworkConnection();

    }

    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();

            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_item_forecast, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
