package com.example.rheza.epem.Kategori;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.rheza.epem.Pesanan.pesanan;
import com.example.rheza.epem.R;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.ViewHolder> {

//    public static final String URL = "http://192.168.42.199/EPEM/";


    private Context context;
    private List<ListKat> listKats;

    KategoriAdapter(Context context, List<ListKat> listKats) {
        this.context = context;
        this.listKats = listKats;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_kategori, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ListKat listKat = listKats.get(position);
       // holder.textViewID.setText(listKat.getId_kategori());
        holder.textViewJenis.setText(listKat.getId_jenis_kategori());
        holder.textViewNAME.setText(listKat.getNama_kategori());
        holder.silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kategori kategori = new kategori();
                kategori.deleteKategori(context, listKats.get(position).getId_kategori());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.textId)
//        TextView textViewID;
        @BindView(R.id.textNama)
        TextView textViewNAME;
        @BindView(R.id.delete)
        ImageView silang;
        @BindView(R.id.textIdJenis)
        TextView textViewJenis;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
           // silang = itemView.findViewById(R.id.delete);
        }

    }
}

