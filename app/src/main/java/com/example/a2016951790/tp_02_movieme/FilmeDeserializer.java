package com.example.a2016951790.tp_02_movieme;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a2016951790 on 14/08/18.
 */

public class FilmeDeserializer implements JsonDeserializer<List>{
    @Override
    public List<Filme> deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        List<Filme> filmes = new ArrayList<>();

        JsonArray p = jsonObject.get("results").getAsJsonArray();

        for(int i =0; i<p.size();i++){
            JsonObject obj = p.get(i).getAsJsonObject();
            Filme filme = new Filme();
            JsonArray genresid;
            int[] numbers;

            filme.setTitulo(obj.get("title").getAsString());
            filme.setAno(obj.get("release_date").getAsString());
            genresid = obj.get("genre_ids").getAsJsonArray();
            filme.setRating(obj.get("vote_average").getAsString());
            filme.setFoto(obj.get("poster_path").getAsString());

            numbers = new int[genresid.size()];

            for (int j = 0; j < genresid.size(); ++j) {
                numbers[j] = genresid.get(j).getAsInt();
            }

            filme.setGender(numbers);

            filmes.add(filme);
        }

        return (filmes);
    }
}
