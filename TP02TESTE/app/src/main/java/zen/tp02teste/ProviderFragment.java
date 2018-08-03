package zen.tp02teste;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ProviderFragment extends SupportMapFragment implements OnMapReadyCallback {

    private static final String TAG = "ProviderFragment";
    private GoogleMap mMap;
    private LocationManager locationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);
            Toast.makeText(getActivity(), "Provider: " + provider.toString(), Toast.LENGTH_LONG).show();
            mMap = googleMap;
            mMap.getUiSettings().setCompassEnabled(true);
            mMap.setMyLocationEnabled(true);
        } catch (SecurityException ex) {
            Log.e(TAG, "Error", ex);
        }
        LatLng local = new LatLng(MapsFragment.getLat(), MapsFragment.getLng());
        mMap.addMarker(new MarkerOptions().position(local).title("Marker :)"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(local));
    }

}
