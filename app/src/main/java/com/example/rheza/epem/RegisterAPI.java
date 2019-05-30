package com.example.rheza.epem;

import com.example.rheza.epem.Kategori.ListKat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RegisterAPI {


    //Kategori
    //Read kategori
    @GET("EPEM/kategori/LihatKategori.php")
    Call<Value> LihatKategori();


    //Tambah Kategori
    @FormUrlEncoded
    @POST("EPEM/kategori/TambahKategori.php")
    Call<Value> TambahKategori(
            //@Field("id_kategori") String id_kategori,
                       @Field("id_jenis_kategori") String id_jenis_kategori,
                       @Field("nama_kategori") String nama_kategori);



    @GET("web_EPEM/api/getKategoriJenis?id_jenis_kategori=JK01")
    Call<List<ListKat>> percobaan();

    @GET("web_EPEM/api/getKategoriJenis?id_jenis_kategori=JK02")
    Call<List<ListKat>> coba();
//
//    @GET("web_EPEM/api/getKategoriJenis?id_jenis_kategori=JK02")
//    Call<List<ListKat>> Nyoba();


    //Hapus Kategori
    @FormUrlEncoded
    @POST("EPEM/kategori/HapusKategori.php")
    Call<Value> HapusKategori(@Field("id_kategori") String id_kategori);


    @GET("EPEM/kategori/LihatNama.php")
    Call<Value> lihatNamaKategori();




    //Pesanan

    //Tambah Pesanan
    @FormUrlEncoded
    @POST("EPEM?Pesanan/tambahPemesan.php")
    Call<Value> TambahPesanan(
            //@Field("id_pesanan") String id_pesanan,
                              @Field("nama_pesanan") String nama_pesanan,
                              @Field("no_wa") String no_wa,
                              @Field("alamat_pesanan") String alamat_pesanan
    //                                );
                              ,@Field("Status_pesanan")String Status_pesanan);


   //Read Pesanan
    @GET("EPEM/Pesanan/lihatPemesan.php")
    Call<Value> lihatPemesan();


    //Get Nomer Wa
    @GET("EPEM/Pesanan/GetWa.php")
    Call<Value> NomerWA();

    //Hapus Pesanan
    @FormUrlEncoded
    @POST("EPEM/Pesanan/HapusPesanan.php")
    Call<Value> HapusPesanan(@Field("id_pesanan")String id_pesanan);
                            // @Field("nama_pesanan") String nama_pesanan);

    @FormUrlEncoded
    @POST("EPEM/Pesanan/updateStatus.php")
    Call<Value>UpdateStatus(
           // @Field("id_pesanan") String id_pesanan,
                            @Field("Status_pesanan") String Status_pesanan);



    //Produk
    //lihat Produk
    @GET("EPEM/Produk/lihatProduk.php")
    Call<Value>lihatProduk();

    //tambah produk
    @FormUrlEncoded
    @POST("EPEM/Produk/tambahProduk.php")
    Call<Value> TambahProduk(@Field("nama_produk") String nama_produk,
                             @Field("nama_kategori") String nama_kategori,
                             @Field("deskripsi") String deskripsi,
                             @Field("gambar") String gambar);

    //Hapus Produk
    @FormUrlEncoded
    @POST("EPEM/Produk/HapusProduk.php")
    Call<Value> HapusProduk(@Field("id_produk") String id_produk);


    @GET("Produk_model.php")
    Call<Value> viewPro();



    //Testimoni

    //view Testimoni
//    @GET("EPEM/Testimoni/lihatTestimoni.php")
//    Call<List<listTestimoni>> LihatTestimoni();

}
