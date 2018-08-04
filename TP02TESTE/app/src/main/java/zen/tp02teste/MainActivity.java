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

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        fragmentManager = getSupportFragmentManager();
        AddressService service = new RetrofitConfig().getEnderecoService();
        Call<Address> enderecoCall = service.getAddress("Brasil");

        enderecoCall.enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
                Address address = response.body();
                MapsFragment.setLat(Float.parseFloat(address.getLat().toString()));
                MapsFragment.setLng(Float.parseFloat(address.getLng().toString()));

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.mapsFrame, new MapsFragment(), "MapsFragment");
                fragmentTransaction.commitAllowingStateLoss();
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {
                Log.d("ERROR - retrofit2", t.toString());
                Toast.makeText(MainActivity.this, "ERROR - retrofit2", Toast.LENGTH_SHORT).show();
            }
        });

        final EditText editTextAddress = findViewById(R.id.editTextAddress);
        editTextAddress.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER) {
                    AddressService service = new RetrofitConfig().getEnderecoService();
                    Call<Address> newLocationCall = service.getAddress(editTextAddress.getText().toString());
                    newLocationCall.enqueue(new Callback<Address>() {
                        @Override
                        public void onResponse(Call<Address> call, Response<Address> response) {
                            Address address = response.body();
                            MapsFragment.setLat(Float.parseFloat(address.getLat().toString()));
                            MapsFragment.setLng(Float.parseFloat(address.getLng().toString()));
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.mapsFrame, new MapsFragment(), "MapsFragment");
                            fragmentTransaction.commit();
                        }

                        @Override
                        public void onFailure(Call<Address> call, Throwable t) {
                            Log.d("ERROR - retrofit2", t.toString());
                            Toast.makeText(MainActivity.this, "ERROR - retrofit2", Toast.LENGTH_SHORT).show();
                        }
                    });
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editTextAddress.getWindowToken(),
                            InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    return true;
                }
                return false;
            }
        });
    }

    public void getMyLocation(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mapsFrame, new ProviderFragment(), "ProviderFragment");
        fragmentTransaction.commit();
    }
}
