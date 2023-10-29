package com.example.ecommerceapp.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.ecommerceapp.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.type.LatLng;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class map extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker mMarker;
    private static final String TAG = "map";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private Boolean mLocationPermissionGranted =false;
    private static int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
        FloatingActionButton btnHome = findViewById(R.id.floatingActionButton);
        FloatingActionButton btnInfo = findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMarker.isInfoWindowShown()){
                    mMarker.hideInfoWindow();
                }else{

                    mMarker.showInfoWindow();
                }
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(map.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initMap(){
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        //10.841150174601632, 106.80988353264051
        com.google.android.gms.maps.model.LatLng shop = new com.google.android.gms.maps.model.LatLng(10.841150174601632, 106.80988353264051);

        float zoomLevel = 18.5f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shop,zoomLevel));
        mMap.clear();

        mMap.setInfoWindowAdapter(new CustomInforWindowAdapter(map.this));
        String snippets = "Address: Lô E2a-7, Đường D1, Khu Công nghệ cao, P.Long Thạnh Mỹ, Tp. Thủ Đức, TP.HCM." + "\n" +
                "Phone number: 0918860840" + "\n" +
                "Email: tritran7985@icloud.com";


        MarkerOptions options = new MarkerOptions()
                .position(shop)
                .title("Rainbow Bandits")
                .snippet(snippets);
        mMarker = mMap.addMarker(options);


    }
    private void getPermission(){
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(), COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionGranted = true;
            }else {
                ActivityCompat.requestPermissions(this, permissions,LOCATION_PERMISSION_REQUEST_CODE);

            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       mLocationPermissionGranted = false;
       if (requestCode == LOCATION_PERMISSION_REQUEST_CODE){
           if (grantResults.length > 0){
               for (int i = 0; i <grantResults.length; i++){
                   if (grantResults[i] != PackageManager.PERMISSION_GRANTED){
                       mLocationPermissionGranted = false;
                        return;
                   }
               }
               mLocationPermissionGranted = true;
               initMap();
           }
       }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
