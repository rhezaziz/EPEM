package com.example.rheza.epem.Testimoni;

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
import android.widget.ImageView;
import android.widget.TextView;


import com.example.rheza.epem.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activity_testimoni extends AppCompatActivity {


//    private List<listTestimoni> listTestimonis = new ArrayList<>();
//    private AdapterTestimoni viewAdapter;

    private ImageView gambar ;
    private TextView tanggal;

    //testimoni testimoni = new testimoni();
    //produk produk = new produk();

    Context mContext;
    private AlertDialog.Builder dialog;
    private LayoutInflater inflater;
    private View dialogView;
    private ProgressDialog progress;

    @BindView(R.id.recyclerView1)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testimoni);

//        ButterKnife.bind(this);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Testimoni");
//
//        viewAdapter = new AdapterTestimoni(this, listTestimonis);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(viewAdapter);
//
//
//        mContext = this;
//        progress = new ProgressDialog(mContext);
//        testimoni.ViewTestimoni(recyclerView, mContext);
    }


}
