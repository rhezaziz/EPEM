package com.example.rheza.epem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rheza.epem.InfoDiri.Activity_dataDiri;
import com.example.rheza.epem.Kategori2.Activity_pilih;
import com.example.rheza.epem.Pesanan.Activity_pesanan;
import com.example.rheza.epem.Produk.Activity_produk;

public class Activity_admin extends AppCompatActivity {

    //implements NavigationView.OnNavigationItemSelectedListener
    CardView kategori , produk , pesanan , infoDiri , testimoni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);

        kategori = (findViewById(R.id.kategori));
        produk = (findViewById(R.id.produk));
        pesanan = (findViewById(R.id.pesanan));
        infoDiri = (findViewById(R.id.info));
        testimoni = (findViewById(R.id.testimoni));
        kategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(Activity_admin.this , Activity_pilih.class);

                startActivity(intent);
            }
        });

        pesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(Activity_admin.this , Activity_pesanan.class);

                startActivity(intent);
            }
        });
        produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                               Intent intent = new Intent(Activity_admin.this , Activity_produk.class);

                startActivity(intent);
            }
        });
        infoDiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Activity_admin.this , Activity_dataDiri.class);
                startActivity(intent);
            }

        });
        testimoni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_admin;
                Bundle extras = getIntent().getExtras();

                id_admin = extras.getString("id_admin");
                Intent intent = new Intent(Activity_admin.this , Activity_pilih.class);
                intent.putExtra("id_admin", id_admin);
                startActivity(intent);
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
        getMenuInflater().inflate(R.menu.activity_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }



}
