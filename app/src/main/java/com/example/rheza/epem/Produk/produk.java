package com.example.rheza.epem.Produk;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.rheza.epem.Kategori.kategori;

import com.example.rheza.epem.R;

import com.example.rheza.epem.RetrofitServer;
import com.example.rheza.epem.Value;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class produk extends kategori {
    public static final String URL = "http://192.168.42.199/";

    ProgressDialog progress;
    private String id_produk;
    private String nama_produk;
    private String deskripsi;
    private String gambar;
   private kategori kat ;
   // private List<ListProduk> listProduks;
    List<ListProduk> pro = new ArrayList<>();




    public String getId_produk() {
        return id_produk;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public void setId_produk(String id_produk) {
        this.id_produk = id_produk;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void PrevKategori(kategori kat){
        this.kat = kat;
    }



    public void addProduk( String nama_produk , String nama_kategori ,String deskripsi, String gambar , final ProgressDialog progress, final Context mContext){
        this.setId_produk(id_produk);
        this.setNama_produk(nama_produk);
        this.setDeskripsi(deskripsi);
        this.setGambar(gambar);

        this.kat.setNama_kategori(nama_kategori);


        progress.setTitle("Tambah Produk");
        progress.setMessage("Tunggu Sebentar . . .");
        progress.show();

        Runnable pr = new Runnable() {
            @Override
            public void run() {
                Call<Value> call = RetrofitServer
                        .getInstance()
                        .RegisterApi()
                        .TambahProduk(getNama_produk(),kat.getNama_kategori(), getDeskripsi(), getGambar());
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, retrofit2.Response<Value> response) {
                        String value = response.body().getValue();
                        String message = response.body().getMessage();

                        if (value.equals("1")) {
                            progress.dismiss();
                            new AlertDialog.Builder(mContext)
//                                    .setIcon(R.drawable.success)
                                    .setTitle("Berhasil")
                                    .setMessage("Pesanan " + getNama_produk() + " Berhasil ditambahkan")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {


                                            Intent i = new Intent(mContext, Activity_produk.class);

                                           // i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            ((Activity_produk) mContext).startActivityForResult(i, 0);
                                            ((Activity_produk) mContext).overridePendingTransition(0, 0);
                                            mContext.startActivity(i);
                                        }
                                    }).show();
                        } else if (value.equals("0")) {
                            progress.dismiss();
                            new AlertDialog.Builder(mContext)
                                    .setIcon(R.drawable.failed)
                                    .setTitle("Gagal")
                                    .setMessage(message)
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
//

                                        }
                                    })
                                    .show();
                        }

                    }


                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        progress.dismiss();
                        Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG);

                    }
                });
            }
        };

        Handler pdCancel = new Handler();
        pdCancel.postDelayed(pr, 3000);

    }

    public void lihatProduk(final RecyclerView recyclerView, final Context mContext){
        Call<Value> call = RetrofitServer
                .getInstance()
                .RegisterApi()
                .lihatProduk();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                assert response.body() != null;
                String value = response.body().getValue();
                //.setVisibility(View.GONE);
                //progress.setVisibility(View.GONE);
                if (value.equals("1")) {
                       List<ListProduk> pro = new ArrayList<>();
                    pro = response.body().getListProduk();
                    ProdukAdapter viewAdapter = new ProdukAdapter(mContext, pro);
                    recyclerView.setAdapter(viewAdapter);
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }

    public void deleteProduk(final Context mContext, final String id_produk ){
//
        new AlertDialog.Builder(mContext)


                .setTitle("Hapus Pesanan")
                .setMessage("Anda Yakin Ingin Menghapus Data " + id_produk + " ? ")
                .setCancelable(false)
                .setPositiveButton("TIDAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("YA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Call<Value> call = RetrofitServer
                        .getInstance()
                        .RegisterApi()
                        .HapusProduk(id_produk);
//

                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, retrofit2.Response<Value> response) {
                        String value = response.body().getValue();
                        String message = response.body().getMessage();

                        if (value.equals("1")) {
                            //progress.dismiss();
                            new AlertDialog.Builder(mContext)
                                    //.setIcon(R.drawable.success)
                                    .setTitle("Berhasil")
                                    .setMessage("Data " + getNama_produk() + " Berhasil Dihapus")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {



                                            Intent i = new Intent(mContext, Activity_produk.class);

                                            ((Activity_produk) mContext).startActivityForResult(i, 0);
                                            ((Activity_produk) mContext).overridePendingTransition(0, 0);
                                            mContext.startActivity(i);

                                        }
                                    }).show();
                        } else if (value.equals("0")) {
                            new AlertDialog.Builder(mContext)
                                    .setIcon(R.drawable.failed)
                                    .setTitle("Gagal")
                                    .setMessage("Data " + getNama_produk() + " Gagal Dihapus")
                                    .setCancelable(false)
                                    .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).show();
                        }
//
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {

                    }
                });
            }

        }).show();
    }
}




