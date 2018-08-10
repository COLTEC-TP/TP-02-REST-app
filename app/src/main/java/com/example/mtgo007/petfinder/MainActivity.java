package com.example.mtgo007.petfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText zipcode = findViewById(R.id.zipcode);
        Button btn = findViewById(R.id.btnSearch);
        final Spinner spinner = (Spinner) findViewById(R.id.pet_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pets_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(zipcode.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Zipcode nulo", Toast.LENGTH_SHORT).show();
                } else {
                    PetService service = new RetrofitConfig().getPetService();
                    Call<List<Pet>> petCall = service.getPets(spinner.getSelectedItem().toString(), zipcode.getText().toString());

                    petCall.enqueue(new Callback<List<Pet>>() {
                        @Override
                        public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                            List<Pet> pets = response.body();
                            ListView languagesListView = findViewById(R.id.pet_list);
                            languagesListView.setAdapter(new PetAdapter(MainActivity.this, pets));

                        }

                        @Override
                        public void onFailure(Call<List<Pet>> call, Throwable t) {
                            t.printStackTrace();
                            Toast.makeText(MainActivity.this, "Falha ao Conectar Com o Servidor", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }
}
