package br.ufmg.coltec.lolzinho;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by a2016951782 on 23/08/18.
 */



public class DataDeserializer implements JsonDeserializer<Data> {

    @Override
    public Data deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        JsonObject datajson = jsonObject.get("data").getAsJsonObject();
        Champion champ = new Champion();
        ArrayList<Champion> champions = new ArrayList<>();
        for(int i=0;i<10;i++) {
            JsonObject championjson = datajson.get("Ahri").getAsJsonObject();
            String championName = championjson.get("name").getAsString();
            String championTitle = championjson.get("title").getAsString();
            int championId = championjson.get("id").getAsInt();
            champ.nome = championName;
            champ.titulo = championTitle;
            champ.id = championId;
            champions.add(champ);
        }
        Data data = new Data(champions);
        return data;
    }
}
