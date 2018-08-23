package coltectp.github.io.tp_02_rest_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Currency;
import java.util.Locale;

import coltectp.github.io.tp_02_rest_app.blockExplorer.activity.SimpleBlockActivity;
import coltectp.github.io.tp_02_rest_app.charts.block.BlockChart;
import coltectp.github.io.tp_02_rest_app.charts.coin.StatsCoinActivity;
import coltectp.github.io.tp_02_rest_app.charts.mining.MiningInfoActivity;
import coltectp.github.io.tp_02_rest_app.charts.pieChart.PoolChartActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView mPriceTextView;
    private TextView mPriceText2;
    private TextView mPriceText3;
    private TextView mRegion2;
    private TextView mRegion3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView imageView = findViewById(R.id.logo);

        Glide.with(this).load(R.drawable.logo).into(imageView);

        String currencyCode = displayCurrencyInfoForLocale(getResources().getConfiguration().locale);
        mPriceTextView = findViewById(R.id.price_tv);
        mPriceText2 = findViewById(R.id.price2);
        mPriceText3 = findViewById(R.id.price3);
        mRegion2 = findViewById(R.id.region2);
        mRegion3 = findViewById(R.id.region3);
        makeCall(mPriceTextView, currencyCode);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.explore) {
            Intent intent = new Intent(MainActivity.this, SimpleBlockActivity.class);
            startActivity(intent);
        } else if (id == R.id.transaction) {
            Intent intent = new Intent(MainActivity.this, BlockChart.class);
            startActivity(intent);
        } else if (id == R.id.stats_of_coin) {
            Intent intent = new Intent(MainActivity.this, StatsCoinActivity.class);
            startActivity(intent);
        } else if (id == R.id.info_mining) {
            Intent intent = new Intent(MainActivity.this, MiningInfoActivity.class);
            startActivity(intent);
        } else if (id == R.id.pools) {
            Intent intent = new Intent(MainActivity.this, PoolChartActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void makeCall(final TextView mPriceTextView, final String currencyCode) {
        BlockchainAPI service = new RetrofitConfig(MainActivity.this).
                getInfoBlockchain(MainActivity.this);

        Call<Coin> priceCall = service.getCoinPrice();

        priceCall.enqueue(new Callback<Coin>() {
            @Override
            public void onResponse(Call<Coin> call, Response<Coin> response) {
                Coin coin = response.body();

                StringBuilder priceEUR = new StringBuilder();
                priceEUR.append(String.valueOf(coin.getPriceEUR().getSymbol()))
                        .append(" ")
                        .append(String.valueOf(coin.getPriceEUR().getLast()));

                StringBuilder priceBRL = new StringBuilder();
                priceBRL.append(String.valueOf(coin.getPriceBRL().getSymbol()))
                        .append(" ")
                        .append(String.valueOf(coin.getPriceBRL().getLast()));
                mPriceTextView.setText(priceBRL.toString());

                StringBuilder priceUSD = new StringBuilder();
                priceUSD.append(String.valueOf(coin.getPriceUSD().getSymbol()))
                        .append(" ")
                        .append(String.valueOf(coin.getPriceUSD().getLast()));


                switch (currencyCode) {
                    case "EUR":
                        mPriceTextView.setText(priceEUR.toString());
                        mPriceText2.setText(priceUSD);
                        mRegion2.setText("UNITED STATES");
                        mPriceText3.setText(priceBRL);
                        mRegion3.setText("BRAZIL");
                            break;
                    case "BRL":
                        mPriceTextView.setText(priceBRL.toString());
                        mPriceText2.setText(priceUSD);
                        mRegion2.setText("ESTADOS UNIDOS");
                        mPriceText3.setText(priceEUR);
                        mRegion3.setText("EUROPA");
                            break;
                    default:
                        mPriceTextView.setText(priceUSD.toString());
                        mPriceText2.setText(priceBRL);
                        mRegion2.setText("BRAZIL");
                        mPriceText3.setText(priceEUR);
                        mRegion3.setText("EUROPA");
                            break;
                }
            }

            @Override
            public void onFailure(Call<Coin> call, Throwable t) {

            }
        });
    }


    private String displayCurrencyInfoForLocale(Locale locale) {
        Currency currency = Currency.getInstance(locale);
        return currency.getCurrencyCode();
    }
}
