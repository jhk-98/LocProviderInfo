package com.example.locproviderinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Criteria;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView txtInfo;
    LocationManager lmg;
    List<String> locProvList;
    LocationProvider locProvider;

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
            switch(locProvName){
                case "gps" :
                    locProvider = lmg.getProvider(LocationManager.GPS_PROVIDER);
                    break;
                case "network" :
                    locProvider = lmg.getProvider(LocationManager.NETWORK_PROVIDER);
                    break;

                case "passive" :
                    locProvider = lmg.getProvider(LocationManager.PASSIVE_PROVIDER);
                default: break;
            }
            printInfo(locProvider);
        }
    }

    private void printInfo(LocationProvider locProvider) {
        txtInfo.append("이름 : " + locProvider.getName() + "\n");

        txtInfo.append("현재이용가능여부: " +

                lmg.isProviderEnabled(locProvider.getName()) + "\n");

        txtInfo.append("위성필요여부: " + locProvider.requiresSatellite() + "\n");

        txtInfo.append("인터넷접속필요여부: " + locProvider.requiresNetwork() + "\n");

        txtInfo.append("기지국필요여부: " + locProvider.requiresCell() + "\n");



        txtInfo.append("고도정보지원여부: " + locProvider.supportsAltitude() + "\n");

        txtInfo.append("방향정보지원여부: " + locProvider.supportsBearing() + "\n");

        txtInfo.append("속도정보지원여부: " + locProvider.supportsSpeed() + "\n");



        String accuracy = "";

        switch(locProvider.getAccuracy()) {

            //2.3부터 사용

            case Criteria.ACCURACY_LOW:    accuracy="500m 이상 오차"; break;

            case Criteria.ACCURACY_MEDIUM: accuracy="100~500m 오차"; break;

            case Criteria.ACCURACY_HIGH:   accuracy="0~100m 오차"; break;

        }

        txtInfo.append("정확성정도: " + accuracy + "\n");



        String power = "";

        switch(locProvider.getPowerRequirement()) {

            case Criteria.POWER_LOW:    power="적게 사용"; break;

            case Criteria.POWER_MEDIUM: power="중간 사용"; break;

            case Criteria.POWER_HIGH:   power="많이 사용"; break;

        }

        txtInfo.append("전원사용정도: " + power + "\n");



        txtInfo.append("요금지불여부: " + locProvider.hasMonetaryCost() + "\n");

        txtInfo.append("\n");

    }
}