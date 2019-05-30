package com.example.rheza.epem.Pesanan;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import android.widget.TextView;

import com.example.rheza.epem.R;
import com.example.rheza.epem.RegisterAPI;
import com.example.rheza.epem.Value;

import java.util.List;


import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PesananAdapter extends RecyclerView.Adapter<PesananAdapter.ViewHolder> {

    public static final String WA = "https://wa.me/";
    public static final String URL = "http://192.168.42.199/EPEM/";



    private Context context;
    private List<ListPesanan> pesanans;

    PesananAdapter(Context context, List<ListPesanan> pesanans) {
        this.context = context;
        this.pesanans = pesanans;
    }

    @Override
    public PesananAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_psanan, parent, false);
        PesananAdapter.ViewHolder holder = new PesananAdapter.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(PesananAdapter.ViewHolder holder, final int i) {
        ListPesanan pesanan = pesanans.get(i);
        holder.textViewIDPes.setText(pesanan.getId_pesanan());
        holder.textViewNamaPes.setText(pesanan.getNama_pesanan());
        holder.Textno_wa.setText(pesanan.getNo_wa());
        holder.TextAlamat.setText(pesanan.getAlamat_pesanan());
       holder.statusRes.setText(pesanan.getStatus_pesanan());
        holder.silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesanan pesanan1 = new pesanan();
                pesanan1.deletePesanan(context, pesanans.get(i).getId_pesanan());
            }
        });

    }

    @Override
    public int getItemCount() {
        return pesanans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView textViewIDPes, textViewNamaPes , Textno_wa , TextAlamat , statusRes;
        ImageView silang ;





        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            textViewIDPes = itemView.findViewById(R.id.Textid_pesanan);
            textViewNamaPes = itemView.findViewById(R.id.Textnama_pesanan);
            Textno_wa = itemView.findViewById(R.id.Textno_wa);
            TextAlamat = itemView.findViewById(R.id.Textakamat_pesanan);
            statusRes = itemView.findViewById(R.id.statusR);
            silang = itemView.findViewById(R.id.delete);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {



            loadDataWA();
            String no_wa = Textno_wa.getText().toString();
            Uri uri = Uri.parse("http://api.whatsapp.com/send?phone=" +62+ no_wa + "&text=Hallo%20Saya%20Rheza");
            final Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);

            sendIntent.setPackage("com.whatsapp");
//            sendIntent.setData(Uri.parse(Url));




//
            statusRes.setText("PROSES" );

            String Status_pesanan = statusRes.getText().toString();
         //   statusRes.setText("PROSES" );
//
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RegisterAPI api = retrofit.create(RegisterAPI.class);
            Call<Value> call = api.UpdateStatus(Status_pesanan);
            call.enqueue(new Callback<Value>() {
                @Override
                public void onResponse(Call<Value> call, Response<Value> response) {
                    statusRes.setText("PROSES" );
//

                }

                @Override
                public void onFailure(Call<Value> call, Throwable t) {

                }
            });




            context.startActivity(sendIntent);

        }


    }

    private void loadDataWA() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.NomerWA();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }
      private void updateS(){

}
//

}

