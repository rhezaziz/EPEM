package com.example.rheza.epem.Pesanan;

public class ListPesanan {
    String id_pesanan;
    String nama_pesanan;
    String no_wa;
    String alamat_pesanan;
    String Status_pesanan;

    public String getStatus_pesanan() {
        return Status_pesanan;
    }

    public String getId_pesanan() {
        return id_pesanan;
    }

    public String getNama_pesanan() {
        return nama_pesanan;
    }

    public void setStatus_pesanan(String status_pesanan) {
        Status_pesanan = status_pesanan;
    }

    public String getNo_wa() {

        return no_wa;
    }

    public String getAlamat_pesanan() {
        return alamat_pesanan;
    }
}
