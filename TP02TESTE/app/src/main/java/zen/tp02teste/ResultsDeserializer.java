package zen.tp02teste;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by a2016951669 on 02/08/18.
 */

public class ResultsDeserializer implements JsonDeserializer<Results> {

    @Override
    public Results deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {

        final JsonArray jsonArray = json.getAsJsonArray();
        final JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
        Geometry geometry = context.deserialize(jsonObject.get("geometry"), Geometry.class);
        final String formatted_address = jsonObject.get("formatted_address").getAsString();

        Results results = new Results();
        results.setGeometry(geometry);
        results.setFormatted_address(formatted_address);
        return results;
    }
}
