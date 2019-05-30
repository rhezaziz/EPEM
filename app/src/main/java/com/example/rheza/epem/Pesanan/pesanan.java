package com.example.rheza.epem.Pesanan;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import retrofit2.Response;


public class pesanan {


    public static final String URL = "http://192.168.42.199/EPEM/";

    ProgressDialog progress;

    private String id_pesanan;
    private String Status_pesanan;
    private String nama_pesanan;
    private String no_wa;
    private String alamat_pesanan;

    List<ListPesanan> kats ;

    public String getNama_pesanan() {
        return nama_pesanan;
    }

    public void setNama_pesanan(String nama_pesanan) {
        this.nama_pesanan = nama_pesanan;
    }

    public String getNo_wa() {
        return no_wa;
    }

    public void setNo_wa(String no_wa) {
        this.no_wa = no_wa;
    }

    public String getAlamat_pesanan() {
        return alamat_pesanan;
    }

    public void setAlamat_pesanan(String alamat_pesanan) {
        this.alamat_pesanan = alamat_pesanan;
    }

    public String getId_pesanan() {
        return id_pesanan;
    }

    public void setId_pesanan(String id_pesanan) {
        this.id_pesanan = id_pesanan;
    }



    public void setStatus_pesanan(String status_pesanan) {
        Status_pesanan = status_pesanan;
    }





    public String getStatus_pesanan() {
        return Status_pesanan;
    }




//

    public void deletePesanan(final Context mContext, final String id_pesanan ){
//        this.id_pesanan = id_pesanan;
//        this.nama_pesanan = nama_pesanan;
        new AlertDialog.Builder(mContext)


                .setTitle("Hapus Pesanan")
                .setMessage("Anda Yakin Ingin Menghapus Data " + id_pesanan + " ? ")
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
                        .HapusPesanan(id_pesanan);
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
                                    .setMessage("Data " + getId_pesanan() + " Berhasil Dihapus")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {



                                            Intent i = new Intent(mContext, Activity_pesanan.class);
                                          //  i.putExtra("id_admin", id_admin);
                                           // i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            ((Activity_pesanan) mContext).startActivityForResult(i, 0);
                                            ((Activity_pesanan) mContext).overridePendingTransition(0, 0);
                                            mContext.startActivity(i);

                                        }
                                    }).show();
                        } else if (value.equals("0")) {
                            new AlertDialog.Builder(mContext)
                                    .setIcon(R.drawable.failed)
                                    .setTitle("Gagal")
                                    .setMessage("Data " + getId_pesanan() + " Gagal Dihapus")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
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

            public void ViewPesanan(final RecyclerView recyclerView, final Context mContext) {
                Call<Value> call = RetrofitServer
                        .getInstance()
                        .RegisterApi()
                        .lihatPemesan();
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        assert response.body() != null;
                        String value = response.body().getValue();
                        //.setVisibility(View.GONE);
                        //progress.setVisibility(View.GONE);
                        if (value.equals("1")) {
                            //   List<ListPesanan> kats = new ArrayList<>();
                            kats = response.body().getListPesanan();
                            PesananAdapter viewAdapter = new PesananAdapter(mContext, kats);
                            recyclerView.setAdapter(viewAdapter);
                        }
                    }


                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {

                    }
                });
            }
        }
