package com.example.mtgo007.petfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PetService service = new RetrofitConfig().getPetService();
        Call<List<Pet>> petCall = service.getPets("cat", "90001");

        petCall.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {

            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {

            }
        });
    }
}
