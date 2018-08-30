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
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FragmentManager fragmentManager;
    private RetrofitConfig retrofitConfig;
    private AutoCompleteTextView acTextAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE)
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
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 3);
            }
        }

        fragmentManager = getSupportFragmentManager();
        File file = new File(getCacheDir().getAbsolutePath() + "/cacheRetrofit");
        retrofitConfig = new RetrofitConfig(getBaseContext(), file);

        ArrayList<String> placesList = new ArrayList();
        try {
            Iterator<String> iterator = retrofitConfig.getOkHttpClient().cache().urls();
            while(iterator.hasNext()) {
                String decoded = URLDecoder.decode(iterator.next().split("\\?")[1].split("&")[0].split("=")[1], "UTF-8");
                if(!placesList.contains(decoded)) {
                    placesList.add(decoded);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, placesList);
        acTextAddress = (AutoCompleteTextView) findViewById(R.id.ACTextAddress);
        acTextAddress.setMaxLines(4);
        acTextAddress.setThreshold(1);
        acTextAddress.setAdapter(adapter);

        getMap("add", "Brasil");

        final EditText ACTextAddress = findViewById(R.id.ACTextAddress);
        ACTextAddress.setOnKeyListener((v, keyCode, event) -> {
            if(keyCode == KeyEvent.KEYCODE_ENTER && ACTextAddress.length() > 0) {
                getMap("replace", ACTextAddress.getText().toString());
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(ACTextAddress.getWindowToken(),
                        InputMethodManager.RESULT_UNCHANGED_SHOWN);
                return true;
            }
            return false;
        });
    }

    private void getMap(final String addReplaceURL, String place){
        AddressService addressService = retrofitConfig.getAddressCachedService();

        if(addReplaceURL.equals("add")) {
            Call<Address> addressCall = addressService.getAddress(place, getResources().getString(R.string.google_maps_key));
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
        } else if(addReplaceURL.equals("replace")){
            Call<Address> addressCall = addressService.getAddress(place, getResources().getString(R.string.google_maps_key));
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

        ArrayList<String> placesList = new ArrayList();
        try {
            Iterator<String> iterator = retrofitConfig.getOkHttpClient().cache().urls();
            while(iterator.hasNext()) {
                String decoded = URLDecoder.decode(iterator.next().split("\\?")[1].split("&")[0].split("=")[1], "UTF-8");
                if(!placesList.contains(decoded)) {
                    placesList.add(decoded);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = (ArrayAdapter<String>)acTextAddress.getAdapter();
        adapter.clear();
        adapter.addAll(placesList);
        adapter.notifyDataSetChanged();
    }

    public void getMyLocation(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mapsFrame, new ProviderFragment(), "ProviderFragment");
        fragmentTransaction.commit();
    }
}
