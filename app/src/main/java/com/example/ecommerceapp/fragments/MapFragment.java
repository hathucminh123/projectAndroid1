package com.example.ecommerceapp.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.activities.map;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MapFragment extends AppCompatActivity {
    private static final String TAG = "MapFragment";
    private static int ERROR_DIALOG = 9001;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        if (checkService()){
            init();
        }
    }
    public void init(){
        Button btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapFragment.this , map.class);
                startActivity(intent);
            }
        });
    }
    public boolean checkService(){
        Log.d(TAG, "checkService: checking google service version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MapFragment.this);

        if(available == ConnectionResult.SUCCESS){
            Log.d(TAG, "checkService: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.d(TAG, "checkService: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MapFragment.this, available, ERROR_DIALOG);
            dialog.show();
        }else {
            Toast.makeText(this,"You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
