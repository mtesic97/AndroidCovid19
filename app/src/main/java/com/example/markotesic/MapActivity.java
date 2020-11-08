package com.example.markotesic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.markotesic.model.CovidSummaryResponse;
import com.example.markotesic.model.Global;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapActivity extends AppCompatActivity implements PermissionsListener {

    TextView confirmed;
    TextView deadths;
    TextView recovered;

    Retrofit retrofit;

    Button buttontolist;
    Button buttontosymptoms;

    PermissionsManager permissionsManager;

    MapboxMap mapboxMap;
    Style style;

    private  MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(getApplicationContext(),getString(R.string.mapbox_access_token));

        setContentView(R.layout.activity_map);
        confirmed=findViewById(R.id.mapconfirmedcases);
        deadths=findViewById(R.id.mapdeadthscases);
        recovered=findViewById(R.id.maprecoveredcases);
        mapView=findViewById(R.id.mapmapview);
        buttontolist=findViewById(R.id.mapbuttontolist);
        buttontosymptoms=findViewById(R.id.mapbuttontosymptoms);
        buttontolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MapActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });
        buttontosymptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MapActivity.this,SymptomActivity.class);
                startActivity(intent);
            }
        });

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                MapActivity.this.mapboxMap=mapboxMap;
                mapboxMap.addOnMapClickListener(new MapboxMap.OnMapClickListener() {
                    @Override
                    public boolean onMapClick(@NonNull LatLng point) {
                        return false;
                    }
                });
                mapboxMap.setStyle(Style.OUTDOORS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        MapActivity.this.style=style;
                        enableLocationComponent();

                    }
                });
            }
        });

        retrofit=new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        Covid19API covid19API=retrofit.create(Covid19API.class);
        Call<CovidSummaryResponse> call=covid19API.getWorldSummary();
        call.enqueue(new Callback<CovidSummaryResponse>() {
            @Override
            public void onResponse(Call<CovidSummaryResponse> call, Response<CovidSummaryResponse> response) {
                if(response.isSuccessful()){
                    CovidSummaryResponse summaryResponse=response.body();
                    Global global=summaryResponse.getGlobal();
                    confirmed.setText("New confirmed:"+global.getNewConfirmed()+"\nTotal confirmed:"+global.getTotalConfirmed());
                    deadths.setText("New deadths:"+global.getNewDeaths()+ "\nTotal deadths:"+global.getTotalDeath());
                    recovered.setText("New recovered:"+global.getNewRecovered()+"\nTotal recovered:"+global.getTotalRecovered());

                }
            }

            @Override
            public void onFailure(Call<CovidSummaryResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Neuspesan zahtev",Toast.LENGTH_LONG).show();
            }
        });
    }
    @SuppressWarnings( {"MissingPermission"})
    private void enableLocationComponent() {

        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            // Get an instance of the component
            LocationComponent locationComponent = mapboxMap.getLocationComponent();

            // Activate with a built LocationComponentActivationOptions object
            locationComponent.activateLocationComponent(LocationComponentActivationOptions.builder(this, style).build());

            // Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

            // Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

            // Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);

        } else {

            permissionsManager = new PermissionsManager(this);

            permissionsManager.requestLocationPermissions(this);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(getApplicationContext(),"Neuspesan zahtev",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if(granted){
           mapboxMap.getStyle(new Style.OnStyleLoaded() {
               @Override
               public void onStyleLoaded(@NonNull Style style) {
                   MapActivity.this.style=style;
                   enableLocationComponent();
               }
           });
        }
    }
}