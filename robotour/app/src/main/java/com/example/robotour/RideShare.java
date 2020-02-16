package com.example.robotour;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class RideShare extends AppCompatActivity {

    private static final String TAG = "lyft:Example";
    private static final String LYFT_PACKAGE = "me.lyft.android";

    private Button robotourActivate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_share);

        robotourActivate = findViewById(R.id.RideShare_btn_confirm);

        Toolbar toolBar = findViewById(R.id.topBar);
        setSupportActionBar(toolBar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        robotourActivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deepLinkIntoLyft();
            }
        });
    }
    private void deepLinkIntoLyft() {
        if (isPackageInstalled(this, LYFT_PACKAGE)) {
            //This intent will help you to launch if the package is already installed
            openLink(this, "lyft://");

            Log.d(TAG, "Lyft is already installed on your phone.");
        } else {
            openLink(this, "https://www.lyft.com/signup/SDKSIGNUP?clientId=YOUR_CLIENT_ID&sdkName=android_direct");

            Log.d(TAG, "Lyft is not currently installed on your phone..");
        }
    }

    static void openLink(Activity activity, String link) {
        Intent playStoreIntent = new Intent(Intent.ACTION_VIEW);
        playStoreIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        playStoreIntent.setData(Uri.parse(link));
        activity.startActivity(playStoreIntent);
    }

    static boolean isPackageInstalled(Context context, String packageId) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packageId, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            // ignored.
        }
        return false;
    }
}
