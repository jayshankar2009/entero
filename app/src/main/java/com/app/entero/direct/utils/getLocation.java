package com.app.entero.direct.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;

import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;
import android.location.LocationListener;

import com.app.entero.direct.ui.activity.salesman.Visit_PlanActivity_Salesman;

import static android.content.Context.MODE_PRIVATE;

public  class getLocation  {
    private Context ctx;
    LocationManager mLocationManager;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0;
    private static final long MIN_TIME_BW_UPDATES = 15 * 60000;

    public getLocation(Activity ctx) {
        this.ctx = ctx;
      //  get_location();
           }

    public boolean get_location(){
        mLocationManager = (LocationManager) ctx.getSystemService(ctx.LOCATION_SERVICE);
        //  mLocationManager.getLastKnownLocation();
        Boolean isGPSEnabled = mLocationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(isGPSEnabled) {
            if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return true;

            }
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    MIN_TIME_BW_UPDATES,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES, (LocationListener) this);
      //      accessKey = pref.getString("key", "");
            Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (location != null) {

              /*  String adress = LocationAddress.getAddressFromLocation(location.getLatitude(), location.getLongitude(), ctx);
                if (adress != null) {
                    txt_address.setText(adress);
                    // txt_address1.setText(LocationAddress.getAddressFromLocation(location.getLatitude(), location.getLongitude(), ctx));
                    pgr.setVisibility(View.GONE);
                }*/
            }

        } else {

            showSettingsAlert();
            return false;
        }
return true;
        }


    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);

        alertDialog.setTitle("GPS Not Enabled");

        alertDialog.setMessage("Please enable the Location");

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                ctx.startActivity(intent);
            }
        });


        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ctx,"Location Services not enabled !. Unable to get the location", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
    public void checkLocation(Activity mContext){
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(mContext, new String[] { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION }, 1);
        }
    }
}
