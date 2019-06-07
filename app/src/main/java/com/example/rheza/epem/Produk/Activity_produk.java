package com.example.rheza.epem.Produk;

import android.annotation.SuppressLint;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;



import com.example.rheza.epem.Kategori.kategori;
import com.example.rheza.epem.R;
import com.example.rheza.epem.RegisterAPI;
import com.example.rheza.epem.Value;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Activity_produk extends AppCompatActivity {

    private ProgressDialog progress;
    public static final String URL = "http://192.168.42.199/";

    Context mContext;


    private final static int IMG_REQUEST = 999;
    private final static int Request_take = 0;




    private MaterialEditText et_idProduk , et_namaProduk , et_deskripsi;
    private Spinner spinnerProduk;
    private TextView textKategori;
    private ImageView uploadImage;
    private Button pilihImage;
    private Bitmap bitmap;


    private List<ListProduk> produks = new ArrayList<>();

    private AlertDialog.Builder dialog;
    private LayoutInflater inflater;
    private View dialogView;

    private kategori kat = new kategori();
    private produk pro = new produk();
    private ProdukAdapter produkAdapter;


    @BindView(R.id.recyclerView1)
    RecyclerView recyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Produk");

        produkAdapter = new ProdukAdapter(this, produks );
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(produkAdapter);
       // recyclerView.setHasFixedSize(true);

        mContext = this;
        progress = new ProgressDialog(mContext);
        pro.lihatProduk(recyclerView, mContext);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tambah, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        switch (id){
            case R.id.action_add:
                dialog = new AlertDialog.Builder(Activity_produk.this);
                inflater = getLayoutInflater();
                dialogView = inflater.inflate(R.layout.activity_tambah_pro, null);
                dialog.setView(dialogView);
                dialog.setCancelable(false);
                dialog.setTitle("Tambah Data");


                et_namaProduk = dialogView.findViewById(R.id.et_namaProduk);
                spinnerProduk = dialogView.findViewById(R.id.spinnerProduk);
                et_deskripsi = dialogView.findViewById(R.id.et_deskripsi);
                pilihImage = dialogView.findViewById(R.id.pilihImage);
                uploadImage = dialogView.findViewById(R.id.imageUpload);



                textKategori = dialogView.findViewById(R.id.textKategori);


                getProduk();



                pilihImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pilihFoto();
                    }
                });
                dialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogCancel, int which) {
                        dialogCancel.dismiss();
                        if(TextUtils.isEmpty(et_namaProduk.getText())){
                            new AlertDialog.Builder(mContext)
                                    .setIcon(R.drawable.failed)
                                    .setTitle("Failed")
                                    .setMessage("ID Produk Kosong")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).show();
                        }else if(textKategori.getText() == "Pilih Nama Kategori"){
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
//
                        }else{

                            String nama_produk = et_namaProduk.getText().toString();
                            String nama_kategori      = spinnerProduk.getSelectedItem().toString();
                            String deskripsi = et_deskripsi.getText().toString();
                            String gambar = imageToString();




                            pro.PrevKategori(kat);// periksa.PrevRM(rm);

                            pro.addProduk(nama_produk,nama_kategori,deskripsi,gambar,progress,mContext);


                        }
                    }
                }).setPositiveButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogCancel, int which) {
                        dialogCancel.dismiss();
                    }
                }).show();

                return true;
            default:
        }

        return super.onOptionsItemSelected(item);
    }




    public void getProduk(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.LihatKategori();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
               // String value = response.body().getValue();
                if(response.isSuccessful()){
                    final Value ListKat = response.body();
                    //String value = response.body().getValue();


                  final List<String> listSpinner = new ArrayList<>();

                    listSpinner.add(0, "Nama Kategori");

                    assert ListKat != null;
                    for(int i = 0; i< ListKat.getListKat().size(); i++){
                        listSpinner.add(ListKat.getListKat().get(i).getNama_kategori());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerProduk.setAdapter(adapter);

                    spinnerProduk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(parent.getItemAtPosition(position).equals("Nama Kategori")){
                                textKategori.setText("Pilih Nama Kategori");
                            }else{
                                for(int i=0; i< ListKat.getListKat().size(); i++){
                                    if(position == i+1){
                                        textKategori.setText(ListKat.getListKat().get(i).getNama_kategori());
                                    }
                                }
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }

    private void pilihFoto(){
        Intent intent = new Intent();
        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, IMG_REQUEST);
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent , IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();

            try{
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                uploadImage.setImageBitmap(bitmap);
                uploadImage.setVisibility(View.VISIBLE);

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    private String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);

    }
    private void uploadImage(){
       // String image = imageToString();
    }
}
