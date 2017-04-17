package com.shang.testfcu;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private ArrayList<YouBike> youBikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        youBikes=new ArrayList<>();
        youBikes= (ArrayList<YouBike>) getIntent().getSerializableExtra("youbike");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /*for(int i=0;i<youBikes.size();i++){
            LatLng local=new LatLng(youBikes.get(i).getLat(),youBikes.get(i).getLng());
            mMap.addMarker(new MarkerOptions().position(local).title(youBikes.get(i).getSna()));
        }*/
        LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").snippet("TEST"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        Marker marker=mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").snippet("test"));
        mMap.setInfoWindowAdapter(adapter);



    }

    GoogleMap.InfoWindowAdapter adapter=new GoogleMap.InfoWindowAdapter() {
        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            View viwe=MapsActivity.this.getLayoutInflater().inflate(R.layout.testlayout,null);
            TextView tv1=(TextView) viwe.findViewById(R.id.textView);
            TextView tv2=(TextView) viwe.findViewById(R.id.textView2);
            ImageView ig=(ImageView)viwe.findViewById(R.id.ig);
            tv1.setText(marker.getTitle());
            tv2.setText(marker.getSnippet());
            ig.setImageResource(R.drawable.appicon);

            return viwe;
        }
    };


}
