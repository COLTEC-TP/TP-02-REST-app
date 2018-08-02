package zen.tp02teste;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by a2016951669 on 02/08/18.
 */

public class LocationDeserializer implements JsonDeserializer<Location> {

    @Override
    public Location deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {

        final JsonObject jsonObject = json.getAsJsonObject();
        final String lat = jsonObject.get("lat").getAsString();
        final String lng = jsonObject.get("lng").getAsString();

        final Location location = new Location();
        location.setLat(lat);
        location.setLng(lng);
        return location;
    }
}
