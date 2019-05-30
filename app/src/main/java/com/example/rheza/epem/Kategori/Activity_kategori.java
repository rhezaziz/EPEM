package com.example.rheza.epem.Kategori;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rheza.epem.R;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Activity_kategori extends AppCompatActivity {

    public static String MENU ;
            //"http://192.168.42.199/EPEM/";


    private List<ListKat> kats = new ArrayList<>();
    private KategoriAdapter viewAdapter;

    private EditText id_kat , nama_kat , id_jenis;
    private TextView textJenis;

    kategori kat = new kategori();


    private Spinner spinnerJenis;

    Context mContext;
    private AlertDialog.Builder dialog;
    private LayoutInflater inflater;
    private View dialogView;
    private ProgressDialog progress;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
//    @BindView(R.id.progress_bar)
//    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);



        String Menu = getIntent().getStringExtra(MENU);




        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Kategori");

        viewAdapter = new KategoriAdapter(this, kats );
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);
        recyclerView.setHasFixedSize(true);

        mContext = this;
        progress = new ProgressDialog(mContext);


        if (Menu.equals("bangunan")){
            kat.ViewKusen(recyclerView, mContext);
            Toast.makeText(this, "ini data kategori bangunan", Toast.LENGTH_SHORT).show();

        }else if (Menu.equals("mebel")){

            kat.ViewBangunan(recyclerView ,  mContext);
            Toast.makeText(this, "ini data kategori mebel" ,  Toast.LENGTH_SHORT).show();

        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tambah, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        switch (id) {
            case R.id.action_add:

                dialog = new AlertDialog.Builder(Activity_kategori.this);
                inflater = getLayoutInflater();
                dialogView = inflater.inflate(R.layout.activity__tambah_kat, null);
                dialog.setView(dialogView);
                dialog.setCancelable(false);
                dialog.setTitle("Tambah Data ");

                //id_kat = dialogView.findViewById(R.id.sId);
                nama_kat = dialogView.findViewById(R.id.sNama);
                spinnerJenis = dialogView.findViewById(R.id.spinnerKategori);
                textJenis = dialogView.findViewById(R.id.textJenis);




                dialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        proggresView();
                        if (TextUtils.isEmpty(textJenis.getText())) {
                            new AlertDialog.Builder(mContext)
                                    .setIcon(R.drawable.failed)
                                    .setTitle("Failed")
                                    .setMessage("ID Jenis Kosong")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).show();
                        } else if (TextUtils.isEmpty(nama_kat.getText())) {
                            new AlertDialog.Builder(mContext)
                                    .setIcon(R.drawable.failed)
                                    .setTitle("Failed")
                                    .setMessage("Nama Kategori Kosong")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).show();
                        } else {



                            String nama_kategori = nama_kat.getText().toString();

                            String id_jenis_kategori      = spinnerJenis.getSelectedItem().toString();


                            kat.tambahKategori( id_jenis_kategori , nama_kategori, progress, mContext);


                        }

                    }

                }).setPositiveButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

                return true;
            default:
        }

        return super.onOptionsItemSelected(item);
    }







    private void proggresView() {
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();
    }
}
