package coltectp.github.io.tp_02_rest_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import coltectp.github.io.tp_02_rest_app.blockExplorer.activity.SimpleBlockActivity;
import coltectp.github.io.tp_02_rest_app.charts.Chart;
import coltectp.github.io.tp_02_rest_app.charts.Point;
import coltectp.github.io.tp_02_rest_app.charts.block.BlockChart;
import coltectp.github.io.tp_02_rest_app.charts.coin.StatsCoinActivity;
import coltectp.github.io.tp_02_rest_app.charts.mining.MiningInfoActivity;
import coltectp.github.io.tp_02_rest_app.charts.pieChart.PoolChartActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final String currency = "BRL";

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

        final TextView lbl_price_usd = findViewById(R.id.lbl_price_usd);
        final TextView lbl_price_brl = findViewById(R.id.lbl_price_brl);
        final TextView lbl_price_eur = findViewById(R.id.lbl_price_eur);


        // Get market price of coins
        BlockchainAPI service = new RetrofitConfig(MainActivity.this).getInfoBlockchain(MainActivity.this);


        Call<Coin> priceCall = service.getCoinPrice();

        priceCall.enqueue(new Callback<Coin>() {
            @Override
            public void onResponse(Call<Coin> call, Response<Coin> response) {
                Coin coin = response.body();

                Float priceUSD = coin.getPriceUSD().getLast();
                String symbolUSD= coin.getPriceUSD().getSymbol();

                Float priceBRL = coin.getPriceBRL().getLast();
                String symbolBRL = coin.getPriceBRL().getSymbol();

                Float priceEUR = coin.getPriceEUR().getLast();
                String symbolEUR = coin.getPriceEUR().getSymbol();


                lbl_price_usd.setText(priceUSD.toString() + " " + symbolUSD);
                lbl_price_brl.setText(priceBRL.toString() + " " + symbolBRL);
                lbl_price_eur.setText(priceEUR.toString() + " " + symbolEUR);


            }

            @Override
            public void onFailure(Call<Coin> call, Throwable t) {

            }
        });

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
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
