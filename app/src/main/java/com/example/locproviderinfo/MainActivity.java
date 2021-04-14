package com.example.locproviderinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView txtInfo;
    LocationManager lmg;
    List<String> locProvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInfo = findViewById(R.id.txtInfo);

        lmg = (LocationManager) getSystemService(LOCATION_SERVICE);

        locProvList = lmg.getAllProviders();
        txtInfo.setText("");
        for(String locProvName : locProvList){
            txtInfo.append("Loc. Provider: " + locProvName + "\n");
            txtInfo.append("Enabled: " + lmg.isProviderEnabled(locProvName) + "\n\n" );
        }
    }
}