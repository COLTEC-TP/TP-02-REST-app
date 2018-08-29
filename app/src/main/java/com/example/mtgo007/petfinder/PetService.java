package com.example.mtgo007.petfinder;

/**
 * Created by a2016952827 on 07/08/18.
 */
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PetService {

    @GET("pet.find?key=e8399e728f691a086c1769fd314bf83e&format=json")
    public Call<List<Pet>> getPets(@Query("animal") String animal,
                                   @Query("location") String location
                                  );

}