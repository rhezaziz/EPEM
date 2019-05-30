package com.example.rheza.epem;

import com.example.rheza.epem.Kategori.ListKat;
import com.example.rheza.epem.Pesanan.ListPesanan;
import com.example.rheza.epem.Produk.ListProduk;

import java.util.List;

public class Value {
    String value;
    String message;

   private List<ListProduk> ListProduk;
   private List<ListPesanan> ListPesanan;
    private List<ListKat> ListKat;
//
//   private List<ListKate> listKates;
////  private  List<listTestimoni> listTestimoni;
////
////    public List<com.example.rheza.epem.Testimoni.listTestimoni> getListTestimoni() {
////        return listTestimoni;
////    }
//
//
//    public List<ListKate> getListKates() {
//        return listKates;
//    }



    public List<com.example.rheza.epem.Produk.ListProduk> getListProduk() {
        return ListProduk;
    }

    public List<com.example.rheza.epem.Kategori.ListKat> getListKat() {
        return ListKat;
    }

    public List<com.example.rheza.epem.Pesanan.ListPesanan> getListPesanan() {
        return ListPesanan;
    }


    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
