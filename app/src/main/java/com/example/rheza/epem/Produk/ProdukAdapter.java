package com.example.rheza.epem.Produk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rheza.epem.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ViewHolder> {
    private Context context;
    private List<ListProduk> listProduks;


    ProdukAdapter(Context context, List<ListProduk> listProduks) {
        this.listProduks = listProduks;
        this.context = context;
    }

    @NonNull
    @Override
    public ProdukAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_produk, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder( ProdukAdapter.ViewHolder holder, final int i) {
        ListProduk listProduk = listProduks.get(i);
        holder.TextNamaPro.setText(listProduks.get(i).getNama_produk());
        holder.TextNamaKat.setText(listProduks.get(i).getKategori());
        holder.silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  produk produk = new produk();
                 produk.deleteProduk(context, listProduks.get(i).getId_produk());
            }
        });
        holder.ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        String url = "http://192.168.42.199/" + listProduks.get(i).getGambar();
        Picasso.with(context).load(url).into(holder.foto);
    }



    @Override
    public int getItemCount() {
        return listProduks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView TextDeskripsi, TextNamaPro , TextNamaKat;
        Button   ubah ;
        ImageView foto , silang;


        ViewHolder(View itemView) {
            super(itemView);


        TextNamaPro = itemView.findViewById(R.id.textNamaPro);
        TextNamaKat = itemView.findViewById(R.id.textNamaKat);
        silang = itemView.findViewById(R.id.delete);
        ubah = itemView.findViewById(R.id.ubahdata);
        foto = itemView.findViewById(R.id.foto);
        }
    }

}