package com.astudio.pamiu.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.astudio.pamiu.R;
import com.astudio.pamiu.util.GPSTracker;


public class MainActivity extends AppCompatActivity {


    public static final int CODE_FOR_ACCESS_FINE_LOCATION_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, CODE_FOR_ACCESS_FINE_LOCATION_PERMISSION);
            return;
        }

        this.getCurrentLocation();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CODE_FOR_ACCESS_FINE_LOCATION_PERMISSION && permissions.length > 0 && grantResults.length > 0) {
            if (permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户同意
                this.getCurrentLocation();
            } else {
                //用户不同意，向用户展示该权限作用
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    AlertDialog dialog = new AlertDialog.Builder(this)
                            .setMessage("App需要賦予定位服務的權限，不開啟將無法正常工作！")
                            .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }).create();
                    dialog.show();
                    return;
                }
                finish();
            }
        }
    }


    //    LocationManager mLocationManager = null;
    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {

//        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//        boolean isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        boolean isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//
//        Location location = null;
//        if (!(isGPSEnabled || isNetworkEnabled)){
//            //            Snackbar.make(mMapView, R.string.error_location_provider, Snackbar.LENGTH_INDEFINITE).show();
//            System.out.println("!(isGPSEnabled || isNetworkEnabled)");
//        }else {
//
//            if (isGPSEnabled) { //gps 開啟
//                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//                        2000, 10, mLocationListener);
//                location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            }else if (isNetworkEnabled) {
//                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
//                        2000, 10, mLocationListener);
//                location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//            }
//        }
//        if (location != null){
//            System.out.println("to do ...");
//        }

        GPSTracker mGPSTracker = new GPSTracker(this);
        Log.d("debug", "location: " + mGPSTracker.getLatitude() + " , " + mGPSTracker.getLongitude());

    }

//    private LocationListener mLocationListener = new LocationListener() {
//        @Override
//        public void onLocationChanged(Location location) {
//            if (location != null) {
//                Log.d("debug", "location: " + " ( " + location.getLatitude() + " , " + location.getLongitude() + " )");
//                mLocationManager.removeUpdates(mLocationListener);
//            } else {
//                Log.d("debug", "Location is null");
//            }
//        }
//
//        @Override
//        public void onStatusChanged(String s, int i, Bundle bundle) {
//        }
//
//        @Override
//        public void onProviderEnabled(String s) {
//        }
//
//        @Override
//        public void onProviderDisabled(String s) {
//        }
//    };


}
