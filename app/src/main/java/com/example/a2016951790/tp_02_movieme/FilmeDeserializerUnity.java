package com.example.a2016951790.tp_02_movieme;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by a2016951790 on 14/08/18.
 */

public class FilmeDeserializerUnity implements JsonDeserializer<Filme>{
    @Override
    public Filme deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
        final JsonObject obj = json.getAsJsonObject();

            Filme filme = new Filme();
            JsonArray genresid;
            int[] numbers;

            filme.setTitulo(obj.get("title").getAsString());
            filme.setAno(obj.get("release_date").getAsString());
            filme.setCartaz(obj.get("backdrop_path").getAsString());
            filme.setSubtitle(obj.get("tagline").getAsString());
            filme.setRating(obj.get("vote_average").getAsString());
            filme.setDescription(obj.get("overview").getAsString());
            filme.setFoto(obj.get("poster_path").getAsString());
            filme.setId(obj.get("id").getAsInt());

            genresid = obj.get("genre_ids").getAsJsonArray();
            numbers = new int[genresid.size()];

            for (int j = 0; j < genresid.size(); ++j) {
                numbers[j] = genresid.get(j).getAsInt();
            }

            filme.setGender(numbers);

        return (filme);
    }
}
