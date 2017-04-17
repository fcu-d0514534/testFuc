package com.shang.testfcu;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
        ,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private ArrayList<YouBike> youBikes;

    private Location mLocation;
    private GoogleApiClient mGoogleApiClient;
    protected static final String TAG="MapsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        youBikes = new ArrayList<>();
        youBikes = (ArrayList<YouBike>) getIntent().getSerializableExtra("youbike");
        buildGoogleApiClient();
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

        Marker marker = mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").snippet("test"));
        mMap.setInfoWindowAdapter(adapter);

        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                Toast.makeText(MapsActivity.this,"TEST mMAp",Toast.LENGTH_SHORT).show();
                return true;
            }
        });;
        mMap.setMyLocationEnabled(true);


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

    protected synchronized void buildGoogleApiClient(){
        mGoogleApiClient=new GoogleApiClient.Builder(MapsActivity.this)
                .addConnectionCallbacks(MapsActivity.this)
                .addOnConnectionFailedListener(MapsActivity.this)
                .addApi(LocationServices.API)
                .build();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocation=LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(mLocation!=null){
            Log.d(TAG,mLocation.getLatitude()+"");
            Log.d(TAG,mLocation.getLongitude()+"");
        }else{
            Toast.makeText(this,"NULL",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }


}
