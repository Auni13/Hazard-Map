package com.example.projectgroup;


import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.projectgroup.databinding.ActivityMapsBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Vector;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    MarkerOptions marker;
    LatLng centerlocation;

    Vector<MarkerOptions> markerOptions;

    private String URl ="http://";
    RequestQueue requestQueue;
    Gson gson;
    Information[] informations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        gson = new GsonBuilder().create();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        centerlocation = new LatLng(3.0,101);

        markerOptions = new Vector<>();

        markerOptions.add(marker = new MarkerOptions().title("Cawangan Alor Setar")
                .position(new LatLng(6.12,100.37))
                .snippet("Open: 8am - 5pm")
        );
        markerOptions.add(marker = new MarkerOptions().title("Cawangan Bayan Lepas")
                .position(new LatLng(5.29,100.259))
                .snippet("Open: 8am - 5pm")
        );
        markerOptions.add(marker = new MarkerOptions().title("Cawangan Ipoh")
                .position(new LatLng(4.61,101.08))
                .snippet("Open: 8am - 5pm")
        );
        markerOptions.add(marker = new MarkerOptions().title("Cawangan Gua Musang")
                .position(new LatLng(4.8721,100.96))
                .snippet("Open: 8am - 5pm")
        );
        markerOptions.add(marker = new MarkerOptions().title("Cawangan Arau")
                .position(new LatLng(6.45,100.29))
                .snippet("Open: 8am - 5pm")
        );


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        for (MarkerOptions mark: markerOptions) {
            mMap.addMarker(mark);
        }
        enableMyLocation();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerlocation,8));
        sendRequest();
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    @SuppressLint("MissingPermission")
    private void enableMyLocation() {
        // 1. Check if permissions are granted, if so, enable the my location layer
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
        else{
            String perms[]={"android.permission.ACCESS_FINE_LOCATION"};
            ActivityCompat.requestPermissions(this,perms,200);
        }

        // 2. Otherwise, request location permissions from the user.


    }

    public void sendRequest(){
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL,onSuccess,onError);
        requestQueue.add(stringRequest);
    }

    public Response.Listener<String> onSuccess = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            informations = gson.fromJson(response, Information[].class);
            Log.d("Maklumat", "Number of Maklumat Data Point:" + informations.length);

            if(informations.length <1){
                Toast.makeText(getApplicationContext(), "Problem retrieving JSON data", Toast.LENGTH_LONG).show();
                return;
            }

            for(Information info: informations){
                Double lat = Double.parseDouble(info.hzLat);
                Double lng = Double.parseDouble(info.hzLng);
                String title = info.hzLocation;
                String snippet = info.hzType;

                MarkerOptions marker = new MarkerOptions().position(new LatLng(lat,lng))
                        .title(title)
                        .snippet(snippet)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));

                mMap.addMarker(marker);
            }
        }
    };

    public Response.ErrorListener onError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
        }
    };
}