package zen.tp02teste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

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
                Toast.makeText(MainActivity.this, address.getFormatted_address(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR - retrofit2", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
