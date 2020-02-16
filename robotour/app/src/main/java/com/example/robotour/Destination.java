package com.example.robotour;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Destination extends AppCompatActivity {
    private Button btn;
    private TextView dst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        btn = findViewById(R.id.Destination_btn_destination);
        dst = findViewById(R.id.Destination_txtView_destinationName);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                queryAmadeus();
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
