package zen.tp02teste;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static float lat;
    private static float lng;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setPadding(0, 100, 0, 0);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        LatLng local = new LatLng(MapsFragment.getLat(), MapsFragment.getLng());
        mMap.addMarker(new MarkerOptions().position(local).title("Marker :)"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(local));
    }

    public static float getLat() {
        return MapsFragment.lat;
    }
    public static void setLat(float lat) {
        MapsFragment.lat = lat;
    }
    public static float getLng() {
        return MapsFragment.lng;
    }
    public static void setLng(float lng) {
        MapsFragment.lng = lng;
    }
}
