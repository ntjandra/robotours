package com.example.robotour;

import android.app.DownloadManager;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Destination extends AppCompatActivity {

    private Location location;
    private Button randomize;
    private Button bookRide;

    private Spinner filter;

    private String[] filterCat;

    private TextView dst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_destination);

        Toolbar toolBar = findViewById(R.id.topBar);
        setSupportActionBar(toolBar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        randomize = findViewById(R.id.Destination_btn_destination);
        dst = findViewById(R.id.Destination_txtView_destinationName);
        bookRide = findViewById(R.id.Destination_btn_bookride);
        filter = findViewById(R.id.Destination_spn_filter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.filters, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterCat = getResources().getStringArray(R.array.filters);
        // Apply the adapter to the spinner
        filter.setAdapter(adapter);

        filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //QueryData(filterCat[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        randomize.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                queryAmadeus();
            }
        });
        bookRide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Destination.this, RideShare.class));
            }
        });
        dst.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Destination.this, DestinationInformation.class));
            }
        });
    }
    public void queryAmadeus()
    {

    }
}
