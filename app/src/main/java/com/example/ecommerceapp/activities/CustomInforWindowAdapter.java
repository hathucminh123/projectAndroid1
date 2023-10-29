package com.example.ecommerceapp.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ecommerceapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInforWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private final View window;
    private Context mContext;

    public CustomInforWindowAdapter( Context mContext) {
        this.window = LayoutInflater.from(mContext).inflate(R.layout.custom_info_window, null);
        this.mContext = mContext;
    }
    private void renderWindowText(Marker marker, View view){
        String title = marker.getTitle();
        TextView tvTitle = view.findViewById(R.id.mapTitle);
        if (!title.isEmpty()){
            tvTitle.setText(title);
        }
        String snippet = marker.getSnippet();
        TextView tvSnippet = view.findViewById(R.id.mapSnippet);
        if (!snippet.isEmpty()){
            tvSnippet.setText(snippet);
        }
    }

    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {
        renderWindowText(marker,window);
        return window;
    }

    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        renderWindowText(marker,window);
        return window;
    }
}
