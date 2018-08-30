package zen.tp02teste;

import android.util.Log;
import android.widget.Toast;

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

public class AddressDeserializer implements JsonDeserializer<Address> {

    @Override
    public Address deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        Results results = context.deserialize(jsonObject.get("results"), Results.class);
        final Address address = new Address();
        address.setFormatted_address(results.getFormatted_address());
        address.setLat(results.getGeometry().getLocation().getLat());
        address.setLng(results.getGeometry().getLocation().getLng());
        return address;
    }
}
