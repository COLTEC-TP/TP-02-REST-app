package zen.tp02teste;

import android.util.Log;

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

public class GeometryDeserializer implements JsonDeserializer<Geometry>{

    @Override
    public Geometry deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        Location location = context.deserialize(jsonObject.get("location"), Location.class);

        final Geometry geometry = new Geometry();
        geometry.setLocation(location);
        return geometry;
    }
}
