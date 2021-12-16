package com.example.unidad3_googlemaps;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.unidad3_googlemaps.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity<FusedLocationProviderClient> extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Location ultimaUbicacion;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        }

        while(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {

        }

     binding = ActivityMapsBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        ultimaUbicacion = manager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
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
        LatLng huesca = new LatLng(42.14, -0.408);
        LatLng zaragoza = new LatLng(41.65, -0.87);
        LatLng benasque = new LatLng(42.60, 0.52);
        //mMap.addMarker(new MarkerOptions().position(huesca).title("Marcador Huesca"));
        //mMap.addMarker(new MarkerOptions().position(zaragoza).title("Marcador Zaragoza"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(huesca));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(huesca, 8.0f));
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(huesca);
        circleOptions.radius(10000);
        circleOptions.strokeColor(Color.RED);
        //mMap.addCircle(circleOptions);
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add(huesca, zaragoza, benasque);
        polygonOptions.strokeColor(Color.BLUE);
        //mMap.addPolygon(polygonOptions);

        LatLng ubicacion = new LatLng(ultimaUbicacion.getLatitude(), ultimaUbicacion.getLongitude());
        mMap.addMarker(new MarkerOptions().position(ubicacion).title("Ubicación actual"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
    }
}