package com.example.a2016951790.tp_02_movieme;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by a2016951790 on 14/08/18.
 */

public interface FilmeService {
    @GET("3/search/movie?api_key=7bd6324cd7f2a1ec966c76a434e3c24e&language=en-US&query=queryteste&page=1&include_adult=false")
    public Call<List<Filme>> getFilme(@Query("animal") String animal,
                                     @Query("location") String location
    );
}
