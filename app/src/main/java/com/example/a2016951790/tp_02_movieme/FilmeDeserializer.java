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

        JsonObject resp = jsonObject.get("petfinder").getAsJsonObject();
        resp = resp.get("pets").getAsJsonObject();
        JsonArray p = resp.get("pet").getAsJsonArray();

        for(int i =0; i<p.size();i++){
            JsonObject obj = p.get(i).getAsJsonObject();
            Filme filme = new Filme();

            filme.setTitulo(obj.get("title").getAsString());
            filme.setAno(obj.get("release_date").getAsString());
            filme.setDiretor(obj.get("title").getAsString());
            filme.setRating(obj.get("vote_average").getAsString());
            filme.setFoto(obj.get("poster_path").getAsInt());
            filmes.add(filme);
        }

        return (filmes);
    }
}
