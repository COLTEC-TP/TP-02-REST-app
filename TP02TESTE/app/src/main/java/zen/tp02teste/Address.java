package zen.tp02teste;

import java.util.ArrayList;

/**
 * Created by a2016951669 on 02/08/18.
 */

public class Address {

    private String formatted_address;
    private ArrayList<String> location;

    Address() {
        this.setLocation(new ArrayList<String>());
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public ArrayList<String> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<String> location) {
        this.location = location;
    }
}
