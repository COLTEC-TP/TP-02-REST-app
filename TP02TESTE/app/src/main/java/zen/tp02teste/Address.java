package zen.tp02teste;

import java.util.ArrayList;

/**
 * Created by a2016951669 on 02/08/18.
 */

public class Address {

    private String formatted_address;
    private String lat;
    private String lng;

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
