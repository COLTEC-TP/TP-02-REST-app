package com.example.a2016951790.tp_02_movieme;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("pref_key", Context.MODE_PRIVATE);
        String result = sharedPreferences.getString("user_id", "");
        DbController crud = new DbController(this);
        String valor[];
        TextView name;
        TextView mail;

        valor = crud.pegarUsuarioPorID(result);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        name = headerView.findViewById(R.id.user_name);
        mail = headerView.findViewById(R.id.user_mail);
        name.setText(valor[0]);
        mail.setText(valor[1]);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager fm = getFragmentManager();
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                if(fm.getBackStackEntryCount()>0) {
                    fm.popBackStack();
                }
                break;
            case R.id.nav_search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).commit();
                if(fm.getBackStackEntryCount()>0) {
                    fm.popBackStack();
                }
                break;
            case R.id.nav_favorite:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavoriteFragment()).commit();
                if(fm.getBackStackEntryCount()>0) {
                    fm.popBackStack();
                }
                break;
            case R.id.nav_next:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NextFragment()).commit();
                if(fm.getBackStackEntryCount()>0) {
                    fm.popBackStack();
                }
                break;
            case R.id.nav_watched:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WatchedFragment()).commit();
                if(fm.getBackStackEntryCount()>0) {
                    fm.popBackStack();
                }
                break;
            case R.id.nav_logout:
                AlertDialog builder = open(this);
                builder.show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public AlertDialog open(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast toast = Toast.makeText(context, "a",Toast.LENGTH_SHORT);
                        toast.show();
                        SharedPreferences sharedPreferences = getSharedPreferences("pref_key", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("user_id", "");
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(), EnterActivity.class);
                        finishAffinity();
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        return builder.create();
    }

}
