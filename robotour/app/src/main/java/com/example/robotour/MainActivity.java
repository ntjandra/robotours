package com.example.robotour;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapviewlite.MapScene;
import com.here.sdk.mapviewlite.MapStyle;
import com.here.sdk.mapviewlite.MapViewLite;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private MapViewLite mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a MapViewLite instance from the layout.
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        loadMapScene(13.384915, 52.530932);
    }

    private void createRequestPOI(int lat, int lng, String filter) {
        String API;

        {
        API = "http://localhost:5000/location/interest/"
                + lat + "/" + lng + "/" + filter;
        }

        Log.e(API);
        RequestQueue requestQueue=Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                API,
                null,
                new Response.Listener<JSONObject> {
                    @Overide
                    public void onResponse(JSONObject response) {
                        Log.e("Rest API Response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Overide
                    public void onErrorResponse(VolleyError error){
                        Log.e("Rest API Response", error.toString());
                    }
                }
        );

        requestQueue.add(objectRequest);
    }

    private void loadMapScene(int lat, int lng) {
        // Load a scene from the SDK to render the map with a map style.
        mapView.getMapScene().loadScene(MapStyle.NORMAL_DAY, new MapScene.LoadSceneCallback() {
            @Override
            public void onLoadScene(@Nullable MapScene.ErrorCode errorCode) {
                // int lng = 13.384915;
                // int lat = 52.530932;
                if (errorCode == null) {
                    mapView.getCamera().setTarget(new GeoCoordinates(lat, lng));
                    mapView.getCamera().setZoomLevel(14);
                } else {
                    Log.d(TAG, "onLoadScene failed: " + errorCode.toString());
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
