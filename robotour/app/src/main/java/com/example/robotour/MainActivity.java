package com.example.robotour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    protected LocationManager locationManager;
    private Location location;

    private String searcher;

    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged (Location location){
        }

        @Override
        public void onProviderDisabled (String provider){

        }

        @Override
        public void onProviderEnabled (String provider){

        }

        @Override
        public void onStatusChanged (String provider,int status, Bundle extras){

        }
    };
    private SearchView searchView;
    private Button btn;

    // flag for GPS status
    public boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;

    private long LOCATION_REFRESH_TIME = 10000;// REFERESH TIME EXPRESSED IN MILLISECONDS

    private float LOCATION_REFRESH_DISTANCE = 10; // METERS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.MainActivity_srcview_location);
        btn = findViewById(R.id.MainActivity_btn_useCurrentLocation);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }
        if (checkLocationPermission()) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                    LOCATION_REFRESH_DISTANCE, locationListener);
        }


        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(getLocation())
                {
                    if(checkLocationPermission())
                    {
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        Toast.makeText(MainActivity.this,"Location" + location.getLongitude() + "" +
                                location.getLatitude(),Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(MainActivity.this, Destination.class);
                    intent.putExtra("Longitude", location.getLongitude());
                    intent.putExtra("Latitude", location.getLatitude());
                    startActivity(intent);
                }
            }
        });
        searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != "") {
                    searcher = newText;
                }
                return false;
            }
        });
    }
    public boolean checkLocationPermission() {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = this.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }
    public boolean getLocation() {
        // getting GPS status
        isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);


        // getting network status
        isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if(isGPSEnabled && isNetworkEnabled &&internetIsConnected())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean internetIsConnected() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
