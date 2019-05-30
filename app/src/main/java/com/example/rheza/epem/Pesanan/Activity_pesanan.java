package com.example.rheza.epem.Pesanan;


import android.app.ProgressDialog;
import android.content.Context;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.widget.EditText;

import android.widget.TextView;



import com.example.rheza.epem.R;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Activity_pesanan extends AppCompatActivity {

    public static final String URL = "http://192.168.42.199/EPEM/";
            //"http://192.168.42.199/EPEM/";
    public static final String WA = "https://wa.me/";

    private List<ListPesanan> pesanans = new ArrayList<>();
    private PesananAdapter viewAdapter;

    private EditText id_pes , nama_pes , no_pes , alamatPes;
    private TextView statusR;

    pesanan pesanan = new pesanan();
    //produk produk = new produk();

    Context mContext;
    private AlertDialog.Builder dialog;
    private LayoutInflater inflater;
    private View dialogView;
    private ProgressDialog progress;

    @BindView(R.id.recyclerView1)
    RecyclerView recyclerView;
//    @BindView(R.id.progress_bar)
//    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pesanan");

        viewAdapter = new PesananAdapter(this, pesanans );
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);

        //loadData();
        mContext = this;
        progress = new ProgressDialog(mContext);
        pesanan.ViewPesanan(recyclerView, mContext);

    }
//
}
