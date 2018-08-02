package zen.tp02teste;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        AddressService service = new RetrofitConfig().getEnderecoService();
        Call<Address> enderecoCall = service.getAddress("Brasil");

        enderecoCall.enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
                Address address = response.body();
                MapsFragment.setLat(Float.parseFloat(address.getLat().toString()));
                MapsFragment.setLng(Float.parseFloat(address.getLng().toString()));
                fragmentManager = getSupportFragmentManager();
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
    }
}
