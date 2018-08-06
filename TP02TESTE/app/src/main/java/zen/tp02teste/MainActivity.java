package zen.tp02teste;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                        != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET}, 2);
            }
        }

        getMap("add", "");

        final EditText editTextAddress = findViewById(R.id.editTextAddress);
        editTextAddress.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER) {
                    getMap("replace", editTextAddress.getText().toString());
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editTextAddress.getWindowToken(),
                            InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    return true;
                }
                return false;
            }
        });
    }

    private void getMap(final String addReplace, String place){
        RetrofitConfig retrofitConfig = new RetrofitConfig(getBaseContext());
        retrofitConfig.getRetrofit();
        AddressService addressService = retrofitConfig.getAddressService();

        RetrofitConfig retrofitConfigCached = new RetrofitConfig(getBaseContext());
        retrofitConfigCached.getCachedRetrofit();
        AddressService addressServiceCached = retrofitConfigCached.getAddressCachedService();

        if(place.equals("")){place = "Brasil";}
        if(addReplace.equals("add")) {
            Call<Address> addressCall = addressService.getAddress(place, getResources().getString(R.string.google_maps_key));
//            Call<Address> addressCachedCall = addressServiceCached.getAddress(place, getResources().getString(R.string.google_maps_key));
//
//            addressCachedCall.enqueue(new Callback<Address>() {
//
//                @Override
//                public void onResponse(Call<Address> call, Response<Address> response) {
//                    Address address = response.body();
//                    MapsFragment.setLat(Float.parseFloat(address.getLat()));
//                    MapsFragment.setLng(Float.parseFloat(address.getLng()));
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.add(R.id.mapsFrame, new MapsFragment(), "MapsFragment");
//                    fragmentTransaction.commitAllowingStateLoss();
//                }
//
//                @Override
//                public void onFailure(Call<Address> call, Throwable t) {
//                    Log.d(TAG, t.toString());
//                }
//            });

            addressCall.enqueue(new Callback<Address>() {

                @Override
                public void onResponse(Call<Address> call, Response<Address> response) {
                    Address address = response.body();
                    MapsFragment.setLat(Float.parseFloat(address.getLat()));
                    MapsFragment.setLng(Float.parseFloat(address.getLng()));
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.mapsFrame, new MapsFragment(), "MapsFragment");
                    fragmentTransaction.commitAllowingStateLoss();
                }

                @Override
                public void onFailure(Call<Address> call, Throwable t) {
                    Log.d(TAG, t.toString());
                }
            });
        } else {
            Call<Address> addressCall = addressServiceCached.getAddress(place, getResources().getString(R.string.google_maps_key));
//            Call<Address> addressCachedCall = addressServiceCached.getAddress(place, getResources().getString(R.string.google_maps_key));
//
//            addressCachedCall.enqueue(new Callback<Address>() {
//
//                @Override
//                public void onResponse(Call<Address> call, Response<Address> response) {
//                    Address address = response.body();
//                    MapsFragment.setLat(Float.parseFloat(address.getLat()));
//                    MapsFragment.setLng(Float.parseFloat(address.getLng()));
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.mapsFrame, new MapsFragment(), "MapsFragment");
//                    fragmentTransaction.commitAllowingStateLoss();
//                }
//
//                @Override
//                public void onFailure(Call<Address> call, Throwable t) {
//                    Log.d(TAG, t.toString());
//                }
//            });

            addressCall.enqueue(new Callback<Address>() {

                @Override
                public void onResponse(Call<Address> call, Response<Address> response) {
                    Address address = response.body();
                    MapsFragment.setLat(Float.parseFloat(address.getLat()));
                    MapsFragment.setLng(Float.parseFloat(address.getLng()));
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.mapsFrame, new MapsFragment(), "MapsFragment");
                    fragmentTransaction.commitAllowingStateLoss();
                }

                @Override
                public void onFailure(Call<Address> call, Throwable t) {
                    Log.d(TAG, t.toString());
                }
            });
        }
    }

    public void getMyLocation(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mapsFrame, new ProviderFragment(), "ProviderFragment");
        fragmentTransaction.commit();
    }
}
