package com.example.rheza.epem.Kategori;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.rheza.epem.R;

import com.example.rheza.epem.RetrofitServer;
import com.example.rheza.epem.Value;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class kategori {
    private String id_kategori;

    private String nama_kategori;
    private String id_jenis_kategori;
    private List<ListKat> kats ;



    KategoriAdapter kategoriAdapter;





    public String getId_jenis_kategori() {
        return id_jenis_kategori;
    }

    public void setId_jenis_kategori(String id_jenis_kategori) {
        this.id_jenis_kategori = id_jenis_kategori;
    }

    public String getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getNama_kategori() {
        return nama_kategori;
    }

    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }

    public void tambahKategori(String id_jenis_kategori, String nama_kategori, final ProgressDialog progress, final Context mContext) {

        this.setId_jenis_kategori(id_jenis_kategori);
        this.setNama_kategori(nama_kategori);

        progress.setTitle("Tambah Kategori");
        progress.setMessage("Tunggu Sebentar . . .");
        progress.show();

        Runnable pr = new Runnable() {
            @Override
            public void run() {

               Call<Value> call = RetrofitServer
                        .getInstance()
                        .RegisterApi()
                       .TambahKategori(

                               getId_jenis_kategori(), getNama_kategori());

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
                                    .setMessage("Kategori " + getNama_kategori() + " Berhasil ditambahkan")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {


                                            Intent i = new Intent(mContext, Activity_kategori.class);

                                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            ((Activity_kategori) mContext).startActivityForResult(i, 0);
                                            ((Activity_kategori) mContext).overridePendingTransition(0, 0);
                                            mContext.startActivity(i);
                                        }
                                    }).show();
                        } else if (value.equals("0")) {
                            progress.dismiss();
                            new AlertDialog.Builder(mContext)
                                    .setIcon(R.drawable.failed)
                                    .setTitle("Failed")
                                    .setMessage(message)
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).show();
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


public void ViewKusen(final RecyclerView recyclerView, final Context mContext){
    Call<List<ListKat>> call = RetrofitServer
            .getInstance()
            .RegisterApi()
            .coba();

    call.enqueue(new Callback<List<ListKat>>() {
        @Override
        public void onResponse(Call<List<ListKat>> call, retrofit2.Response<List<ListKat>> response) {
            kats = response.body();
            kategoriAdapter = new KategoriAdapter(mContext,kats);
            recyclerView.setAdapter(kategoriAdapter);
            kategoriAdapter.notifyDataSetChanged();
        }

        @Override
        public void onFailure(Call<List<ListKat>> call, Throwable t) {

        }
    });
}

    public void ViewBangunan(final RecyclerView recyclerView, final Context mContext){
        Call<List<ListKat>> call = RetrofitServer
                .getInstance()
                .RegisterApi()
                .percobaan();

        call.enqueue(new Callback<List<ListKat>>() {
            @Override
            public void onResponse(Call<List<ListKat>> call, retrofit2.Response<List<ListKat>> response) {
                kats = response.body();
                kategoriAdapter = new KategoriAdapter(mContext,kats);
                recyclerView.setAdapter(kategoriAdapter);
                kategoriAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ListKat>> call, Throwable t) {

            }
        });
    }


    public void deleteKategori(final Context mContext, final String id_kategori) {
        new AlertDialog.Builder(mContext)


                .setTitle("Hapus Kategori")
                .setMessage("Anda Yakin Ingin Menghapus Data " + id_kategori + " ? ")
                .setCancelable(false)
                .setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Call<Value> call = RetrofitServer
                        .getInstance()
                        .RegisterApi()
                        .HapusKategori(id_kategori);
//

                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, retrofit2.Response<Value> response) {
//
                        String value = response.body().getValue();
                        String message = response.body().getMessage();

                        if (value.equals("1")) {

                            new AlertDialog.Builder(mContext)

                                    //.setIcon(R.drawable.success)
                                    .setTitle("Berhasil")
                                    .setMessage("Data " + id_kategori + " Berhasil Dihapus")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {



                                            Intent i = new Intent(mContext, Activity_kategori.class);

                                            ((Activity_kategori) mContext).startActivityForResult(i, 0);
                                            ((Activity_kategori) mContext).overridePendingTransition(0, 0);
                                            mContext.startActivity(i);

                                        }
                                    }).show();
                        } else if (value.equals("0")) {
                            new AlertDialog.Builder(mContext)
                                    .setIcon(R.drawable.failed)
                                    .setTitle("Gagal")
                                    .setMessage(message)
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {

                    }
                });
            }

        }).show();
    }
}

